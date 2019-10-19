package com.bergerkiller.bukkit.common.internal.logic;

import java.lang.reflect.Modifier;
import java.util.IdentityHashMap;

import org.bukkit.entity.Entity;

import com.bergerkiller.bukkit.common.Common;
import com.bergerkiller.bukkit.common.bases.ExtendedEntity;
import com.bergerkiller.bukkit.common.conversion.type.HandleConversion;
import com.bergerkiller.bukkit.common.internal.hooks.EntityTrackerEntryHook;
import com.bergerkiller.bukkit.common.internal.hooks.EntityTrackerEntryHook_1_14;
import com.bergerkiller.bukkit.common.utils.CommonUtil;
import com.bergerkiller.bukkit.common.wrappers.EntityTracker;
import com.bergerkiller.generated.net.minecraft.server.EntityTrackerEntryHandle;
import com.bergerkiller.generated.net.minecraft.server.EntityTrackerEntryStateHandle;
import com.bergerkiller.generated.net.minecraft.server.EntityTrackerHandle;
import com.bergerkiller.generated.net.minecraft.server.EntityTypesHandle;
import com.bergerkiller.generated.net.minecraft.server.WorldServerHandle;
import com.bergerkiller.mountiplex.reflection.ClassInterceptor;
import com.bergerkiller.mountiplex.reflection.ClassTemplate;
import com.bergerkiller.mountiplex.reflection.declarations.ClassResolver;
import com.bergerkiller.mountiplex.reflection.declarations.MethodDeclaration;
import com.bergerkiller.mountiplex.reflection.declarations.SourceDeclaration;
import com.bergerkiller.mountiplex.reflection.util.FastMethod;

/**
 * Since Minecraft 1.14 it has become much harder to know what NMS Entity
 * Class is spawned for various Entity Types. This class tracks
 * all these in a map.
 */
public class EntityTypingHandler_1_14 extends EntityTypingHandler {
    private final IdentityHashMap<Object, Class<?>> _cache = new IdentityHashMap<>();
    private final FastMethod<Class<?>> findEntityTypesClass = new FastMethod<>();
    private final FastMethod<Object> createEntry = new FastMethod<>();
    private final Object nmsWorldHandle;

    // Initialize findEntityTypesClass which is a fallback for types we did not pre-register
    public EntityTypingHandler_1_14() {
        {
            ClassResolver resolver = new ClassResolver();
            resolver.setDeclaredClass(EntityTypesHandle.T.getType());
            MethodDeclaration m = new MethodDeclaration(resolver, 
                    "public Class<?> findClassFromEntityTypes(net.minecraft.server.World world) {\n" +
                    "    Object entity = instance.a(world);\n" +
                    "    if (entity == null) {\n" +
                    "        return null;\n" +
                    "    } else {\n" +
                    "        return entity.getClass();\n" +
                    "    }\n" +
                    "}");
            findEntityTypesClass.init(m);
        }

        // Initialize a dummy field with the sole purpose of constructing an entity without errors
        this.nmsWorldHandle = WorldServerHandle.T.newInstanceNull();
        {
            // Create WorldData instance by calling the protected empty constructor
            Object nmsWorldData = ClassTemplate.createNMS("WorldData").getConstructor().newInstance();

            ClassResolver resolver = new ClassResolver();
            resolver.setDeclaredClass(WorldServerHandle.T.getType());
            MethodDeclaration m = new MethodDeclaration(resolver, SourceDeclaration.preprocess(
                    "public void initWorldServer(WorldData worldData) {\n" +
                    "    // Spigot World configuration\n" +
                    "#if fieldexists net.minecraft.server.World public final org.spigotmc.SpigotWorldConfig spigotConfig;\n" +
                    "    #require net.minecraft.server.World public final org.spigotmc.SpigotWorldConfig spigotConfig;\n" +
                    "    org.spigotmc.SpigotWorldConfig spigotConfig = new org.spigotmc.SpigotWorldConfig(\"DUMMY\");\n" +
                    "    instance#spigotConfig = spigotConfig;\n" +
                    "#endif\n" +
                    "#if fieldexists net.minecraft.server.World public final com.destroystokyo.paper.PaperWorldConfig paperConfig;\n" +
                    "    #require net.minecraft.server.World public final com.destroystokyo.paper.PaperWorldConfig paperConfig;\n" +
                    "    com.destroystokyo.paper.PaperWorldConfig paperConfig = new com.destroystokyo.paper.PaperWorldConfig(\"DUMMY\", spigotConfig);\n" +
                    "    instance#paperConfig = paperConfig;\n" +
                    "#endif\n" +
                    "\n" +
                    "    // worldProvider field\n" +
                    "    int envId = org.bukkit.World.Environment.NORMAL.getId();\n" +
                    "    instance.worldProvider = DimensionManager.a(envId).getWorldProvider(instance);\n" +
                    "\n" +
                    "    // worldData field\n" +
                    "    #require net.minecraft.server.World public final WorldData worldData;\n" +
                    "    instance#worldData = worldData;\n" +
                    "}"));
            FastMethod<Void> fm = new FastMethod<>();
            fm.init(m);
            fm.invoke(this.nmsWorldHandle, nmsWorldData);
        }

        // Pre-register certain classes that cause events to be fired when constructing
        registerEntityTypes("AREA_EFFECT_CLOUD", "EntityAreaEffectCloud");
        registerEntityTypes("ENDER_DRAGON", "EntityEnderDragon");
        registerEntityTypes("FIREBALL", "EntityLargeFireball");
        registerEntityTypes("WITHER", "EntityWither"); // scoreboard things

        // Initialize method that creates new EntityTrackerEntry instances (which are actually PlayerChunkMap$EntityTracker)
        {
            ClassResolver resolver = new ClassResolver();
            resolver.setDeclaredClass(EntityTrackerHandle.T.getType());
            MethodDeclaration createEntryMethod = new MethodDeclaration(resolver, 
                    "public Object createEntry(Entity entity) {\n" +
                    "    EntityTypes entitytypes = entity.getEntityType();\n" +
                    "    int i = entitytypes.getChunkRange() * 16;\n" +
                    (Common.IS_SPIGOT_SERVER ? "i = org.spigotmc.TrackingRange.getEntityTrackingRange(entity, i);\n" : "") +
                    "    int j = entitytypes.getUpdateInterval();\n" +
                    "    return new PlayerChunkMap$EntityTracker(instance, entity, i, j, entitytypes.isDeltaTracking());\n" +
                    "}");
            createEntry.init(createEntryMethod);
        }
    }

    private void registerEntityTypes(String name, String nmsClassName) {
        try {
            java.lang.reflect.Field field = EntityTypesHandle.T.getType().getDeclaredField(name);
            if ((field.getModifiers() & Modifier.STATIC) == 0) {
                throw new IllegalStateException("EntityTypes field " + name + " is not static");
            }
            
            Object nmsEntityTypes;
            if ((field.getModifiers() & Modifier.PUBLIC) == 0) {
                field.setAccessible(true);
                nmsEntityTypes = field.get(null);
                field.setAccessible(false);
            } else {
                nmsEntityTypes = field.get(null);
            }

            Class<?> type = CommonUtil.getNMSClass(nmsClassName);
            if (type == null) {
                throw new IllegalStateException("EntityTypes type " + nmsClassName + " not found");
            }

            this._cache.put(nmsEntityTypes, type);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public Class<?> getClassFromEntityTypes(Object nmsEntityTypesInstance) {
        Class<?> result = this._cache.get(nmsEntityTypesInstance);
        if (result == null) {
            result = this.findEntityTypesClass.invoke(nmsEntityTypesInstance, this.nmsWorldHandle);
            this._cache.put(nmsEntityTypesInstance, result);
        }
        return result;
    }

    @Override
    public EntityTrackerEntryHandle createEntityTrackerEntry(EntityTracker entityTracker, Entity entity) {
        Object handle = createEntry.invoke(entityTracker.getRawHandle(), HandleConversion.toEntityHandle(entity));
        EntityTrackerEntryHandle entry = EntityTrackerEntryHandle.createHandle(handle);

        // Set the passengers field to the current passengers
        EntityTrackerEntryStateHandle.T.opt_passengers.set(entry.getState().getRaw(), (new ExtendedEntity<>(entity)).getPassengers());

        return entry;
    }

    @Override
    public EntityTrackerEntryHook getEntityTrackerEntryHook(Object entityTrackerEntryHandle) {
        return ClassInterceptor.get(entityTrackerEntryHandle, EntityTrackerEntryHook_1_14.class);
    }

    @Override
    public Object hookEntityTrackerEntry(Object entityTrackerEntryHandle) {
        return new EntityTrackerEntryHook_1_14().hook(entityTrackerEntryHandle);
    }
}
