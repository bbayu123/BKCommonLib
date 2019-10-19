package com.bergerkiller.generated.org.bukkit.craftbukkit.util;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;
import org.bukkit.Material;

/**
 * Instance wrapper handle for type <b>org.bukkit.craftbukkit.util.CraftMagicNumbers</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class CraftMagicNumbersHandle extends Template.Handle {
    /** @See {@link CraftMagicNumbersClass} */
    public static final CraftMagicNumbersClass T = new CraftMagicNumbersClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(CraftMagicNumbersHandle.class, "org.bukkit.craftbukkit.util.CraftMagicNumbers", com.bergerkiller.bukkit.common.Common.TEMPLATE_RESOLVER);

    /* ============================================================================== */

    public static CraftMagicNumbersHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    public static Material getMaterialFromBlock(Object nmsBlock) {
        return T.getMaterialFromBlock.invoke(nmsBlock);
    }

    public static Material getMaterialFromItem(Object nmsItem) {
        return T.getMaterialFromItem.invoke(nmsItem);
    }

    public static Object getItemFromMaterial(Material material) {
        return T.getItemFromMaterial.invoke(material);
    }

    public static Object getBlockFromMaterial(Material material) {
        return T.getBlockFromMaterial.invoke(material);
    }


    public static com.bergerkiller.generated.net.minecraft.server.IBlockDataHandle getBlockDataFromMaterial(org.bukkit.Material material) {
        return com.bergerkiller.generated.net.minecraft.server.BlockHandle.T.getBlockData.invoke(getBlockFromMaterial(material));
    }
    /**
     * Stores class members for <b>org.bukkit.craftbukkit.util.CraftMagicNumbers</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class CraftMagicNumbersClass extends Template.Class<CraftMagicNumbersHandle> {
        public final Template.StaticMethod.Converted<Material> getMaterialFromBlock = new Template.StaticMethod.Converted<>();
        public final Template.StaticMethod.Converted<Material> getMaterialFromItem = new Template.StaticMethod.Converted<>();
        public final Template.StaticMethod.Converted<Object> getItemFromMaterial = new Template.StaticMethod.Converted<>();
        public final Template.StaticMethod.Converted<Object> getBlockFromMaterial = new Template.StaticMethod.Converted<>();

    }

}

