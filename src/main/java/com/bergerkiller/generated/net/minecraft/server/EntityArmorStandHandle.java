package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import com.bergerkiller.bukkit.common.wrappers.DataWatcher.Key;
import org.bukkit.util.Vector;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.EntityArmorStand</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class EntityArmorStandHandle extends EntityLivingHandle {
    /** @See {@link EntityArmorStandClass} */
    public static final EntityArmorStandClass T = new EntityArmorStandClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(EntityArmorStandHandle.class, "net.minecraft.server.EntityArmorStand", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static EntityArmorStandHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */


    public static final Key<Byte> DATA_ARMORSTAND_FLAGS = Key.Type.BYTE.createKey(T.DATA_ARMORSTAND_FLAGS, 10);
    public static final Key<org.bukkit.util.Vector> DATA_POSE_HEAD = Key.Type.VECTOR.createKey(T.DATA_POSE_HEAD, 11);
    public static final Key<org.bukkit.util.Vector> DATA_POSE_BODY = Key.Type.VECTOR.createKey(T.DATA_POSE_BODY, 12);
    public static final Key<org.bukkit.util.Vector> DATA_POSE_ARM_LEFT = Key.Type.VECTOR.createKey(T.DATA_POSE_ARM_LEFT, 13);
    public static final Key<org.bukkit.util.Vector> DATA_POSE_ARM_RIGHT = Key.Type.VECTOR.createKey(T.DATA_POSE_ARM_RIGHT, 14);
    public static final Key<org.bukkit.util.Vector> DATA_POSE_LEG_LEFT = Key.Type.VECTOR.createKey(T.DATA_POSE_LEG_LEFT, 15);
    public static final Key<org.bukkit.util.Vector> DATA_POSE_LEG_RIGHT = Key.Type.VECTOR.createKey(T.DATA_POSE_LEG_RIGHT, 16);

    public static final int DATA_FLAG_IS_SMALL = (1 << 0);
    public static final int DATA_FLAG_HAS_ARMS = (1 << 2);
    public static final int DATA_FLAG_NO_BASEPLATE = (1 << 3);
    public static final int DATA_FLAG_SET_MARKER = (1 << 4);
    /**
     * Stores class members for <b>net.minecraft.server.EntityArmorStand</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class EntityArmorStandClass extends Template.Class<EntityArmorStandHandle> {
        @Template.Optional
        public final Template.StaticField.Converted<Key<Byte>> DATA_ARMORSTAND_FLAGS = new Template.StaticField.Converted<>();
        @Template.Optional
        public final Template.StaticField.Converted<Key<Vector>> DATA_POSE_HEAD = new Template.StaticField.Converted<>();
        @Template.Optional
        public final Template.StaticField.Converted<Key<Vector>> DATA_POSE_BODY = new Template.StaticField.Converted<>();
        @Template.Optional
        public final Template.StaticField.Converted<Key<Vector>> DATA_POSE_ARM_LEFT = new Template.StaticField.Converted<>();
        @Template.Optional
        public final Template.StaticField.Converted<Key<Vector>> DATA_POSE_ARM_RIGHT = new Template.StaticField.Converted<>();
        @Template.Optional
        public final Template.StaticField.Converted<Key<Vector>> DATA_POSE_LEG_LEFT = new Template.StaticField.Converted<>();
        @Template.Optional
        public final Template.StaticField.Converted<Key<Vector>> DATA_POSE_LEG_RIGHT = new Template.StaticField.Converted<>();

    }

}

