package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import com.bergerkiller.bukkit.common.bases.IntVector3;
import com.bergerkiller.bukkit.common.wrappers.ChatText;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayInUpdateSign</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class PacketPlayInUpdateSignHandle extends PacketHandle {
    /** @See {@link PacketPlayInUpdateSignClass} */
    public static final PacketPlayInUpdateSignClass T = new PacketPlayInUpdateSignClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(PacketPlayInUpdateSignHandle.class, "net.minecraft.server.PacketPlayInUpdateSign", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static PacketPlayInUpdateSignHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public abstract IntVector3 getPosition();
    public abstract void setPosition(IntVector3 value);
    public abstract ChatText[] getLines();
    public abstract void setLines(ChatText[] value);
    /**
     * Stores class members for <b>net.minecraft.server.PacketPlayInUpdateSign</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class PacketPlayInUpdateSignClass extends Template.Class<PacketPlayInUpdateSignHandle> {
        public final Template.Field.Converted<IntVector3> position = new Template.Field.Converted<>();
        public final Template.Field.Converted<ChatText[]> lines = new Template.Field.Converted<>();

    }

}

