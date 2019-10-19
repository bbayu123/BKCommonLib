package com.bergerkiller.bukkit.common.internal.network;

import com.bergerkiller.bukkit.common.Common;
import com.bergerkiller.bukkit.common.Logging;
import com.bergerkiller.bukkit.common.Task;
import com.bergerkiller.bukkit.common.events.PacketReceiveEvent;
import com.bergerkiller.bukkit.common.events.PacketSendEvent;
import com.bergerkiller.bukkit.common.internal.CommonPlugin;
import com.bergerkiller.bukkit.common.internal.PacketHandler;
import com.bergerkiller.bukkit.common.protocol.CommonPacket;
import com.bergerkiller.bukkit.common.protocol.PacketListener;
import com.bergerkiller.bukkit.common.protocol.PacketMonitor;
import com.bergerkiller.bukkit.common.protocol.PacketType;
import com.bergerkiller.bukkit.common.utils.CommonUtil;
import com.bergerkiller.generated.net.minecraft.server.PlayerConnectionHandle;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.*;
import com.comphenix.protocol.injector.GamePhase;
import com.comphenix.protocol.injector.PlayerLoggedOutException;
import com.comphenix.protocol.injector.packet.PacketRegistry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.logging.Level;

/**
 * A packet handler implementation that uses ProtocolLib packet listeners
 */
public class ProtocolLibPacketHandler implements PacketHandler {

    public static final String LIB_ROOT = "com.comphenix.protocol.";
    private final List<CommonPacketMonitor> monitors = new ArrayList<>();
    private final List<CommonPacketListener> listeners = new ArrayList<>();
    private final SilentPacketQueue silentPacketQueueFallback = new SilentPacketQueue();
    private final SilentQueueCleanupTask silentQueueCleanupTask = new SilentQueueCleanupTask();
    private boolean useSilentPacketQueue = false;
    private boolean isSendSilentPacketBroken = false;

    @Override
    public void onPlayerJoin(Player player) {
    }

    @Override
    public boolean onEnable() {
        // Check whether all required classes are available
        Class<?> manager = CommonUtil.getClass(LIB_ROOT + "ProtocolManager");
        Class<?> packetContainer = CommonUtil.getClass(LIB_ROOT + "events.PacketContainer");
        if (manager == null || packetContainer == null) {
            return false;
        }

        // There is a bug with protocollib where sending a silent packet one tick after the player
        // joins the server, ProtocolLib still notifies the packet listeners.
        // This here is a workaround for that, at least for BKCommonLib Packet Listeners.
        // See: https://github.com/PaperMC/Paper/issues/1934
        this.useSilentPacketQueue = false;
        if (Common.IS_PAPERSPIGOT_SERVER) {
            //TODO: This should only be done when anti-xray is enabled in (one of) the world configurations
            //      I have no idea how to (or if I should) hack into the paperspigot code to read this
            //      The field can be found in PaperWorldConfig but no idea where the mapping for it is
            this.useSilentPacketQueue = true;
        }
        return true;
    }

    @Override
    public boolean onDisable() {
        for (CommonPacketMonitor monitor : this.monitors) {
            ProtocolLibrary.getProtocolManager().removePacketListener(monitor);
        }
        for (CommonPacketListener listener : this.listeners) {
            ProtocolLibrary.getProtocolManager().removePacketListener(listener);
        }
        this.monitors.clear();
        this.listeners.clear();
        this.silentQueueCleanupTask.disable();
        if (!CommonUtil.isShuttingDown()) {
            Logging.LOGGER_NETWORK.warning("Reload detected! ProtocolLib does not officially support reloading the server!");
            if (!Bukkit.getOnlinePlayers().isEmpty()) {
                Logging.LOGGER_NETWORK.warning("Players are logged in. This is known to cause complete lock-out of the server!");
                Logging.LOGGER_NETWORK.warning("If you must absolutely reload, do so from the server terminal with no players logged in");
            }
        }
        return true;
    }

    @Override
    public String getName() {
        return "the ProtocolLib library";
    }

    @Override
    public Collection<Plugin> getListening(PacketType packetType) {
        Set<Plugin> plugins = new HashSet<>();
        // Obtain all plugins that have a listener (ignore monitors)
        boolean outGoing = packetType.isOutGoing();
        com.comphenix.protocol.PacketType comType = getPacketType(packetType);
        for (com.comphenix.protocol.events.PacketListener listener : ProtocolLibrary.getProtocolManager().getPacketListeners()) {
            final ListeningWhitelist whitelist;
            if (outGoing) {
                whitelist = listener.getSendingWhitelist();
            } else {
                whitelist = listener.getReceivingWhitelist();
            }
            if (whitelist.getPriority() != ListenerPriority.MONITOR && whitelist.getTypes().contains(comType)) {
                plugins.add(listener.getPlugin());
            }
        }
        return plugins;
    }

    @Override
    public void receivePacket(Player player, PacketType type, Object packet) {
        if (PacketHandlerHooked.getPlayerConnection(player) == null) {
            return; // NPC player is not connected
        }

        type.preprocess(packet);
        PacketContainer toReceive = new PacketContainer(getPacketType(packet.getClass()), packet);
        try {
            ProtocolLibrary.getProtocolManager().recieveClientPacket(player, toReceive);
        } catch (PlayerLoggedOutException ex) {
            // Ignore
        } catch (Exception e) {
            throw new RuntimeException("Error while receiving packet:", e);
        }
    }

    @Override
    public void sendPacket(Player player, PacketType type, Object packet, boolean throughListeners) {
        Object connection = PacketHandlerHooked.getPlayerConnection(player);
        if (connection == null) {
            return; // Player is an NPC or isn't connected
        }

        type.preprocess(packet);

        // Simplified logic for sending normally
        if (throughListeners) {
            PlayerConnectionHandle.T.sendPacket.raw.invoke(connection, packet);
            return;
        }

        // See: onEnable()
        if (this.useSilentPacketQueue) {
            this.silentPacketQueueFallback.add(player, packet);
            this.silentQueueCleanupTask.kick();
        }

        if (this.isSendSilentPacketBroken) {
            // ProtocolLib had a linkage error while sending the packet before
            // Instead we now use our own 'silent' packet queue to deal with this
            PlayerConnectionHandle.T.sendPacket.raw.invoke(connection, packet);

        } else {
            // Silent - do not send it through listeners, only through monitors
            try {
                PacketContainer toSend = new PacketContainer(getPacketType(packet.getClass()), packet);
                ProtocolLibrary.getProtocolManager().sendServerPacket(player, toSend, null, false);
            } catch (PlayerLoggedOutException ex) {
                // Ignore
            } catch (LinkageError err) {
                // Serious issue inside the ProtocolLib library. We cannot use it.
                Logging.LOGGER_NETWORK.log(Level.SEVERE, "A severe error occurred inside ProtocolLib while trying to send a packet");
                Logging.LOGGER_NETWORK.log(Level.SEVERE, "Please look for an update of ProtocolLib to get this issue resolved");
                Logging.LOGGER_NETWORK.log(Level.SEVERE, "Error that occurred", err);

                // Enable the silent packet queue (if it wasn't already)
                // Remove the packet from the queue in case we added it previously
                this.silentPacketQueueFallback.take(player, packet);
                this.isSendSilentPacketBroken = true;
                this.useSilentPacketQueue = true;

                // Fallback
                sendPacket(player, type, packet, throughListeners);
            } catch (Throwable t) {
                throw new RuntimeException("Error while sending packet:", t);
            }
        }
    }

    @Override
    public void removePacketListeners(Plugin plugin) {
        ProtocolLibrary.getProtocolManager().removePacketListeners(plugin);

        // Remove all listeners of this plugin
        Iterator<CommonPacketListener> list_iter = listeners.iterator();
        while (list_iter.hasNext()) {
            if (list_iter.next().getPlugin() == plugin) {
                list_iter.remove();
            }
        }

        // Remove all monitors of this plugin
        Iterator<CommonPacketMonitor> mon_iter = monitors.iterator();
        while (mon_iter.hasNext()) {
            if (mon_iter.next().getPlugin() == plugin) {
                mon_iter.remove();
            }
        }
    }

    @Override
    public void removePacketListener(PacketListener listener) {
        Iterator<CommonPacketListener> iter = listeners.iterator();
        while (iter.hasNext()) {
            CommonPacketListener cpl = iter.next();
            if (cpl.listener == listener) {
                ProtocolLibrary.getProtocolManager().removePacketListener(cpl);
                iter.remove();
            }
        }
    }

    @Override
    public void removePacketMonitor(PacketMonitor monitor) {
        Iterator<CommonPacketMonitor> iter = monitors.iterator();
        while (iter.hasNext()) {
            CommonPacketMonitor cpm = iter.next();
            if (cpm.monitor == monitor) {
                ProtocolLibrary.getProtocolManager().removePacketListener(cpm);
                iter.remove();
            }
        }
    }

    @Override
    public void addPacketListener(Plugin plugin, PacketListener listener, PacketType[] types) {
        CommonPacketListener commonListener = new CommonPacketListener(this, plugin, listener, types);
        ProtocolLibrary.getProtocolManager().addPacketListener(commonListener);
        this.listeners.add(commonListener);
    }

    @Override
    public void addPacketMonitor(Plugin plugin, PacketMonitor monitor, PacketType[] types) {
        CommonPacketMonitor commonMonitor = new CommonPacketMonitor(plugin, monitor, types);
        ProtocolLibrary.getProtocolManager().addPacketListener(commonMonitor);
        this.monitors.add(commonMonitor);
    }

    @Override
    public void transfer(PacketHandler to) {
        for (CommonPacketListener listener : listeners) {
            to.addPacketListener(listener.getPlugin(), listener.listener, listener.types);
        }
        for (CommonPacketMonitor monitor : monitors) {
            to.addPacketMonitor(monitor.getPlugin(), monitor.monitor, monitor.types);
        }
    }

    private static com.comphenix.protocol.PacketType getPacketType(PacketType commonType) {
        return getPacketType(commonType.getType());
    }

    private static com.comphenix.protocol.PacketType getPacketType(Class<?> packetClass) {
        return PacketRegistry.getPacketType(packetClass);
    }

    private static class CommonPacketMonitor extends CommonPacketAdapter {

        public final PacketMonitor monitor;

        public CommonPacketMonitor(Plugin plugin, PacketMonitor monitor, PacketType[] types) {
            super(plugin, ListenerPriority.MONITOR, types);
            this.monitor = monitor;
        }

        @Override
        public void onPacketReceiving(PacketEvent event) {
            monitor.onMonitorPacketReceive(new CommonPacket(event.getPacket().getHandle()), event.getPlayer());
        }

        @Override
        public void onPacketSending(PacketEvent event) {
            monitor.onMonitorPacketSend(new CommonPacket(event.getPacket().getHandle()), event.getPlayer());
        }
    }

    private static class CommonPacketListener extends CommonPacketAdapter {

        public final PacketListener listener;
        private final Class<?> temporaryPlayerClass;
        private final ProtocolLibPacketHandler packetHandler;

        public CommonPacketListener(ProtocolLibPacketHandler packetHandler, Plugin plugin, PacketListener listener, PacketType[] types) {
            super(plugin, ListenerPriority.NORMAL, types);
            this.packetHandler = packetHandler;
            this.listener = listener;

            Class<?> tempPlayerClass = String.class; // fallback, always unassignable to player
            try {
                tempPlayerClass = Class.forName("com.comphenix.protocol.injector.server.TemporaryPlayer");
            } catch (Throwable t) {
                Logging.LOGGER_NETWORK.warning("Failed to find ProtocolLib TemporaryPlayer class!");
            }
            this.temporaryPlayerClass = tempPlayerClass;
        }

        @Override
        public void onPacketReceiving(PacketEvent event) {
            // If coming from a pre-authenticated client, ignore the packet
            // ProtocolLib uses a TemporaryPlayer for that, which throws errors for certain properties
            // We rather not expose plugins to these problems.
            Player player = event.getPlayer();
            if (player == null || this.temporaryPlayerClass.isAssignableFrom(player.getClass())) {
                return;
            }

            CommonPacket packet = new CommonPacket(event.getPacket().getHandle());
            PacketReceiveEvent receiveEvent = new PacketReceiveEvent(player, packet);
            receiveEvent.setCancelled(event.isCancelled());
            listener.onPacketReceive(receiveEvent);
            event.setCancelled(receiveEvent.isCancelled());
        }

        @Override
        public void onPacketSending(PacketEvent event) {
            // Check not silent
            // See: onEnable why this is here.
            Object raw_packet = event.getPacket().getHandle();
            if (this.packetHandler.useSilentPacketQueue &&
                this.packetHandler.silentPacketQueueFallback.take(event.getPlayer(), raw_packet))
            {
                return;
            }

            CommonPacket packet = new CommonPacket(raw_packet);
            PacketSendEvent sendEvent = new PacketSendEvent(event.getPlayer(), packet);
            sendEvent.setCancelled(event.isCancelled());
            listener.onPacketSend(sendEvent);
            event.setCancelled(sendEvent.isCancelled());
        }
    }

    private static abstract class CommonPacketAdapter implements com.comphenix.protocol.events.PacketListener {

        private final Plugin plugin;
        public final PacketType[] types;
        private final ListeningWhitelist receiving;
        private final ListeningWhitelist sending;

        public CommonPacketAdapter(Plugin plugin, ListenerPriority priority, PacketType[] types) {
            this.plugin = plugin;
            this.types = types;
            this.receiving = getWhiteList(priority, types, true);
            this.sending = getWhiteList(priority, types, false);
        }

        private static ListeningWhitelist getWhiteList(ListenerPriority priority, PacketType[] types, boolean receiving) {
            List<com.comphenix.protocol.PacketType> comTypes = new ArrayList<>();
            for (PacketType type : types) {
                if ((!type.isOutGoing()) != receiving) {
                    continue;
                }
                if (type.getType() == null) {
                    continue;
                }
                com.comphenix.protocol.PacketType comType = getPacketType(type);
                if (comType != null) {
                    comTypes.add(comType);
                }
            }
            return ListeningWhitelist.newBuilder().priority(priority).types(comTypes)
                    .gamePhase(GamePhase.PLAYING).options(new ListenerOptions[] { ListenerOptions.ASYNC } ).build();
        }

        @Override
        public Plugin getPlugin() {
            return plugin;
        }

        @Override
        public ListeningWhitelist getReceivingWhitelist() {
            return receiving;
        }

        @Override
        public ListeningWhitelist getSendingWhitelist() {
            return sending;
        }
    }

    private final class SilentQueueCleanupTask extends Task {
        private boolean _isScheduled = false;

        public SilentQueueCleanupTask() {
            super(CommonPlugin.getInstance());
        }

        @Override
        public synchronized void run() {
            if (ProtocolLibPacketHandler.this.silentPacketQueueFallback.cleanup()) {
                this._isScheduled = false;
                this.stop();
            }
        }

        public synchronized void disable() {
            if (this._isScheduled) {
                this._isScheduled = false;
                this.stop();
            }
        }

        public synchronized void kick() {
            if (!this._isScheduled) {
                this.start(20, 20);
                this._isScheduled = true;
            }
        }
    }
}
