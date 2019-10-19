package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import org.bukkit.entity.Entity;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayOutEntityHeadRotation</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class PacketPlayOutEntityHeadRotationHandle extends PacketHandle {
    /** @See {@link PacketPlayOutEntityHeadRotationClass} */
    public static final PacketPlayOutEntityHeadRotationClass T = new PacketPlayOutEntityHeadRotationClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(PacketPlayOutEntityHeadRotationHandle.class, "net.minecraft.server.PacketPlayOutEntityHeadRotation", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static PacketPlayOutEntityHeadRotationHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    public static final PacketPlayOutEntityHeadRotationHandle createNew() {
        return T.constr.newInstance();
    }

    /* ============================================================================== */

    public static PacketPlayOutEntityHeadRotationHandle createNew(Entity entity, float headYaw) {
        return T.createNew.invoke(entity, headYaw);
    }

    public abstract float getHeadYaw();
    public abstract void setHeadYaw(float headYaw);

    public static PacketPlayOutEntityHeadRotationHandle createNew(int entityId, float headYaw) {
        PacketPlayOutEntityHeadRotationHandle packet = createNew();
        packet.setEntityId(entityId);
        packet.setHeadYaw(headYaw);
        return packet;
    }
    public abstract int getEntityId();
    public abstract void setEntityId(int value);
    /**
     * Stores class members for <b>net.minecraft.server.PacketPlayOutEntityHeadRotation</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class PacketPlayOutEntityHeadRotationClass extends Template.Class<PacketPlayOutEntityHeadRotationHandle> {
        public final Template.Constructor.Converted<PacketPlayOutEntityHeadRotationHandle> constr = new Template.Constructor.Converted<>();

        public final Template.Field.Integer entityId = new Template.Field.Integer();

        public final Template.StaticMethod.Converted<PacketPlayOutEntityHeadRotationHandle> createNew = new Template.StaticMethod.Converted<>();

        public final Template.Method<Float> getHeadYaw = new Template.Method<>();
        public final Template.Method<Void> setHeadYaw = new Template.Method<>();

    }

}

