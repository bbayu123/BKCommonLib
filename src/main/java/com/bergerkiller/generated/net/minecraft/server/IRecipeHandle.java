package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import org.bukkit.inventory.ItemStack;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.IRecipe</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class IRecipeHandle extends Template.Handle {
    /** @See {@link IRecipeClass} */
    public static final IRecipeClass T = new IRecipeClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(IRecipeHandle.class, "net.minecraft.server.IRecipe", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static IRecipeHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public abstract ItemStack getOutput();
    /**
     * Stores class members for <b>net.minecraft.server.IRecipe</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class IRecipeClass extends Template.Class<IRecipeHandle> {
        public final Template.Method.Converted<ItemStack> getOutput = new Template.Method.Converted<>();

    }

}

