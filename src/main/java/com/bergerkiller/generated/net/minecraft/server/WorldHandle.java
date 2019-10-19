package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import com.bergerkiller.bukkit.common.bases.IntVector3;
import com.bergerkiller.bukkit.common.wrappers.BlockData;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import java.util.List;
import java.util.Random;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.World</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class WorldHandle extends IBlockAccessHandle {
    /** @See {@link WorldClass} */
    public static final WorldClass T = new WorldClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(WorldHandle.class, "net.minecraft.server.World", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static WorldHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public abstract void method_profiler_begin(String label);
    public abstract void method_profiler_end();
    public abstract World getWorld();
    public abstract Server getServer();
    public abstract BlockData getBlockData(IntVector3 blockposition);
    public abstract BlockData getBlockDataAtCoord(int x, int y, int z);
    public abstract boolean setBlockData(IntVector3 blockposition, BlockData iblockdata, int updateFlags);
    public abstract long getTime();
    public abstract boolean isWithinWorldBorder(EntityHandle entity);
    public abstract boolean isNotCollidingWithBlocks(EntityHandle entity, AxisAlignedBBHandle axisalignedbb);
    public abstract List<?> getRawEntitiesOfType(Class<?> rawType, AxisAlignedBBHandle bounds);
    public abstract List<EntityHandle> getNearbyEntities(EntityHandle entity, AxisAlignedBBHandle axisalignedbb);
    public abstract TileEntityHandle getTileEntity(IntVector3 blockposition);
    public abstract WorldDataHandle getWorldData();
    public abstract boolean isBurnArea(AxisAlignedBBHandle bounds);
    public abstract Entity getEntityById(int entityId);
    public abstract boolean areChunksLoaded(IntVector3 blockposition, int distance);
    public abstract MovingObjectPositionHandle rayTrace(Vector point1, Vector point2);
    public abstract void applyBlockPhysics(IntVector3 position, BlockData causeType);
    public abstract void setSectionSkyLight(int cx, int cy, int cz, byte[] data);
    public abstract void setSectionBlockLight(int cx, int cy, int cz, byte[] data);
    public abstract byte[] getSectionSkyLight(int cx, int cy, int cz);
    public abstract byte[] getSectionBlockLight(int cx, int cy, int cz);

    public static final int UPDATE_PHYSICS = 0x1; // flag specifying block physics should occur after the change
    public static final int UPDATE_NOTIFY = 0x2; // flag specifying the change should be updated to players
    public static final int UPDATE_DEFAULT = (UPDATE_PHYSICS | UPDATE_NOTIFY); // default flags used when updating block types


    public void applyPhysics(IntVector3 position, BlockData causeType, boolean self) {
        if (T.opt_applyPhysics.isAvailable()) {
            T.opt_applyPhysics.invoke(getRaw(), position, causeType, self);
        } else if (T.opt_applyPhysics_old.isAvailable()) {
            T.opt_applyPhysics_old.invoke(getRaw(), position, causeType);
        } else {
            throw new UnsupportedOperationException("Apply physics function not available on this server");
        }
        if (self) {
            applyBlockPhysics(position, causeType);
        }
    }


    public org.bukkit.World toBukkit() {
        return com.bergerkiller.bukkit.common.conversion.Conversion.toWorld.convert(getRaw());
    }

    public static WorldHandle fromBukkit(org.bukkit.World world) {
        return createHandle(com.bergerkiller.bukkit.common.conversion.Conversion.toWorldHandle.convert(world));
    }
    public abstract Random getRandom();
    public abstract void setRandom(Random value);
    public abstract WorldProviderHandle getWorldProvider();
    public abstract void setWorldProvider(WorldProviderHandle value);
    public abstract World getBukkitWorld();
    public abstract void setBukkitWorld(World value);
    public abstract boolean isKeepSpawnInMemory();
    public abstract void setKeepSpawnInMemory(boolean value);
    /**
     * Stores class members for <b>net.minecraft.server.World</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class WorldClass extends Template.Class<WorldHandle> {
        public final Template.Field<Random> random = new Template.Field<>();
        public final Template.Field.Converted<WorldProviderHandle> worldProvider = new Template.Field.Converted<>();
        @Template.Optional
        public final Template.Field.Converted<Object> navigationListener = new Template.Field.Converted<>();
        @Template.Optional
        public final Template.Field.Converted<Object> field_chunkProvider = new Template.Field.Converted<>();
        public final Template.Field.Converted<World> bukkitWorld = new Template.Field.Converted<>();
        public final Template.Field.Boolean keepSpawnInMemory = new Template.Field.Boolean();

        public final Template.Method<Void> method_profiler_begin = new Template.Method<>();
        public final Template.Method<Void> method_profiler_end = new Template.Method<>();
        public final Template.Method.Converted<World> getWorld = new Template.Method.Converted<>();
        public final Template.Method.Converted<Server> getServer = new Template.Method.Converted<>();
        public final Template.Method.Converted<BlockData> getBlockData = new Template.Method.Converted<>();
        public final Template.Method<BlockData> getBlockDataAtCoord = new Template.Method<>();
        public final Template.Method.Converted<Boolean> setBlockData = new Template.Method.Converted<>();
        public final Template.Method<Long> getTime = new Template.Method<>();
        @Template.Optional
        public final Template.Method.Converted<Object> getChunkProvider = new Template.Method.Converted<>();
        public final Template.Method.Converted<Boolean> isWithinWorldBorder = new Template.Method.Converted<>();
        @Template.Optional
        public final Template.Method.Converted<Boolean> getBlockCollisions = new Template.Method.Converted<>();
        public final Template.Method.Converted<Boolean> isNotCollidingWithBlocks = new Template.Method.Converted<>();
        @Template.Optional
        public final Template.Method.Converted<List<AxisAlignedBBHandle>> opt_getCubes_1_8 = new Template.Method.Converted<>();
        public final Template.Method.Converted<List<?>> getRawEntitiesOfType = new Template.Method.Converted<>();
        public final Template.Method.Converted<List<EntityHandle>> getNearbyEntities = new Template.Method.Converted<>();
        public final Template.Method.Converted<TileEntityHandle> getTileEntity = new Template.Method.Converted<>();
        public final Template.Method.Converted<WorldDataHandle> getWorldData = new Template.Method.Converted<>();
        @Template.Optional
        public final Template.Method<Void> makeSound = new Template.Method<>();
        public final Template.Method.Converted<Boolean> isBurnArea = new Template.Method.Converted<>();
        public final Template.Method.Converted<Entity> getEntityById = new Template.Method.Converted<>();
        public final Template.Method.Converted<Boolean> areChunksLoaded = new Template.Method.Converted<>();
        public final Template.Method.Converted<MovingObjectPositionHandle> rayTrace = new Template.Method.Converted<>();
        @Template.Optional
        public final Template.Method.Converted<Void> opt_applyPhysics = new Template.Method.Converted<>();
        @Template.Optional
        public final Template.Method.Converted<Void> opt_applyPhysics_old = new Template.Method.Converted<>();
        public final Template.Method.Converted<Void> applyBlockPhysics = new Template.Method.Converted<>();
        public final Template.Method<Void> setSectionSkyLight = new Template.Method<>();
        public final Template.Method<Void> setSectionBlockLight = new Template.Method<>();
        public final Template.Method<byte[]> getSectionSkyLight = new Template.Method<>();
        public final Template.Method<byte[]> getSectionBlockLight = new Template.Method<>();

    }

}

