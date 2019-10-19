package com.bergerkiller.bukkit.common.internal.logic;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Chunk;
import org.bukkit.World;

import com.bergerkiller.bukkit.common.Common;
import com.bergerkiller.bukkit.common.bases.IntVector2;
import com.bergerkiller.bukkit.common.utils.CommonUtil;
import com.bergerkiller.generated.net.minecraft.server.RegionFileHandle;
import com.bergerkiller.generated.net.minecraft.server.WorldHandle;
import com.bergerkiller.mountiplex.reflection.declarations.ClassResolver;
import com.bergerkiller.mountiplex.reflection.declarations.MethodDeclaration;
import com.bergerkiller.mountiplex.reflection.util.FastMethod;

/**
 * Handles region-based operations from MC 1.14 onwards
 */
public class RegionHandler_1_14 extends RegionHandler {
    private static final FastMethod<Collection<Object>> findWorldRegionFileInstances = new FastMethod<>();
    private static final FastMethod<Collection<IntVector2>> findWorldRegionFileCoordinates = new FastMethod<>();
    private static final FastMethod<Object> findRegionFileAt = new FastMethod<>();

    public RegionHandler_1_14() {
        ClassResolver resolver = new ClassResolver();
        resolver.setDeclaredClass(CommonUtil.getNMSClass("RegionFileCache"));

        // Initialize runtime generated method to obtain the RegionFile instances of a World
        {
            MethodDeclaration findWorldRegionFileInstancesMethod = new MethodDeclaration(resolver,
                    "public static Collection<RegionFile> findWorldRegionFileInstances(WorldServer world) {\n" +
                    "    ChunkProviderServer cps = world.getChunkProvider();\n" +
                    "    PlayerChunkMap pcm = cps.playerChunkMap;\n" +
                    "    RegionFileCache rfc = (RegionFileCache) pcm;\n" +
                    "    return rfc.cache.values();\n" +
                    "}");
            findWorldRegionFileInstances.init(findWorldRegionFileInstancesMethod);
        }

        // Initialize method to obtain all the region coordinates of regions loaded
        {
            MethodDeclaration findWorldRegionFileCoordinatesMethod = new MethodDeclaration(resolver,
                    "public static Collection<com.bergerkiller.bukkit.common.bases.IntVector2> " +
                    "findWorldRegionFileInstances(WorldServer world) {\n" +
                    "    ChunkProviderServer cps = world.getChunkProvider();\n" +
                    "    PlayerChunkMap pcm = cps.playerChunkMap;\n" +
                    "    RegionFileCache rfc = (RegionFileCache) pcm;\n" +
                    "    it.unimi.dsi.fastutil.longs.LongSet coordSet;\n" +
                    "    it.unimi.dsi.fastutil.longs.LongIterator iter;\n" +
                    "\n" +
                    "    coordSet = rfc.cache.keySet();\n" +
                    "    java.util.ArrayList result = new java.util.ArrayList(coordSet.size());\n" +
                    "    iter = coordSet.iterator();\n" +
                    "    while (iter.hasNext()) {\n" +
                    "        long coord = iter.nextLong();\n" +
                    "        int coord_x = ChunkCoordIntPair.getX(coord);\n" +
                    "        int coord_z = ChunkCoordIntPair.getZ(coord);\n" +
                    "        result.add(new com.bergerkiller.bukkit.common.bases.IntVector2(coord_x, coord_z));\n" +
                    "    }\n" +
                    "    return result;\n" +
                    "}");
            findWorldRegionFileCoordinates.init(findWorldRegionFileCoordinatesMethod);
        }

        // Initialize method to obtain a RegionFile instance at a given region x/z from the cache
        {
            MethodDeclaration findRegionFileAtMethod = new MethodDeclaration(resolver,
                    "public static RegionFile findRegionFileAt(WorldServer world, int rx, int rz) {\n" +
                    "    ChunkProviderServer cps = world.getChunkProvider();\n" +
                    "    PlayerChunkMap pcm = cps.playerChunkMap;\n" +
                    "    RegionFileCache rfc = (RegionFileCache) pcm;\n" +
                    "    long coord = ChunkCoordIntPair.pair(rx, rz);\n" +
                    "    return rfc.cache.get(coord);\n" +
                    "}");
            findRegionFileAt.init(findRegionFileAtMethod);
        }
    }

    @Override
    public void closeStreams(World world) {
        for (Object regionFile : findWorldRegionFileInstances.invoke(null, WorldHandle.fromBukkit(world).getRaw())) {
            RegionFileHandle.createHandle(regionFile).close();
        }
    }

    @Override
    public Set<IntVector2> getRegions(World world) {
        HashSet<IntVector2> regionIndices = new HashSet<>();

        // Add all RegionFile instances in the cache
        regionIndices.addAll(findWorldRegionFileCoordinates.invoke(null, WorldHandle.fromBukkit(world).getRaw()));

        // Obtain the region coordinates from all files in regions folder
        File regionFolder = Common.SERVER.getWorldRegionFolder(world.getName());
        if (regionFolder != null) {
            String[] regionFileNames = regionFolder.list();
            for (String regionFileName : regionFileNames) {
                File file = new File(regionFolder, regionFileName);
                if (file.isFile() && file.exists() && file.length() >= 4096) {
                    IntVector2 coords = getRegionFileCoordinates(file);
                    if (coords != null && !regionIndices.contains(coords)) {
                        regionIndices.add(coords);
                    }
                }
            }
        }

        // Look at all loaded chunks of the world and add the regions they are inside of
        for (Chunk chunk : world.getLoadedChunks()) {
            IntVector2 coords = new IntVector2(chunk.getX() >> 5, chunk.getZ() >> 5);
            if (!regionIndices.contains(coords)) {
                regionIndices.add(coords);
            }
        }

        return regionIndices;
    }

    @Override
    public BitSet getRegionChunks(World world, int rx, int rz) {
        BitSet chunks = new BitSet(1024);

        RegionFileHandle regionFileHandle = RegionFileHandle.createHandle(findRegionFileAt.invoke(null,
                WorldHandle.fromBukkit(world).getRaw(), rx, rz));

        if (regionFileHandle == null) {
            File regionFile = getRegionFile(world, rx, rz);
            if (regionFile.exists()) {
                // Start a new file stream to read the coordinates
                // Creating a new region file is too slow and results in memory leaks
                try {
                    DataInputStream stream = new DataInputStream(new FileInputStream(regionFile));
                    try {
                        for (int coordIndex = 0; coordIndex < 1024; coordIndex++) {
                            if (stream.readInt() > 0) {
                                chunks.set(coordIndex);
                            }
                        }
                    } finally {
                        stream.close();
                    }
                } catch (IOException ex) {
                }
            }
        } else {
            // Use the RegionFile instance to find out what chunks exist
            int coordIndex = 0;
            int cx, cz;
            for (cz = 0; cz < 32; cz++) {
                for (cx = 0; cx < 32; cx++) {
                    if (regionFileHandle.chunkExists(cx, cz)) {
                        chunks.set(coordIndex);
                    }
                    coordIndex++;
                }
            }
        }

        return chunks;
    }

    @Override
    public boolean isChunkSaved(World world, int cx, int cz) {
        int rx = cx >> 5;
        int rz = cz >> 5;
        cx &= 0x1F;
        cz &= 0x1F;

        // Try checking if a RegionFile instance is available. If so, use that.
        RegionFileHandle regionFileHandle = RegionFileHandle.createHandle(findRegionFileAt.invoke(null,
                WorldHandle.fromBukkit(world).getRaw(), rx, rz));
        if (regionFileHandle != null) {
            return regionFileHandle.chunkExists(cx, cz);
        }

        //TODO: Optimize!
        return getRegionChunks(world, rx, rz).get((cz << 5) | cx);
    }
}
