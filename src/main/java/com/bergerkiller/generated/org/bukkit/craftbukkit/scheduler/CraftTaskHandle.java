package com.bergerkiller.generated.org.bukkit.craftbukkit.scheduler;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import org.bukkit.plugin.Plugin;

/**
 * Instance wrapper handle for type <b>org.bukkit.craftbukkit.scheduler.CraftTask</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class CraftTaskHandle extends Template.Handle {
    /** @See {@link CraftTaskClass} */
    public static final CraftTaskClass T = new CraftTaskClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(CraftTaskHandle.class, "org.bukkit.craftbukkit.scheduler.CraftTask", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static CraftTaskHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public abstract Runnable getTask();
    public abstract void setTask(Runnable value);
    public abstract Plugin getPlugin();
    public abstract void setPlugin(Plugin value);
    /**
     * Stores class members for <b>org.bukkit.craftbukkit.scheduler.CraftTask</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class CraftTaskClass extends Template.Class<CraftTaskHandle> {
        public final Template.Field<Runnable> task = new Template.Field<>();
        public final Template.Field<Plugin> plugin = new Template.Field<>();

    }

}

