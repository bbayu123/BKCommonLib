package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import com.bergerkiller.bukkit.common.bases.IntVector2;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import java.util.List;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.PlayerChunk</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class PlayerChunkHandle extends Template.Handle {
    /** @See {@link PlayerChunkClass} */
    public static final PlayerChunkClass T = new PlayerChunkClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(PlayerChunkHandle.class, "net.minecraft.server.PlayerChunk", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static PlayerChunkHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public abstract PlayerChunkMapHandle getPlayerChunkMap();
    public abstract void markAllLightDirty();
    public abstract List<Player> getPlayers();
    public abstract Chunk getChunkIfLoaded();
    public abstract IntVector2 getLocation();
    public abstract void setLocation(IntVector2 value);
    public abstract int getDirtyCount();
    public abstract void setDirtyCount(int value);
    public abstract int getDirtySectionMask();
    public abstract void setDirtySectionMask(int value);
    public abstract boolean isDone();
    public abstract void setDone(boolean value);
    /**
     * Stores class members for <b>net.minecraft.server.PlayerChunk</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class PlayerChunkClass extends Template.Class<PlayerChunkHandle> {
        public final Template.Field.Converted<IntVector2> location = new Template.Field.Converted<>();
        public final Template.Field.Integer dirtyCount = new Template.Field.Integer();
        public final Template.Field.Integer dirtySectionMask = new Template.Field.Integer();
        public final Template.Field.Boolean done = new Template.Field.Boolean();

        public final Template.Method.Converted<PlayerChunkMapHandle> getPlayerChunkMap = new Template.Method.Converted<>();
        public final Template.Method<Void> markAllLightDirty = new Template.Method<>();
        public final Template.Method.Converted<List<Player>> getPlayers = new Template.Method.Converted<>();
        public final Template.Method.Converted<Chunk> getChunkIfLoaded = new Template.Method.Converted<>();

    }

}

