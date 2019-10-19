package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import com.bergerkiller.bukkit.common.bases.IntVector3;
import com.bergerkiller.bukkit.common.wrappers.HumanHand;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.HumanEntity;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayInUseItem</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class PacketPlayInUseItemHandle extends PacketHandle {
    /** @See {@link PacketPlayInUseItemClass} */
    public static final PacketPlayInUseItemClass T = new PacketPlayInUseItemClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(PacketPlayInUseItemHandle.class, "net.minecraft.server.PacketPlayInUseItem", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static PacketPlayInUseItemHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public abstract HumanHand getHand(HumanEntity humanEntity);
    public abstract void setHand(HumanEntity humanEntity, HumanHand hand);
    public abstract BlockFace getDirection();
    public abstract void setDirection(BlockFace direction);
    public abstract boolean isBlockPlacePacket();
    public abstract void setBlockPlacePacket();
    public abstract IntVector3 getPosition();
    public abstract void setPosition(IntVector3 blockPosition);
    public abstract float getDeltaX();
    public abstract float getDeltaY();
    public abstract float getDeltaZ();
    public abstract void setDeltaX(float dx);
    public abstract void setDeltaY(float dy);
    public abstract void setDeltaZ(float dz);

    @Override
    public com.bergerkiller.bukkit.common.protocol.PacketType getPacketType() {
        return com.bergerkiller.bukkit.common.protocol.PacketType.IN_USE_ITEM;
    }

    public void setTimestamp(long timestamp) {
        if (T.timestamp.isAvailable()) {
            T.timestamp.setLong(getRaw(), timestamp);
        }
    }
    /**
     * Stores class members for <b>net.minecraft.server.PacketPlayInUseItem</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class PacketPlayInUseItemClass extends Template.Class<PacketPlayInUseItemHandle> {
        @Template.Optional
        public final Template.Field.Long timestamp = new Template.Field.Long();

        public final Template.Method<HumanHand> getHand = new Template.Method<>();
        public final Template.Method<Void> setHand = new Template.Method<>();
        public final Template.Method.Converted<BlockFace> getDirection = new Template.Method.Converted<>();
        public final Template.Method.Converted<Void> setDirection = new Template.Method.Converted<>();
        public final Template.Method<Boolean> isBlockPlacePacket = new Template.Method<>();
        public final Template.Method<Void> setBlockPlacePacket = new Template.Method<>();
        public final Template.Method.Converted<IntVector3> getPosition = new Template.Method.Converted<>();
        public final Template.Method.Converted<Void> setPosition = new Template.Method.Converted<>();
        public final Template.Method<Float> getDeltaX = new Template.Method<>();
        public final Template.Method<Float> getDeltaY = new Template.Method<>();
        public final Template.Method<Float> getDeltaZ = new Template.Method<>();
        public final Template.Method<Void> setDeltaX = new Template.Method<>();
        public final Template.Method<Void> setDeltaY = new Template.Method<>();
        public final Template.Method<Void> setDeltaZ = new Template.Method<>();
        @Template.Optional
        public final Template.Method<Void> initMovingObject = new Template.Method<>();

    }

}

