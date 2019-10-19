package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayOutEntity</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class PacketPlayOutEntityHandle extends PacketHandle {
    /** @See {@link PacketPlayOutEntityClass} */
    public static final PacketPlayOutEntityClass T = new PacketPlayOutEntityClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(PacketPlayOutEntityHandle.class, "net.minecraft.server.PacketPlayOutEntity", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static PacketPlayOutEntityHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public abstract double getDeltaX();
    public abstract double getDeltaY();
    public abstract double getDeltaZ();
    public abstract void setDeltaX(double dx);
    public abstract void setDeltaY(double dy);
    public abstract void setDeltaZ(double dz);
    public abstract float getYaw();
    public abstract float getPitch();
    public abstract void setYaw(float yaw);
    public abstract void setPitch(float pitch);


    @Deprecated
    public float getDeltaYaw() {
        return getYaw();
    }


    @Deprecated
    public float getDeltaPitch() {
        return getPitch();
    }


    @Deprecated
    public void setDeltaYaw(float deltaYaw) {
        setYaw(deltaYaw);
    }


    @Deprecated
    public void setDeltaPitch(float deltaPitch) {
        setPitch(deltaPitch);
    }
    public abstract int getEntityId();
    public abstract void setEntityId(int value);
    public abstract boolean isOnGround();
    public abstract void setOnGround(boolean value);
    /**
     * Stores class members for <b>net.minecraft.server.PacketPlayOutEntity</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class PacketPlayOutEntityClass extends Template.Class<PacketPlayOutEntityHandle> {
        public final Template.Field.Integer entityId = new Template.Field.Integer();
        public final Template.Field.Boolean onGround = new Template.Field.Boolean();

        public final Template.Method<Double> getDeltaX = new Template.Method<>();
        public final Template.Method<Double> getDeltaY = new Template.Method<>();
        public final Template.Method<Double> getDeltaZ = new Template.Method<>();
        public final Template.Method<Void> setDeltaX = new Template.Method<>();
        public final Template.Method<Void> setDeltaY = new Template.Method<>();
        public final Template.Method<Void> setDeltaZ = new Template.Method<>();
        public final Template.Method<Float> getYaw = new Template.Method<>();
        public final Template.Method<Float> getPitch = new Template.Method<>();
        public final Template.Method<Void> setYaw = new Template.Method<>();
        public final Template.Method<Void> setPitch = new Template.Method<>();

    }


    /**
     * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayOutEntity.PacketPlayOutEntityLook</b>.
     * To access members without creating a handle type, use the static {@link #T} member.
     * New handles can be created from raw instances using {@link #createHandle(Object)}.
     */
    public abstract static class PacketPlayOutEntityLookHandle extends PacketPlayOutEntityHandle {
        /** @See {@link PacketPlayOutEntityLookClass} */
        public static final PacketPlayOutEntityLookClass T = new PacketPlayOutEntityLookClass();
        static final StaticInitHelper _init_helper = new StaticInitHelper(PacketPlayOutEntityLookHandle.class, "net.minecraft.server.PacketPlayOutEntity.PacketPlayOutEntityLook", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

        /* ============================================================================== */

        public static PacketPlayOutEntityLookHandle createHandle(Object handleInstance) {
            return T.createHandle(handleInstance);
        }

        public static final PacketPlayOutEntityLookHandle createNew() {
            return T.constr.newInstance();
        }

        /* ============================================================================== */


        @Override
        public com.bergerkiller.bukkit.common.protocol.PacketType getPacketType() {
            return com.bergerkiller.bukkit.common.protocol.PacketType.OUT_ENTITY_LOOK;
        }

        public static PacketPlayOutEntityHandle.PacketPlayOutEntityLookHandle createNew(int entityId, float yaw, float pitch, boolean onGround) {
            PacketPlayOutEntityHandle.PacketPlayOutEntityLookHandle handle = createNew();
            handle.setEntityId(entityId);
            handle.setYaw(yaw);
            handle.setPitch(pitch);
            handle.setOnGround(onGround);
            return handle;
        }
        /**
         * Stores class members for <b>net.minecraft.server.PacketPlayOutEntity.PacketPlayOutEntityLook</b>.
         * Methods, fields, and constructors can be used without using Handle Objects.
         */
        public static final class PacketPlayOutEntityLookClass extends Template.Class<PacketPlayOutEntityLookHandle> {
            public final Template.Constructor.Converted<PacketPlayOutEntityLookHandle> constr = new Template.Constructor.Converted<>();

        }

    }


    /**
     * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayOutEntity.PacketPlayOutRelEntityMove</b>.
     * To access members without creating a handle type, use the static {@link #T} member.
     * New handles can be created from raw instances using {@link #createHandle(Object)}.
     */
    public abstract static class PacketPlayOutRelEntityMoveHandle extends PacketPlayOutEntityHandle {
        /** @See {@link PacketPlayOutRelEntityMoveClass} */
        public static final PacketPlayOutRelEntityMoveClass T = new PacketPlayOutRelEntityMoveClass();
        static final StaticInitHelper _init_helper = new StaticInitHelper(PacketPlayOutRelEntityMoveHandle.class, "net.minecraft.server.PacketPlayOutEntity.PacketPlayOutRelEntityMove", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

        /* ============================================================================== */

        public static PacketPlayOutRelEntityMoveHandle createHandle(Object handleInstance) {
            return T.createHandle(handleInstance);
        }

        public static final PacketPlayOutRelEntityMoveHandle createNew() {
            return T.constr.newInstance();
        }

        /* ============================================================================== */


        @Override
        public com.bergerkiller.bukkit.common.protocol.PacketType getPacketType() {
            return com.bergerkiller.bukkit.common.protocol.PacketType.OUT_ENTITY_MOVE;
        }

        public static PacketPlayOutEntityHandle.PacketPlayOutRelEntityMoveHandle createNew(int entityId, double dx, double dy, double dz, boolean onGround) {
            PacketPlayOutEntityHandle.PacketPlayOutRelEntityMoveHandle handle = createNew();
            handle.setEntityId(entityId);
            handle.setDeltaX(dx);
            handle.setDeltaY(dy);
            handle.setDeltaZ(dz);
            handle.setOnGround(onGround);
            return handle;
        }
        /**
         * Stores class members for <b>net.minecraft.server.PacketPlayOutEntity.PacketPlayOutRelEntityMove</b>.
         * Methods, fields, and constructors can be used without using Handle Objects.
         */
        public static final class PacketPlayOutRelEntityMoveClass extends Template.Class<PacketPlayOutRelEntityMoveHandle> {
            public final Template.Constructor.Converted<PacketPlayOutRelEntityMoveHandle> constr = new Template.Constructor.Converted<>();

        }

    }


    /**
     * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook</b>.
     * To access members without creating a handle type, use the static {@link #T} member.
     * New handles can be created from raw instances using {@link #createHandle(Object)}.
     */
    public abstract static class PacketPlayOutRelEntityMoveLookHandle extends PacketPlayOutEntityHandle {
        /** @See {@link PacketPlayOutRelEntityMoveLookClass} */
        public static final PacketPlayOutRelEntityMoveLookClass T = new PacketPlayOutRelEntityMoveLookClass();
        static final StaticInitHelper _init_helper = new StaticInitHelper(PacketPlayOutRelEntityMoveLookHandle.class, "net.minecraft.server.PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

        /* ============================================================================== */

        public static PacketPlayOutRelEntityMoveLookHandle createHandle(Object handleInstance) {
            return T.createHandle(handleInstance);
        }

        public static final PacketPlayOutRelEntityMoveLookHandle createNew() {
            return T.constr.newInstance();
        }

        /* ============================================================================== */


        @Override
        public com.bergerkiller.bukkit.common.protocol.PacketType getPacketType() {
            return com.bergerkiller.bukkit.common.protocol.PacketType.OUT_ENTITY_MOVE_LOOK;
        }

        public static PacketPlayOutEntityHandle.PacketPlayOutRelEntityMoveLookHandle createNew(int entityId, double dx, double dy, double dz, float yaw, float pitch, boolean onGround) {
            PacketPlayOutEntityHandle.PacketPlayOutRelEntityMoveLookHandle handle = createNew();
            handle.setEntityId(entityId);
            handle.setDeltaX(dx);
            handle.setDeltaY(dy);
            handle.setDeltaZ(dz);
            handle.setYaw(yaw);
            handle.setPitch(pitch);
            handle.setOnGround(onGround);
            return handle;
        }
        /**
         * Stores class members for <b>net.minecraft.server.PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook</b>.
         * Methods, fields, and constructors can be used without using Handle Objects.
         */
        public static final class PacketPlayOutRelEntityMoveLookClass extends Template.Class<PacketPlayOutRelEntityMoveLookHandle> {
            public final Template.Constructor.Converted<PacketPlayOutRelEntityMoveLookHandle> constr = new Template.Constructor.Converted<>();

        }

    }

}

