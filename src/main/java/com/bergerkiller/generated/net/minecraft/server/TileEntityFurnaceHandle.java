package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.TileEntityFurnace</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class TileEntityFurnaceHandle extends TileEntityHandle {
    /** @See {@link TileEntityFurnaceClass} */
    public static final TileEntityFurnaceClass T = new TileEntityFurnaceClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(TileEntityFurnaceHandle.class, "net.minecraft.server.TileEntityFurnace", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static TileEntityFurnaceHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public static int fuelTime(ItemStackHandle itemstack) {
        return T.fuelTime.invoke(itemstack);
    }

    /**
     * Stores class members for <b>net.minecraft.server.TileEntityFurnace</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class TileEntityFurnaceClass extends Template.Class<TileEntityFurnaceHandle> {
        public final Template.StaticMethod.Converted<Integer> fuelTime = new Template.StaticMethod.Converted<>();

    }

}

