package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.AttributeInstance</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class AttributeInstanceHandle extends Template.Handle {
    /** @See {@link AttributeInstanceClass} */
    public static final AttributeInstanceClass T = new AttributeInstanceClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(AttributeInstanceHandle.class, "net.minecraft.server.AttributeInstance", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static AttributeInstanceHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public abstract void setValue(double value);
    public abstract double getValue();
    /**
     * Stores class members for <b>net.minecraft.server.AttributeInstance</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class AttributeInstanceClass extends Template.Class<AttributeInstanceHandle> {
        public final Template.Method<Void> setValue = new Template.Method<>();
        public final Template.Method<Double> getValue = new Template.Method<>();

    }

}

