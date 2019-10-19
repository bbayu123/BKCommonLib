package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.CrashReport</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class CrashReportHandle extends Template.Handle {
    /** @See {@link CrashReportClass} */
    public static final CrashReportClass T = new CrashReportClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(CrashReportHandle.class, "net.minecraft.server.CrashReport", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static CrashReportHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public static CrashReportHandle create(Throwable throwable, String message) {
        return T.create.invoke(throwable, message);
    }

    public abstract CrashReportSystemDetailsHandle getSystemDetails(String message);
    /**
     * Stores class members for <b>net.minecraft.server.CrashReport</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class CrashReportClass extends Template.Class<CrashReportHandle> {
        public final Template.StaticMethod.Converted<CrashReportHandle> create = new Template.StaticMethod.Converted<>();

        public final Template.Method.Converted<CrashReportSystemDetailsHandle> getSystemDetails = new Template.Method.Converted<>();

    }

}

