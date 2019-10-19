package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import com.bergerkiller.bukkit.common.nbt.CommonTagCompound;
import java.util.List;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayOutMapChunk</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class PacketPlayOutMapChunkHandle extends PacketHandle {
    /** @See {@link PacketPlayOutMapChunkClass} */
    public static final PacketPlayOutMapChunkClass T = new PacketPlayOutMapChunkClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(PacketPlayOutMapChunkHandle.class, "net.minecraft.server.PacketPlayOutMapChunk", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static PacketPlayOutMapChunkHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */


    public CommonTagCompound getMetadata() {
        if (T.metadata.isAvailable()) {
            return T.metadata.get(getRaw());
        } else {
            return new CommonTagCompound();
        }
    }

    public void setMetadata(CommonTagCompound metadata) {
        if (T.metadata.isAvailable()) {
            T.metadata.set(getRaw(), metadata);
        }
    }

    public int getSectionsMask() {
        if (T.sectionsMask.isAvailable()) {
            return T.sectionsMask.get(getRaw());
        } else {
            return T.chunkInfo.get(getRaw()).getSectionsMask();
        }
    }

    public void setSectionsMask(int sectionsMask) {
        if (T.sectionsMask.isAvailable()) {
            T.sectionsMask.set(getRaw(), sectionsMask);
        } else {
            T.chunkInfo.get(getRaw()).setSectionsMask(sectionsMask);
        }
    }

    public byte[] getData() {
        if (T.data.isAvailable()) {
            return T.data.get(getRaw());
        } else {
            return T.chunkInfo.get(getRaw()).getData();
        }
    }

    public void setData(byte[] data) {
        if (T.data.isAvailable()) {
            T.data.set(getRaw(), data);
        } else {
            T.chunkInfo.get(getRaw()).setData(data);
        }
    }

    public List<CommonTagCompound> getTags() {
        if (T.tags.isAvailable()) {
            return T.tags.get(getRaw());
        } else {
            return java.util.Collections.emptyList();
        }
    }

    public void setTags(List<CommonTagCompound> tags) {
        if (T.tags.isAvailable()) {
            T.tags.set(getRaw(), tags);
        } else {
        }
    }


    public static PacketPlayOutMapChunkHandle createNew(org.bukkit.Chunk chunk, int sectionsMask) {
        if (T.constr_chunk_sectionsMask.isAvailable()) {
            return T.constr_chunk_sectionsMask.newInstance(chunk, sectionsMask);
        } else {
            boolean flag = WorldHandle.fromBukkit(chunk.getWorld()).getWorldProvider().isDarkWorld();
            return T.constr_chunk_flag_sectionsMask.newInstance(chunk, flag, sectionsMask);
        }
    }
    public abstract int getX();
    public abstract void setX(int value);
    public abstract int getZ();
    public abstract void setZ(int value);
    public abstract boolean isHasBiomeData();
    public abstract void setHasBiomeData(boolean value);
    /**
     * Stores class members for <b>net.minecraft.server.PacketPlayOutMapChunk</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class PacketPlayOutMapChunkClass extends Template.Class<PacketPlayOutMapChunkHandle> {
        @Template.Optional
        public final Template.Constructor.Converted<PacketPlayOutMapChunkHandle> constr_chunk_flag_sectionsMask = new Template.Constructor.Converted<>();
        @Template.Optional
        public final Template.Constructor.Converted<PacketPlayOutMapChunkHandle> constr_chunk_sectionsMask = new Template.Constructor.Converted<>();

        public final Template.Field.Integer x = new Template.Field.Integer();
        public final Template.Field.Integer z = new Template.Field.Integer();
        @Template.Optional
        public final Template.Field.Integer sectionsMask = new Template.Field.Integer();
        @Template.Optional
        public final Template.Field.Converted<CommonTagCompound> metadata = new Template.Field.Converted<>();
        @Template.Optional
        public final Template.Field<byte[]> data = new Template.Field<>();
        @Template.Optional
        public final Template.Field.Converted<List<CommonTagCompound>> tags = new Template.Field.Converted<>();
        @Template.Optional
        public final Template.Field.Converted<ChunkMapHandle> chunkInfo = new Template.Field.Converted<>();
        public final Template.Field.Boolean hasBiomeData = new Template.Field.Boolean();

    }


    /**
     * Instance wrapper handle for type <b>net.minecraft.server.PacketPlayOutMapChunk.ChunkMap</b>.
     * To access members without creating a handle type, use the static {@link #T} member.
     * New handles can be created from raw instances using {@link #createHandle(Object)}.
     */
    @Template.Optional
    public abstract static class ChunkMapHandle extends Template.Handle {
        /** @See {@link ChunkMapClass} */
        public static final ChunkMapClass T = new ChunkMapClass();
        static final StaticInitHelper _init_helper = new StaticInitHelper(ChunkMapHandle.class, "net.minecraft.server.PacketPlayOutMapChunk.ChunkMap", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

        /* ============================================================================== */

        public static ChunkMapHandle createHandle(Object handleInstance) {
            return T.createHandle(handleInstance);
        }

        /* ============================================================================== */

        public abstract byte[] getData();
        public abstract void setData(byte[] value);
        public abstract int getSectionsMask();
        public abstract void setSectionsMask(int value);
        /**
         * Stores class members for <b>net.minecraft.server.PacketPlayOutMapChunk.ChunkMap</b>.
         * Methods, fields, and constructors can be used without using Handle Objects.
         */
        public static final class ChunkMapClass extends Template.Class<ChunkMapHandle> {
            public final Template.Field<byte[]> data = new Template.Field<>();
            public final Template.Field.Integer sectionsMask = new Template.Field.Integer();

        }

    }

}

