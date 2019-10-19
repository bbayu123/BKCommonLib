package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import org.bukkit.inventory.ItemStack;
import java.util.Map;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.RecipesFurnace</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
@Template.Optional
public abstract class RecipesFurnaceHandle extends Template.Handle {
    /** @See {@link RecipesFurnaceClass} */
    public static final RecipesFurnaceClass T = new RecipesFurnaceClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(RecipesFurnaceHandle.class, "net.minecraft.server.RecipesFurnace", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static RecipesFurnaceHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public static RecipesFurnaceHandle getInstance() {
        return T.getInstance.invoke();
    }

    public abstract ItemStackHandle getResult(ItemStackHandle itemstack);
    public abstract Map<ItemStack, ItemStack> getRecipes();
    public abstract void setRecipes(Map<ItemStack, ItemStack> value);
    /**
     * Stores class members for <b>net.minecraft.server.RecipesFurnace</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class RecipesFurnaceClass extends Template.Class<RecipesFurnaceHandle> {
        public final Template.Field.Converted<Map<ItemStack, ItemStack>> recipes = new Template.Field.Converted<>();

        public final Template.StaticMethod.Converted<RecipesFurnaceHandle> getInstance = new Template.StaticMethod.Converted<>();

        public final Template.Method.Converted<ItemStackHandle> getResult = new Template.Method.Converted<>();

    }

}

