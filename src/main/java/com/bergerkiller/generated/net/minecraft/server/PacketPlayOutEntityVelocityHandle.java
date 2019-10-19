package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import org.bukkit.entity.Entity;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayOutEntityVelocity</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class PacketPlayOutEntityVelocityHandle extends PacketHandle {
    /** @See {@link PacketPlayOutEntityVelocityClass} */
    public static final PacketPlayOutEntityVelocityClass T = new PacketPlayOutEntityVelocityClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(PacketPlayOutEntityVelocityHandle.class, "net.minecraft.server.PacketPlayOutEntityVelocity", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static PacketPlayOutEntityVelocityHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    public static final PacketPlayOutEntityVelocityHandle createNew(Entity entity) {
        return T.constr_entity.newInstance(entity);
    }

    /* ============================================================================== */

    public static PacketPlayOutEntityVelocityHandle createNew(int entityId, double motX, double motY, double motZ) {
        return T.createNew.invoke(entityId, motX, motY, motZ);
    }


    @Override
    public com.bergerkiller.bukkit.common.protocol.PacketType getPacketType() {
        return com.bergerkiller.bukkit.common.protocol.PacketType.OUT_ENTITY_VELOCITY;
    }

    public double getMotX() {
        return deserializeVelocity(T.motX_raw.getInteger(getRaw()));
    }

    public double getMotY() {
        return deserializeVelocity(T.motY_raw.getInteger(getRaw()));
    }

    public double getMotZ() {
        return deserializeVelocity(T.motZ_raw.getInteger(getRaw()));
    }

    public void setMotX(double motX) {
        T.motX_raw.setInteger(getRaw(), serializeVelocity(motX));
    }

    public void setMotY(double motY) {
        T.motY_raw.setInteger(getRaw(), serializeVelocity(motY));
    }

    public void setMotZ(double motZ) {
        T.motZ_raw.setInteger(getRaw(), serializeVelocity(motZ));
    }
    public abstract int getEntityId();
    public abstract void setEntityId(int value);
    public abstract int getMotX_raw();
    public abstract void setMotX_raw(int value);
    public abstract int getMotY_raw();
    public abstract void setMotY_raw(int value);
    public abstract int getMotZ_raw();
    public abstract void setMotZ_raw(int value);
    /**
     * Stores class members for <b>net.minecraft.server.PacketPlayOutEntityVelocity</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class PacketPlayOutEntityVelocityClass extends Template.Class<PacketPlayOutEntityVelocityHandle> {
        public final Template.Constructor.Converted<PacketPlayOutEntityVelocityHandle> constr_entity = new Template.Constructor.Converted<>();

        public final Template.Field.Integer entityId = new Template.Field.Integer();
        public final Template.Field.Integer motX_raw = new Template.Field.Integer();
        public final Template.Field.Integer motY_raw = new Template.Field.Integer();
        public final Template.Field.Integer motZ_raw = new Template.Field.Integer();

        public final Template.StaticMethod.Converted<PacketPlayOutEntityVelocityHandle> createNew = new Template.StaticMethod.Converted<>();

    }

}

