package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayInResourcePackStatus</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class PacketPlayInResourcePackStatusHandle extends PacketHandle {
    /** @See {@link PacketPlayInResourcePackStatusClass} */
    public static final PacketPlayInResourcePackStatusClass T = new PacketPlayInResourcePackStatusClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(PacketPlayInResourcePackStatusHandle.class, "net.minecraft.server.PacketPlayInResourcePackStatus", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static PacketPlayInResourcePackStatusHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public abstract Object getStatus();
    public abstract void setStatus(Object value);
    /**
     * Stores class members for <b>net.minecraft.server.PacketPlayInResourcePackStatus</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class PacketPlayInResourcePackStatusClass extends Template.Class<PacketPlayInResourcePackStatusHandle> {
        @Template.Optional
        public final Template.Field<String> message = new Template.Field<>();
        public final Template.Field.Converted<Object> status = new Template.Field.Converted<>();

    }

}

