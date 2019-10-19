package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import com.bergerkiller.bukkit.common.wrappers.UseAction;
import org.bukkit.util.Vector;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayInUseEntity</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class PacketPlayInUseEntityHandle extends PacketHandle {
    /** @See {@link PacketPlayInUseEntityClass} */
    public static final PacketPlayInUseEntityClass T = new PacketPlayInUseEntityClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(PacketPlayInUseEntityHandle.class, "net.minecraft.server.PacketPlayInUseEntity", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static PacketPlayInUseEntityHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */


    public com.bergerkiller.bukkit.common.wrappers.HumanHand getHand(org.bukkit.entity.HumanEntity humanEntity) {
        return internalGetHand(T.enumHand, humanEntity);
    }

    public void setHand(org.bukkit.entity.HumanEntity humanEntity, com.bergerkiller.bukkit.common.wrappers.HumanHand hand) {
        internalSetHand(T.enumHand, humanEntity, hand);
    }
    public abstract int getUsedEntityId();
    public abstract void setUsedEntityId(int value);
    public abstract UseAction getAction();
    public abstract void setAction(UseAction value);
    public abstract Vector getOffset();
    public abstract void setOffset(Vector value);
    /**
     * Stores class members for <b>net.minecraft.server.PacketPlayInUseEntity</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class PacketPlayInUseEntityClass extends Template.Class<PacketPlayInUseEntityHandle> {
        public final Template.Field.Integer usedEntityId = new Template.Field.Integer();
        public final Template.Field.Converted<UseAction> action = new Template.Field.Converted<>();
        public final Template.Field.Converted<Vector> offset = new Template.Field.Converted<>();
        @Template.Optional
        public final Template.Field.Converted<Object> enumHand = new Template.Field.Converted<>();

    }


    /**
     * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayInUseEntity.EnumEntityUseAction</b>.
     * To access members without creating a handle type, use the static {@link #T} member.
     * New handles can be created from raw instances using {@link #createHandle(Object)}.
     */
    public abstract static class EnumEntityUseActionHandle extends Template.Handle {
        /** @See {@link EnumEntityUseActionClass} */
        public static final EnumEntityUseActionClass T = new EnumEntityUseActionClass();
        static final StaticInitHelper _init_helper = new StaticInitHelper(EnumEntityUseActionHandle.class, "net.minecraft.server.PacketPlayInUseEntity.EnumEntityUseAction", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

        public static final EnumEntityUseActionHandle INTERACT = T.INTERACT.getSafe();
        public static final EnumEntityUseActionHandle ATTACK = T.ATTACK.getSafe();
        public static final EnumEntityUseActionHandle INTERACT_AT = T.INTERACT_AT.getSafe();
        /* ============================================================================== */

        public static EnumEntityUseActionHandle createHandle(Object handleInstance) {
            return T.createHandle(handleInstance);
        }

        /* ============================================================================== */

        /**
         * Stores class members for <b>net.minecraft.server.PacketPlayInUseEntity.EnumEntityUseAction</b>.
         * Methods, fields, and constructors can be used without using Handle Objects.
         */
        public static final class EnumEntityUseActionClass extends Template.Class<EnumEntityUseActionHandle> {
            public final Template.EnumConstant.Converted<EnumEntityUseActionHandle> INTERACT = new Template.EnumConstant.Converted<>();
            public final Template.EnumConstant.Converted<EnumEntityUseActionHandle> ATTACK = new Template.EnumConstant.Converted<>();
            public final Template.EnumConstant.Converted<EnumEntityUseActionHandle> INTERACT_AT = new Template.EnumConstant.Converted<>();

        }

    }

}

