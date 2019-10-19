package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.TileEntityHopper</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class TileEntityHopperHandle extends TileEntityHandle {
    /** @See {@link TileEntityHopperClass} */
    public static final TileEntityHopperClass T = new TileEntityHopperClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(TileEntityHopperHandle.class, "net.minecraft.server.TileEntityHopper", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static TileEntityHopperHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public static boolean suckItems(Object ihopper) {
        return T.suckItems.invoke(ihopper);
    }

    /**
     * Stores class members for <b>net.minecraft.server.TileEntityHopper</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class TileEntityHopperClass extends Template.Class<TileEntityHopperHandle> {
        public final Template.StaticMethod.Converted<Boolean> suckItems = new Template.StaticMethod.Converted<>();

    }

}

