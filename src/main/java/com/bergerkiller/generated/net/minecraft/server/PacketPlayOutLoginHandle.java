package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import com.bergerkiller.bukkit.common.wrappers.Dimension;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.WorldType;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayOutLogin</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class PacketPlayOutLoginHandle extends PacketHandle {
    /** @See {@link PacketPlayOutLoginClass} */
    public static final PacketPlayOutLoginClass T = new PacketPlayOutLoginClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(PacketPlayOutLoginHandle.class, "net.minecraft.server.PacketPlayOutLogin", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static PacketPlayOutLoginHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public abstract int getPlayerId();
    public abstract void setPlayerId(int value);
    public abstract boolean isHardcore();
    public abstract void setHardcore(boolean value);
    public abstract GameMode getGameMode();
    public abstract void setGameMode(GameMode value);
    public abstract Dimension getDimension();
    public abstract void setDimension(Dimension value);
    public abstract int getMaxPlayers();
    public abstract void setMaxPlayers(int value);
    public abstract WorldType getWorldType();
    public abstract void setWorldType(WorldType value);
    public abstract boolean isReducedDebugInfo();
    public abstract void setReducedDebugInfo(boolean value);
    /**
     * Stores class members for <b>net.minecraft.server.PacketPlayOutLogin</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class PacketPlayOutLoginClass extends Template.Class<PacketPlayOutLoginHandle> {
        public final Template.Field.Integer playerId = new Template.Field.Integer();
        public final Template.Field.Boolean hardcore = new Template.Field.Boolean();
        public final Template.Field.Converted<GameMode> gameMode = new Template.Field.Converted<>();
        public final Template.Field.Converted<Dimension> dimension = new Template.Field.Converted<>();
        @Template.Optional
        public final Template.Field.Converted<Difficulty> difficulty = new Template.Field.Converted<>();
        public final Template.Field.Integer maxPlayers = new Template.Field.Integer();
        public final Template.Field.Converted<WorldType> worldType = new Template.Field.Converted<>();
        @Template.Optional
        public final Template.Field.Integer viewDistance = new Template.Field.Integer();
        public final Template.Field.Boolean reducedDebugInfo = new Template.Field.Boolean();

    }

}

