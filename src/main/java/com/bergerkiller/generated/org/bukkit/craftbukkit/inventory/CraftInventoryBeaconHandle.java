package com.bergerkiller.generated.org.bukkit.craftbukkit.inventory;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import org.bukkit.inventory.BeaconInventory;

/**
 * Instance wrapper handle for type <b>org.bukkit.craftbukkit.inventory.CraftInventoryBeacon</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class CraftInventoryBeaconHandle extends Template.Handle {
    /** @See {@link CraftInventoryBeaconClass} */
    public static final CraftInventoryBeaconClass T = new CraftInventoryBeaconClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(CraftInventoryBeaconHandle.class, "org.bukkit.craftbukkit.inventory.CraftInventoryBeacon", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static CraftInventoryBeaconHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public static BeaconInventory createNew(Object nmsTileEntityBeacon) {
        return T.createNew.invoke(nmsTileEntityBeacon);
    }

    /**
     * Stores class members for <b>org.bukkit.craftbukkit.inventory.CraftInventoryBeacon</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class CraftInventoryBeaconClass extends Template.Class<CraftInventoryBeaconHandle> {
        public final Template.StaticMethod.Converted<BeaconInventory> createNew = new Template.StaticMethod.Converted<>();

    }

}

