package com.bergerkiller.bukkit.common.internal.proxy;

import java.util.function.Consumer;

import com.bergerkiller.bukkit.common.utils.CommonUtil;
import com.bergerkiller.mountiplex.reflection.SafeMethod;

/**
 * This proxy is used on MC 1.12.2 and before, back when a Runnable was used as callback
 * instead of a future / consumer system.
 */
public class ChunkConsumerProxy implements Runnable {
    private static final SafeMethod<Object> cps_getChunkAt = new SafeMethod<>(CommonUtil.getNMSClass("ChunkProviderServer"), "getChunkAt", int.class, int.class);
    private final Consumer<Object> consumer;
    private final Object cps;
    private final int chunkX;
    private final int chunkZ;

    public ChunkConsumerProxy(Consumer<Object> consumer, Object cps, int chunkX, int chunkZ) {
        this.consumer = consumer;
        this.cps = cps;
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
    }

    @Override
    public void run() {
        Object chunk = null;
        try {
            chunk = cps_getChunkAt.invoke(this.cps, this.chunkX, this.chunkZ);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        this.consumer.accept(chunk);
    }
}
