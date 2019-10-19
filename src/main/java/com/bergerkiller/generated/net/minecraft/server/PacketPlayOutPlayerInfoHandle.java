package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import com.bergerkiller.bukkit.common.wrappers.ChatText;
import com.bergerkiller.generated.com.mojang.authlib.GameProfileHandle;
import org.bukkit.GameMode;
import java.util.List;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayOutPlayerInfo</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class PacketPlayOutPlayerInfoHandle extends PacketHandle {
    /** @See {@link PacketPlayOutPlayerInfoClass} */
    public static final PacketPlayOutPlayerInfoClass T = new PacketPlayOutPlayerInfoClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(PacketPlayOutPlayerInfoHandle.class, "net.minecraft.server.PacketPlayOutPlayerInfo", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static PacketPlayOutPlayerInfoHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    public static final PacketPlayOutPlayerInfoHandle createNew() {
        return T.constr.newInstance();
    }

    /* ============================================================================== */

    public abstract EnumPlayerInfoActionHandle getAction();
    public abstract void setAction(EnumPlayerInfoActionHandle value);
    public abstract List<PlayerInfoDataHandle> getPlayers();
    public abstract void setPlayers(List<PlayerInfoDataHandle> value);
    /**
     * Stores class members for <b>net.minecraft.server.PacketPlayOutPlayerInfo</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class PacketPlayOutPlayerInfoClass extends Template.Class<PacketPlayOutPlayerInfoHandle> {
        public final Template.Constructor.Converted<PacketPlayOutPlayerInfoHandle> constr = new Template.Constructor.Converted<>();

        public final Template.Field.Converted<EnumPlayerInfoActionHandle> action = new Template.Field.Converted<>();
        public final Template.Field.Converted<List<PlayerInfoDataHandle>> players = new Template.Field.Converted<>();

    }


    /**
     * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayOutPlayerInfo.PlayerInfoData</b>.
     * To access members without creating a handle type, use the static {@link #T} member.
     * New handles can be created from raw instances using {@link #createHandle(Object)}.
     */
    public abstract static class PlayerInfoDataHandle extends Template.Handle {
        /** @See {@link PlayerInfoDataClass} */
        public static final PlayerInfoDataClass T = new PlayerInfoDataClass();
        static final StaticInitHelper _init_helper = new StaticInitHelper(PlayerInfoDataHandle.class, "net.minecraft.server.PacketPlayOutPlayerInfo.PlayerInfoData", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

        /* ============================================================================== */

        public static PlayerInfoDataHandle createHandle(Object handleInstance) {
            return T.createHandle(handleInstance);
        }

        public static final PlayerInfoDataHandle createNew(PacketPlayOutPlayerInfoHandle packet, GameProfileHandle profile, int ping, GameMode gameMode, ChatText listName) {
            return T.constr_packet_profile_ping_gameMode_listName.newInstance(packet, profile, ping, gameMode, listName);
        }

        /* ============================================================================== */

        public abstract GameProfileHandle getProfile();
        public abstract int getPing();
        public abstract GameMode getGameMode();
        public abstract ChatText getListName();
        /**
         * Stores class members for <b>net.minecraft.server.PacketPlayOutPlayerInfo.PlayerInfoData</b>.
         * Methods, fields, and constructors can be used without using Handle Objects.
         */
        public static final class PlayerInfoDataClass extends Template.Class<PlayerInfoDataHandle> {
            public final Template.Constructor.Converted<PlayerInfoDataHandle> constr_packet_profile_ping_gameMode_listName = new Template.Constructor.Converted<>();

            public final Template.Method.Converted<GameProfileHandle> getProfile = new Template.Method.Converted<>();
            public final Template.Method<Integer> getPing = new Template.Method<>();
            public final Template.Method.Converted<GameMode> getGameMode = new Template.Method.Converted<>();
            public final Template.Method.Converted<ChatText> getListName = new Template.Method.Converted<>();

        }

    }


    /**
     * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayOutPlayerInfo.EnumPlayerInfoAction</b>.
     * To access members without creating a handle type, use the static {@link #T} member.
     * New handles can be created from raw instances using {@link #createHandle(Object)}.
     */
    public abstract static class EnumPlayerInfoActionHandle extends Template.Handle {
        /** @See {@link EnumPlayerInfoActionClass} */
        public static final EnumPlayerInfoActionClass T = new EnumPlayerInfoActionClass();
        static final StaticInitHelper _init_helper = new StaticInitHelper(EnumPlayerInfoActionHandle.class, "net.minecraft.server.PacketPlayOutPlayerInfo.EnumPlayerInfoAction", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

        public static final EnumPlayerInfoActionHandle ADD_PLAYER = T.ADD_PLAYER.getSafe();
        public static final EnumPlayerInfoActionHandle UPDATE_GAME_MODE = T.UPDATE_GAME_MODE.getSafe();
        public static final EnumPlayerInfoActionHandle UPDATE_LATENCY = T.UPDATE_LATENCY.getSafe();
        public static final EnumPlayerInfoActionHandle UPDATE_DISPLAY_NAME = T.UPDATE_DISPLAY_NAME.getSafe();
        public static final EnumPlayerInfoActionHandle REMOVE_PLAYER = T.REMOVE_PLAYER.getSafe();
        /* ============================================================================== */

        public static EnumPlayerInfoActionHandle createHandle(Object handleInstance) {
            return T.createHandle(handleInstance);
        }

        /* ============================================================================== */

        /**
         * Stores class members for <b>net.minecraft.server.PacketPlayOutPlayerInfo.EnumPlayerInfoAction</b>.
         * Methods, fields, and constructors can be used without using Handle Objects.
         */
        public static final class EnumPlayerInfoActionClass extends Template.Class<EnumPlayerInfoActionHandle> {
            public final Template.EnumConstant.Converted<EnumPlayerInfoActionHandle> ADD_PLAYER = new Template.EnumConstant.Converted<>();
            public final Template.EnumConstant.Converted<EnumPlayerInfoActionHandle> UPDATE_GAME_MODE = new Template.EnumConstant.Converted<>();
            public final Template.EnumConstant.Converted<EnumPlayerInfoActionHandle> UPDATE_LATENCY = new Template.EnumConstant.Converted<>();
            public final Template.EnumConstant.Converted<EnumPlayerInfoActionHandle> UPDATE_DISPLAY_NAME = new Template.EnumConstant.Converted<>();
            public final Template.EnumConstant.Converted<EnumPlayerInfoActionHandle> REMOVE_PLAYER = new Template.EnumConstant.Converted<>();

        }

    }

}

