package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import java.util.List;
import java.util.Map;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.EntityTypes</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class EntityTypesHandle extends Template.Handle {
    /** @See {@link EntityTypesClass} */
    public static final EntityTypesClass T = new EntityTypesClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(EntityTypesHandle.class, "net.minecraft.server.EntityTypes", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static EntityTypesHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public static Class<?> getEntityClass(String internalEntityName) {
        return T.getEntityClass.invoke(internalEntityName);
    }

    public static String getEntityInternalName(Class<?> entityType) {
        return T.getEntityInternalName.invoke(entityType);
    }

    public static int getEntityTypeId(Class<?> entityType) {
        return T.getEntityTypeId.invoke(entityType);
    }

    /**
     * Stores class members for <b>net.minecraft.server.EntityTypes</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class EntityTypesClass extends Template.Class<EntityTypesHandle> {
        @Template.Optional
        public final Template.StaticField.Converted<Map<String, Class<?>>> opt_nameTypeMap_1_10_2 = new Template.StaticField.Converted<>();
        @Template.Optional
        public final Template.StaticField.Converted<Map<Class<?>, String>> opt_typeNameMap_1_10_2 = new Template.StaticField.Converted<>();
        @Template.Optional
        public final Template.StaticField<List<String>> opt_typeIdToName_1_11 = new Template.StaticField<>();
        @Template.Optional
        public final Template.StaticField.Converted<Map<Class<?>, Integer>> opt_typeIdMap_1_8 = new Template.StaticField.Converted<>();

        @Template.Optional
        public final Template.StaticMethod.Converted<RegistryMaterialsHandle> opt_getRegistry = new Template.StaticMethod.Converted<>();
        @Template.Optional
        public final Template.StaticMethod.Converted<EntityTypesHandle> fromEntityClass = new Template.StaticMethod.Converted<>();
        public final Template.StaticMethod<Class<?>> getEntityClass = new Template.StaticMethod<>();
        public final Template.StaticMethod<String> getEntityInternalName = new Template.StaticMethod<>();
        public final Template.StaticMethod<Integer> getEntityTypeId = new Template.StaticMethod<>();

        @Template.Optional
        public final Template.Method.Converted<Class<?>> getEntityClassInst = new Template.Method.Converted<>();
        @Template.Optional
        public final Template.Method<Integer> getTypeId = new Template.Method<>();

    }

}

