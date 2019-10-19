package com.bergerkiller.bukkit.common.internal;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.bergerkiller.bukkit.common.nbt.CommonTagCompound;
import com.bergerkiller.bukkit.common.utils.ItemUtil;

/**
 * Keeps track of map UUID <> Map Id conversion
 */
public class CommonMapUUIDStore {
    /**
     * Material for a filled map, the type given out when creating map display items
     */
    public static final Material FILLED_MAP_TYPE;

    static {
        if (CommonCapabilities.MATERIAL_ENUM_CHANGES) {
            FILLED_MAP_TYPE = CommonLegacyMaterials.getMaterial("FILLED_MAP");
        } else {
            FILLED_MAP_TYPE = CommonLegacyMaterials.getLegacyMaterial("MAP");
        }
    }

    /**
     * Gets whether an ItemStack contains a Map item
     * 
     * @param item
     * @return True if a map item
     */
    public static boolean isMap(ItemStack item) {
        return item != null && item.getType() == FILLED_MAP_TYPE;
    }

    /**
     * Gets the Map Id stored inside a Map Item
     * 
     * @param item
     * @return map Id
     */
    public static int getItemMapId(ItemStack item) {
        if (CommonCapabilities.MAP_ID_IN_NBT) {
            CommonTagCompound tag = ItemUtil.getMetaTag(item, false);
            if (tag != null) {
                return tag.getValue("map", -1);
            } else {
                return -1;
            }
        } else {
            return item.getDurability();
        }
    }

    /**
     * Sets the Map Id stored inside a Map Item
     * 
     * @param item
     * @param mapId to set to
     */
    public static void setItemMapId(ItemStack item, int mapId) {
        if (CommonCapabilities.MAP_ID_IN_NBT) {
            ItemUtil.getMetaTag(item, true).putValue("map", mapId);
        } else {
            item.setDurability((short) mapId);
        }
    }

    /**
     * Internal use only! Obtains the unique Id of a map item. Returns null when the item is not a valid map.
     * This function may be subject to change and should not be depended on.
     * 
     * @param item to get the Map Id for
     * @return map id
     */
    public static UUID getMapUUID(ItemStack item) {
        if (!isMap(item)) {
            return null;
        } else {
            CommonTagCompound tag = ItemUtil.getMetaTag(item, false);
            if (tag != null) {
                UUID uuid = tag.getUUID("mapDisplay");
                if (uuid != null) {
                    return uuid;
                }
            }

            // Turn Map Id into an UUID
            return getStaticMapUUID(getItemMapId(item));
        }
    }

    /**
     * Internal use only! Obtains a UUID from a map id value.
     * 
     * @param mapId to turn into a UUID
     * @return map UUID
     */
    public static UUID getStaticMapUUID(int mapId) {
        // Turn Map durability into an UUID
        long leastBits = mapId;
        long mostBits = 0L;
        return new UUID(mostBits, leastBits);
    }

    /**
     * Internal use only! If the UUID is that of a static map UUID, returns
     * the static Map Id. Returns -1 if the UUID is not static.
     * 
     * @param uuid
     * @return map Id, -1 if not static
     */
    public static int getStaticMapId(UUID uuid) {
        if (uuid.getMostSignificantBits() != 0L) {
            return -1;
        }
        long least = uuid.getLeastSignificantBits();
        int id = (int) (least & 0xFFFFFFFFL);
        if ((least - id) != 0L) {
            return -1;
        }
        return id;
    }

    /**
     * Generates a new Map UUID for uniquely identifying a map item instance
     * 
     * @return new map instance UUID
     */
    public static UUID generateDynamicMapUUID() {
        UUID uuid;
        do {
            uuid = UUID.randomUUID();
        } while (getStaticMapId(uuid) != -1);
        return uuid;
    }
}
