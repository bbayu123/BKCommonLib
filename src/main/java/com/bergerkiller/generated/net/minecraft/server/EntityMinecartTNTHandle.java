package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.EntityMinecartTNT</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class EntityMinecartTNTHandle extends EntityMinecartAbstractHandle {
    /** @See {@link EntityMinecartTNTClass} */
    public static final EntityMinecartTNTClass T = new EntityMinecartTNTClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(EntityMinecartTNTHandle.class, "net.minecraft.server.EntityMinecartTNT", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static EntityMinecartTNTHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public abstract void explode(double damage);
    public abstract void prime();
    public abstract int getFuse();
    public abstract void setFuse(int value);
    /**
     * Stores class members for <b>net.minecraft.server.EntityMinecartTNT</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class EntityMinecartTNTClass extends Template.Class<EntityMinecartTNTHandle> {
        public final Template.Field.Integer fuse = new Template.Field.Integer();

        public final Template.Method<Void> explode = new Template.Method<>();
        public final Template.Method<Void> prime = new Template.Method<>();

    }

}

