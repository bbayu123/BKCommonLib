package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import com.bergerkiller.bukkit.common.wrappers.DataWatcher;
import com.bergerkiller.bukkit.common.wrappers.DataWatcher.Item;
import org.bukkit.Material;
import java.util.List;
import java.util.UUID;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayOutNamedEntitySpawn</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class PacketPlayOutNamedEntitySpawnHandle extends PacketHandle {
    /** @See {@link PacketPlayOutNamedEntitySpawnClass} */
    public static final PacketPlayOutNamedEntitySpawnClass T = new PacketPlayOutNamedEntitySpawnClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(PacketPlayOutNamedEntitySpawnHandle.class, "net.minecraft.server.PacketPlayOutNamedEntitySpawn", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static PacketPlayOutNamedEntitySpawnHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */


    @Override
    public com.bergerkiller.bukkit.common.protocol.PacketType getPacketType() {
        return com.bergerkiller.bukkit.common.protocol.PacketType.OUT_ENTITY_SPAWN_NAMED;
    }

    public double getPosX() {
        return getProtocolPosition(T.posX_1_8_8, T.posX_1_10_2);
    }

    public double getPosY() {
        return getProtocolPosition(T.posY_1_8_8, T.posY_1_10_2);
    }

    public double getPosZ() {
        return getProtocolPosition(T.posZ_1_8_8, T.posZ_1_10_2);
    }

    public void setPosX(double posX) {
        setProtocolPosition(T.posX_1_8_8, T.posX_1_10_2, posX);
    }

    public void setPosY(double posY) {
        setProtocolPosition(T.posY_1_8_8, T.posY_1_10_2, posY);
    }

    public void setPosZ(double posZ) {
        setProtocolPosition(T.posZ_1_8_8, T.posZ_1_10_2, posZ);
    }

    public float getYaw() {
        return getProtocolRotation(T.yaw_raw);
    }

    public float getPitch() {
        return getProtocolRotation(T.pitch_raw);
    }

    public void setYaw(float yaw) {
        setProtocolRotation(T.yaw_raw, yaw);
    }

    public void setPitch(float pitch) {
        setProtocolRotation(T.pitch_raw, pitch);
    }

    public void setHeldItem(Material type) {
        if (T.heldItem.isAvailable()) {
            T.heldItem.set(getRaw(), type);
        }
    }

    public org.bukkit.Material getHeldItem() {
        if (T.heldItem.isAvailable()) {
            return T.heldItem.get(getRaw());
        } else {
            return org.bukkit.Material.AIR;
        }
    }
    public abstract int getEntityId();
    public abstract void setEntityId(int value);
    public abstract UUID getEntityUUID();
    public abstract void setEntityUUID(UUID value);
    public abstract DataWatcher getDataWatcher();
    public abstract void setDataWatcher(DataWatcher value);
    public abstract List<Item<?>> getDataWatcherItems();
    public abstract void setDataWatcherItems(List<Item<?>> value);
    /**
     * Stores class members for <b>net.minecraft.server.PacketPlayOutNamedEntitySpawn</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class PacketPlayOutNamedEntitySpawnClass extends Template.Class<PacketPlayOutNamedEntitySpawnHandle> {
        public final Template.Field.Integer entityId = new Template.Field.Integer();
        public final Template.Field<UUID> entityUUID = new Template.Field<>();
        @Template.Optional
        public final Template.Field.Integer posX_1_8_8 = new Template.Field.Integer();
        @Template.Optional
        public final Template.Field.Integer posY_1_8_8 = new Template.Field.Integer();
        @Template.Optional
        public final Template.Field.Integer posZ_1_8_8 = new Template.Field.Integer();
        @Template.Optional
        public final Template.Field.Double posX_1_10_2 = new Template.Field.Double();
        @Template.Optional
        public final Template.Field.Double posY_1_10_2 = new Template.Field.Double();
        @Template.Optional
        public final Template.Field.Double posZ_1_10_2 = new Template.Field.Double();
        @Template.Optional
        public final Template.Field.Byte yaw_raw = new Template.Field.Byte();
        @Template.Optional
        public final Template.Field.Byte pitch_raw = new Template.Field.Byte();
        @Template.Optional
        public final Template.Field.Converted<Material> heldItem = new Template.Field.Converted<>();
        public final Template.Field.Converted<DataWatcher> dataWatcher = new Template.Field.Converted<>();
        public final Template.Field.Converted<List<Item<?>>> dataWatcherItems = new Template.Field.Converted<>();

    }

}

