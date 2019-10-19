package com.bergerkiller.generated.org.bukkit.craftbukkit.util;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import com.bergerkiller.generated.net.minecraft.server.IChatBaseComponentHandle;

/**
 * Instance wrapper handle for type <b>org.bukkit.craftbukkit.util.CraftChatMessage</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class CraftChatMessageHandle extends Template.Handle {
    /** @See {@link CraftChatMessageClass} */
    public static final CraftChatMessageClass T = new CraftChatMessageClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(CraftChatMessageHandle.class, "org.bukkit.craftbukkit.util.CraftChatMessage", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static CraftChatMessageHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public static String fromComponent(IChatBaseComponentHandle component) {
        return T.fromComponent.invoke(component);
    }

    public static IChatBaseComponentHandle[] fromString(String message) {
        return T.fromString.invoke(message);
    }

    /**
     * Stores class members for <b>org.bukkit.craftbukkit.util.CraftChatMessage</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class CraftChatMessageClass extends Template.Class<CraftChatMessageHandle> {
        public final Template.StaticMethod.Converted<String> fromComponent = new Template.StaticMethod.Converted<>();
        public final Template.StaticMethod.Converted<IChatBaseComponentHandle[]> fromString = new Template.StaticMethod.Converted<>();

    }

}

