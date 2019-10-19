package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.Packet</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class PacketHandle extends Template.Handle {
    /** @See {@link PacketClass} */
    public static final PacketClass T = new PacketClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(PacketHandle.class, "net.minecraft.server.Packet", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static PacketHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */


    public com.bergerkiller.bukkit.common.protocol.CommonPacket toCommonPacket() {
        return new com.bergerkiller.bukkit.common.protocol.CommonPacket(getRaw(), getPacketType());
    }

    public com.bergerkiller.bukkit.common.protocol.PacketType getPacketType() {
        return com.bergerkiller.bukkit.common.protocol.PacketType.getType(getRaw());
    }


    protected static final int serializeVelocity(double velocity) {
        return (int) (com.bergerkiller.bukkit.common.utils.MathUtil.clamp(velocity, 3.9) * 8000.0);
    }

    protected static final double deserializeVelocity(int protVelocity) {
        return protVelocity / 8000.0;
    }

    protected static final int serializePosition_1_8_8(double position) {
        return com.bergerkiller.bukkit.common.utils.MathUtil.floor(position * 32.0);
    }

    protected static final double deserializePosition_1_8_8(int protPosition) {
        return protPosition / 32.0;
    }

    protected static final int serializePosition_1_10_2(double position) {
        return com.bergerkiller.bukkit.common.utils.MathUtil.floor(position * 4096.0);
    }

    protected static final double deserializePosition_1_10_2(int protPosition) {
        return protPosition / 4096.0;
    }

    protected static final int serializeRotation(float rotation) {
        return com.bergerkiller.bukkit.common.utils.MathUtil.floor(rotation * 256.0f / 360.0f);
    }

    protected static final float deserializeRotation(int protRotation) {
        return protRotation * 360.0f / 256.0f;
    }

    protected final double getProtocolPosition(Template.Field.Byte field_1_8_8, Template.Field.Integer field_1_10_2) {
        if (field_1_10_2.isAvailable()) {
            return deserializePosition_1_10_2(field_1_10_2.getInteger(getRaw()));
        } else {
            return deserializePosition_1_8_8(field_1_8_8.getByte(getRaw()));
        }
    }

    protected final void setProtocolPosition(Template.Field.Byte field_1_8_8, Template.Field.Integer field_1_10_2, double position) {
        if (field_1_10_2.isAvailable()) {
            field_1_10_2.setInteger(getRaw(), serializePosition_1_10_2(position));
        } else {
            field_1_8_8.setByte(getRaw(), (byte) serializePosition_1_8_8(position));
        }
    }

    protected final double getProtocolPosition(Template.Field.Integer field_1_8_8, Template.Field.Double field_1_10_2) {
        if (field_1_10_2.isAvailable()) {
            return field_1_10_2.getDouble(getRaw());
        } else {
            return deserializePosition_1_8_8(field_1_8_8.getInteger(getRaw()));
        }
    }

    protected final void setProtocolPosition(Template.Field.Integer field_1_8_8, Template.Field.Double field_1_10_2, double position) {
        if (field_1_10_2.isAvailable()) {
            field_1_10_2.setDouble(getRaw(), position);
        } else {
            field_1_8_8.setInteger(getRaw(), serializePosition_1_8_8(position));
        }
    }

    protected final float getProtocolRotation(Template.Field.Byte field) {
        return deserializeRotation(field.getByte(getRaw()));
    }

    protected final void setProtocolRotation(Template.Field.Byte field, float rotation) {
        field.setByte(getRaw(), (byte) serializeRotation(rotation));
    }

    protected final com.bergerkiller.bukkit.common.wrappers.HumanHand internalGetHand(Template.Field.Converted<Object> field, org.bukkit.entity.HumanEntity humanEntity) {
        if (field.isAvailable()) {
            return com.bergerkiller.bukkit.common.wrappers.HumanHand.fromNMSEnumHand(humanEntity, field.get(getRaw()));
        } else {
            return com.bergerkiller.bukkit.common.wrappers.HumanHand.RIGHT;
        }
    }

    protected final void internalSetHand(Template.Field.Converted<Object> field, org.bukkit.entity.HumanEntity humanEntity, com.bergerkiller.bukkit.common.wrappers.HumanHand hand) {
        if (field.isAvailable()) {
            field.set(getRaw(), com.bergerkiller.bukkit.common.wrappers.HumanHand.toNMSEnumHand(humanEntity, hand));
        }
    }
    /**
     * Stores class members for <b>net.minecraft.server.Packet</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class PacketClass extends Template.Class<PacketHandle> {
    }

}

