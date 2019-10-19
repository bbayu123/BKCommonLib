package com.bergerkiller.bukkit.common.controller;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.bergerkiller.bukkit.common.conversion.type.HandleConversion;
import com.bergerkiller.bukkit.common.entity.CommonEntity;
import com.bergerkiller.bukkit.common.entity.CommonEntityController;
import com.bergerkiller.bukkit.common.internal.hooks.EntityHook;
import com.bergerkiller.bukkit.common.internal.logic.EntityMoveHandler;
import com.bergerkiller.bukkit.common.wrappers.HumanHand;
import com.bergerkiller.bukkit.common.wrappers.MoveType;
import com.bergerkiller.mountiplex.reflection.ClassInterceptor;

public abstract class EntityController<T extends CommonEntity<?>> extends CommonEntityController<T> {
    private EntityHook hook = null;
    private final EntityMoveHandler moveHandler = EntityMoveHandler.create(this);

    /**
     * Binds this Entity Controller to an Entity. This is called from elsewhere,
     * and should be ignored entirely.
     *
     * @param entity to bind with
     * @param handleAttachment whether to fire {@link #onAttached()}
     */
    @SuppressWarnings("unchecked")
    public final void bind(CommonEntity<?> entity, boolean handleAttachment) {
        if (entity == null && this.hook == null) {
            throw new RuntimeException("WTF");
        }
        if (entity != null && entity.getWorld() == null) {
            throw new RuntimeException("Can not bind to an Entity that has no world set");
        }
        if (this.entity != null) {
            this.onDetached();
        }
        this.entity = (T) entity;
        if (this.entity != null) {
            this.hook = ClassInterceptor.get(this.entity.getHandle(), EntityHook.class);
            if (this.hook == null) {
                this.hook = new EntityHook();
                this.hook.mock(this.entity.getHandle());
            }
            this.hook.setController(this);
            if (handleAttachment) {
                this.onAttached();
            }
        }
    }

    /**
     * Called when this Entity dies (could be called more than one time)
     */
    public void onDie() {
        this.hook.base.die();
    }

    /**
     * Called every tick to update the entity
     */
    @Override
    public void onTick() {
        this.hook.base.onTick();
    }

    /**
     * Called when the entity is interacted by something
     *
     * @param interacter that interacted
     * @param hand that is used
     * @return True if interaction occurred, False if not
     */
    public boolean onInteractBy(HumanEntity interacter, HumanHand hand) {
        return this.hook.base_onInteractBy(interacter, hand);
    }

    /**
     * Called when the entity is damaged by something
     *
     * @param damageSource of the damage
     * @param damage amount
     */
    public boolean onDamage(com.bergerkiller.bukkit.common.wrappers.DamageSource damageSource, double damage) {
        return this.hook.base.onDamageEntity(damageSource.getRawHandle(), (float) damage);
    }

    /**
     * Handles the collision of this minecart with another Entity
     *
     * @param e entity with which is collided
     * @return True if collision is allowed, False if it is ignored
     */
    public boolean onEntityCollision(org.bukkit.entity.Entity e) {
        return true;
    }

    /**
     * Handles the collision of this minecart with a Block
     *
     * @param block with which this minecart collided
     * @param hitFace of the block that the minecart hit
     * @return True if collision is allowed, False if it is ignored
     */
    public boolean onBlockCollision(org.bukkit.block.Block block, BlockFace hitFace) {
        return true;
    }

    /**
     * Fired when the entity is getting burned by something
     *
     * @param damage dealt
     */
    public void onBurnDamage(double damage) {
        hook.base.onBurn((float) damage);
    }

    /**
     * Gets whether this Entity Controller allows players to take this Entity
     * with them. With this enabled, players take the vehicle with them. To
     * disable this default behavior, override this method.
     *
     * @return True if players can take the entity with them, False if not
     */
    public boolean isPlayerTakable() {
        return true;
    }

    /**
     * Gets the localized name of this Entity. Override this method to change
     * the name.
     *
     * @return Localized name
     */
    public String getLocalizedName() {
        return hook.getName_base();
    }

    public void onPush(double dx, double dy, double dz) {
        hook.base.onPush(dx, dy, dz);
    }

    /**
     * Performs Entity movement logic, to move the Entity and handle collisions
     *
     * @param moveType mode of moving
     * @param dx offset to move
     * @param dy offset to move
     * @param dz offset to move
     */
    public void onMove(MoveType moveType, double dx, double dy, double dz) {
        moveHandler.move(moveType, dx, dy ,dz);
        //hook.base.onMove(com.bergerkiller.generated.net.minecraft.server.EnumMoveTypeHandle.SELF.getRaw(), dx, dy, dz);
    }

    /**
     * Called when an item in the inventory changes, if the entity has an inventory
     * 
     * @param index of the item
     * @param item it was set to
     */
    public void onItemSet(int index, ItemStack item) {
        hook.base.setInventoryItem(index, HandleConversion.toItemStackHandle(item));
    }

    /**
     * Sets whether block collisions are handled during movement of the entity.
     * Disabling block collisions causes entities to fall through the floor, but may
     * result in a significant boost in performance.
     * 
     * @param enabled
     */
    public void setBlockCollisionEnabled(boolean enabled) {
        this.moveHandler.setBlockCollisionEnabled(enabled);
    }

    /**
     * Sets whether entity collisions are handled during movement of the entity.
     * Disabling entity collisions causes entities to go right through other entities
     * without being affected, and may result in a significant boost in performance.
     * 
     * @param enabled
     */
    public void setEntityCollisionEnabled(boolean enabled) {
        this.moveHandler.setEntityCollisionEnabled(enabled);
    }

    /**
     * Sets custom axis-aligned bounding box x/y/z size dimensions when handling block
     * collisions. If using a larger bounding box, changing the block collision bounding
     * box helps fixing significant stress on the server handling a lot more block collision
     * events than usual.
     * 
     * @param bounds size vector to set to, <i>null<i/> for defaults
     */
    public void setBlockCollisionBounds(Vector bounds) {
        this.moveHandler.setCustomBlockCollisionBounds(bounds);
    }
}
