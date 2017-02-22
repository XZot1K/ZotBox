package XZot1K.plugins.zl.libraries.inventorylib;

import XZot1K.plugins.zl.ZotLib;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomItem
{

    private ZotLib plugin = ZotLib.getInstance();
    private ItemStack itemStack;

    public CustomItem(Material material, int amount, short durability)
    {
        setItemStack(new ItemStack(material, amount, durability));
    }

    public CustomItem(int materialId, int amount, short durability)
    {
        setItemStack(new ItemStack(Material.getMaterial(materialId), amount, durability));
    }

    public CustomItem(String materialName, int amount, short durability)
    {
        String formatedName = materialName.toUpperCase().replace(" ", "_").replace("-", "_");
        setItemStack(new ItemStack(Material.getMaterial(formatedName), amount, durability));
    }

    public ItemStack getItemStack()
    {
        return itemStack;
    }

    private void setItemStack(ItemStack itemStack)
    {
        this.itemStack = itemStack;
    }

    public boolean hasEnchantment()
    {
        return !getItemStack().getEnchantments().isEmpty();
    }

    public Map<String, Object> getItemStackSerialized()
    {
        return getItemStack().serialize();
    }

    public CustomItem setDisplayName(String displayName)
    {
        ItemMeta meta = getItemStack().getItemMeta();
        meta.setDisplayName(plugin.getGeneralLibrary().color(displayName));
        getItemStack().setItemMeta(meta);
        return this;
    }

    public CustomItem setLore(List<String> lore)
    {
        ItemMeta meta = getItemStack().getItemMeta();
        ArrayList<String> newLore = new ArrayList<>();
        for (String line : lore)
        {
            newLore.add(plugin.getGeneralLibrary().color(line));
        }
        meta.setLore(newLore);
        getItemStack().setItemMeta(meta);
        return this;
    }

    public CustomItem addEnchantment(Enchantment enchantment, int level, boolean ignoreLevelRestriction)
    {
        if (ignoreLevelRestriction)
        {
            getItemStack().addUnsafeEnchantment(enchantment, level);
        } else
        {
            getItemStack().addEnchantment(enchantment, level);
        }
        return this;
    }

    public CustomItem addMetaEnchantment(Enchantment enchantment, int level, boolean ignoreLevelRestriction)
    {
        ItemMeta meta = getItemStack().getItemMeta();
        meta.addEnchant(enchantment, level, ignoreLevelRestriction);
        getItemStack().setItemMeta(meta);
        return this;
    }

    public CustomItem removeEnchantment(Enchantment enchantment)
    {
        getItemStack().removeEnchantment(enchantment);
        return this;
    }

    public CustomItem removeMetaEnchantment(Enchantment enchantment)
    {
        ItemMeta meta = getItemStack().getItemMeta();
        meta.removeEnchant(enchantment);
        getItemStack().setItemMeta(meta);
        return this;
    }

    public CustomItem setBaseValues(Material material, int amount, short durability)
    {
        getItemStack().setType(material);
        getItemStack().setAmount(amount);
        getItemStack().setDurability(durability);
        return this;
    }

    public CustomItem removeItemFlag(ItemFlag itemFlag)
    {
        ItemMeta meta = getItemStack().getItemMeta();
        meta.removeItemFlags(itemFlag);
        getItemStack().setItemMeta(meta);
        return this;
    }

    public CustomItem removeItemFlags(ItemFlag[] itemFlags)
    {
        ItemMeta meta = getItemStack().getItemMeta();
        meta.removeItemFlags(itemFlags);
        getItemStack().setItemMeta(meta);
        return this;
    }

    public CustomItem addItemFlag(ItemFlag itemFlag)
    {
        ItemMeta meta = getItemStack().getItemMeta();
        meta.addItemFlags(itemFlag);
        getItemStack().setItemMeta(meta);
        return this;
    }

    public CustomItem addItemFlags(ItemFlag[] itemFlags)
    {
        ItemMeta meta = getItemStack().getItemMeta();
        meta.addItemFlags(itemFlags);
        getItemStack().setItemMeta(meta);
        return this;
    }

    public boolean hasConflictingEnchant(Enchantment enchantment)
    {
        ItemMeta meta = getItemStack().getItemMeta();
        return meta.hasConflictingEnchant(enchantment);
    }

    public ItemMeta getItemMeta()
    {
        return getItemStack().getItemMeta();
    }

    public CustomItem setItemMeta(ItemMeta meta)
    {
        getItemStack().setItemMeta(meta);
        return this;
    }

    public ItemMeta.Spigot spigot()
    {
        return getItemStack().getItemMeta().spigot();
    }

    public boolean compare(ItemStack itemStack)
    {
        return plugin.getInventoryLibrary().doItemsMatch(getItemStack(), itemStack);
    }

    public void replaceInLore(String placeholder, String value)
    {
        if (getItemMeta().hasLore())
        {
            List<String> lore = getItemMeta().getLore();
            for (String line : lore)
            {
                line.replace(placeholder, value);
            }
            setLore(lore);
        }
    }

    public void replaceInDisplayName(String placeholder, String value)
    {
        if (getItemMeta().hasDisplayName())
        {
            String displayName = getItemMeta().getDisplayName();
            displayName.replace(placeholder, value);
            setDisplayName(displayName);
        }
    }

}
