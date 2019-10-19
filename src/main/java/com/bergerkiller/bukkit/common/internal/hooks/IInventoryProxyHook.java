package com.bergerkiller.bukkit.common.internal.hooks;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import com.bergerkiller.bukkit.common.conversion.DuplexConversion;
import com.bergerkiller.bukkit.common.conversion.type.HandleConversion;
import com.bergerkiller.bukkit.common.conversion.type.WrapperConversion;
import com.bergerkiller.bukkit.common.utils.ItemUtil;
import com.bergerkiller.bukkit.common.utils.LogicUtil;
import com.bergerkiller.generated.net.minecraft.server.ItemStackHandle;
import com.bergerkiller.generated.org.bukkit.inventory.InventoryHandle;
import com.bergerkiller.mountiplex.conversion.util.ConvertingList;
import com.bergerkiller.mountiplex.reflection.ClassHook;
import com.bergerkiller.mountiplex.reflection.declarations.Template.Handle;

/**
 * Redirects all IInventory function calls to the appropriate method in a 
 * org.bukkit.inventory.Inventory object.
 */
public class IInventoryProxyHook extends ClassHook<IInventoryProxyHook> {
    private final Inventory inventory;

    public IInventoryProxyHook(Inventory inventory ){
        this.inventory = inventory;
    }

    @HookMethod("public abstract int getSize()")
    public int getSize() {
        return this.inventory.getSize();
    }

    @HookMethod("public abstract int getMaxStackSize()")
    public int getMaxStackSize() {
        return this.inventory.getMaxStackSize();
    }

    @HookMethod("public abstract void setMaxStackSize(int paramInt)")
    public void setMaxStackSize(int size) {
        inventory.setMaxStackSize(size);
    }

    @HookMethod("public abstract void setItem(int paramInt, ItemStack paramItemStack)")
    public void setItem(int index, Object nmsItemStack) {
        this.inventory.setItem(index, WrapperConversion.toItemStack(nmsItemStack));
    }

    @HookMethod("public abstract ItemStack getItem(int paramInt)")
    public Object getItem(int index) {
        return HandleConversion.toItemStackHandle(this.inventory.getItem(index));
    }

    @HookMethod("public abstract void clear:???()")
    public void clear() {
        this.inventory.clear();
    }

    @HookMethod(value="public abstract List<ItemStack> getContents()", optional=true)
    public List<?> getContents() {
        return new ConvertingList<>(Arrays.asList(this.inventory.getContents()), DuplexConversion.itemStack.reverse());
    }

    @HookMethod(value="public abstract ItemStack[] getContents()", optional=true)
    public Object[] getContents_old() {
        return LogicUtil.toArray(this.getContents(), ItemStackHandle.T.getType());
    }

    @HookMethod("public abstract List<org.bukkit.entity.HumanEntity> getViewers()")
    public List<?> getViewers() {
        return new ConvertingList<>(this.inventory.getViewers(), DuplexConversion.entity.reverse());
    }

    @HookMethod("public abstract org.bukkit.inventory.InventoryHolder getOwner()")
    public InventoryHolder getOwner() {
        return this.inventory.getHolder();
    }

    // Since 1.10.2
    @HookMethod(value="public abstract org.bukkit.Location getLocation()", optional=true)
    public Location getLocation() {
        if (InventoryHandle.T.getLocation.isAvailable()) {
            return InventoryHandle.T.getLocation.invoke(this.inventory);
        } else {
            return null;
        }
    }

    /* Questionable implementations taken over from EntityMinecartContainer */

    @HookMethod("public ItemStack splitStack(int i, int j)")
    public Object splitStack(int i, int j) {
        if ((i < 0) || (i >= inventory.getSize()) || (j <= 0)) {
            return ItemStackHandle.EMPTY_ITEM.getRaw();
        }
        org.bukkit.inventory.ItemStack item = this.inventory.getItem(i);
        if (ItemUtil.isEmpty(item)) {
            return ItemStackHandle.EMPTY_ITEM.getRaw();
        }
        ItemStackHandle nmsItem = ItemStackHandle.fromBukkit(item);
        if (nmsItem == null) {
            return ItemStackHandle.EMPTY_ITEM.getRaw();
        }
        return Handle.getRaw(nmsItem.cloneAndSubtract(j));
    }

    @HookMethod("public ItemStack splitWithoutUpdate(int i)")
    public Object splitWithoutUpdate(int i) {
        if ((i < 0) || (i >= inventory.getSize())) {
            return ItemStackHandle.EMPTY_ITEM.getRaw();
        }
        org.bukkit.inventory.ItemStack item = this.inventory.getItem(i);
        if (ItemUtil.isEmpty(item)) {
            return ItemStackHandle.EMPTY_ITEM.getRaw();
        }
        this.setItem(i, ItemStackHandle.EMPTY_ITEM.getRaw());
        return HandleConversion.toItemStackHandle(item);
    }

    /* The below are NOP because they don't make sense for an Inventory 'Base' not representing anything */

    @HookMethod("public abstract void update()")
    public void update() {
    }

    @HookMethod("public abstract void onOpen(org.bukkit.craftbukkit.entity.CraftHumanEntity paramCraftHumanEntity)")
    public void onOpen(Object entity) {
    }

    @HookMethod("public abstract void onClose(org.bukkit.craftbukkit.entity.CraftHumanEntity paramCraftHumanEntity)")
    public void onClose(Object entity) {
    }

    @HookMethod("public abstract void startOpen(EntityHuman paramEntityHuman)")
    public void startOpen(Object entityHuman) {
    }

    @HookMethod("public abstract void closeContainer(EntityHuman paramEntityHuman)")
    public void closeContainer(Object entityHuman) {
    }

    // <= MC 1.13.2 only
    @HookMethod(value="public abstract int getProperty:???(int key)", optional=true)
    public int getProperty(int key) {
        return 0;
    }

    // <= MC 1.13.2 only
    @HookMethod(value="public abstract void setProperty:???(int key, int value)", optional=true)
    public void setProperty(int key, int value) {
    }

    // <= MC 1.13.2 only
    @HookMethod(value="public abstract int someFunction:???()", optional=true)
    public int someFunction() {
        return 0;
    }

    @HookMethod("public abstract boolean canOpen:???(EntityHuman paramEntityHuman)")
    public boolean canOpen(Object human) {
        return true;
    }

    @HookMethod("public abstract boolean canStoreItem:???(int paramInt, ItemStack paramItemStack)")
    public boolean canStoreItem(int paramInt, Object paramItemStack) {
        return true;
    }

    @HookMethod(value="public abstract boolean someFunction2:???()", optional=true)
    public boolean someFunction2() {
        return true;
    }
}
