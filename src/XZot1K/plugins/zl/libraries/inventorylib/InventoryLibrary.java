package XZot1K.plugins.zl.libraries.inventorylib;

import XZot1K.plugins.zl.Manager;
import XZot1K.plugins.zl.ZotLib;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class InventoryLibrary
{

    private ZotLib plugin = Manager.getPlugin();

    // The "removeItem" method allows you to remove a specific item from an inventory based on the itemstack's amount.
    private void removeItem(Inventory inventory, ItemStack itemStack)
    {
        inventory.removeItem(itemStack);
    }

    // The "createItem" method allows you to create a ItemStack with item meta at ease.
    public CustomItem createCustomItemStack(String materialName, int amount, short durability)
    {
        return new CustomItem(materialName, amount, durability);
    }

    // The "createItem" method allows you to create a ItemStack with item meta at ease.
    public CustomItem createCustomItemStack(Material material, int amount, short durability)
    {
        return new CustomItem(material, amount, durability);
    }

    // The "createItem" method allows you to create a ItemStack with item meta at ease.
    public CustomItem createCustomItemStack(int materialId, int amount, short durability)
    {
        return new CustomItem(materialId, amount, durability);
    }

    // The "createItem" method allows you to create a Inventory (Custom GUI) with ease.
    public CustomInventory createCustomInventory(InventoryHolder inventoryHolder, String inventoryTitle, int inventorySize)
    {
        return new CustomInventory(inventoryHolder, inventoryTitle, inventorySize);
    }

    // The "isEmpty" method allows you to check if the fed inventory is empty or not.
    public boolean isEmpty(Inventory inventory)
    {
        return inventory.firstEmpty() == -1;
    }

    // The "doItemsMatch" method allows you to check if two item stacks match EXACTLY (This also works with no item meta).
    public boolean doItemsMatch(ItemStack item1, ItemStack item2)
    {
        if (item1.hasItemMeta() && item2.hasItemMeta())
        {
            if (item1.getItemMeta().hasDisplayName() && item2.getItemMeta().hasDisplayName())
            {
                if (item1.getItemMeta().getDisplayName().equals(item2.getItemMeta().getDisplayName()))
                {
                    if (item1.getItemMeta().hasLore() && item2.getItemMeta().hasLore())
                    {
                        if (item1.getItemMeta().getLore().equals(item2.getItemMeta().getLore()))
                        {
                            return true;
                        }
                    } else if (!item1.getItemMeta().hasLore() && !item2.getItemMeta().hasLore())
                    {
                        return true;
                    }
                }
            } else if (!item1.getItemMeta().hasDisplayName() && !item2.getItemMeta().hasDisplayName())
            {
                if (item1.getItemMeta().getDisplayName().equals(item2.getItemMeta().getDisplayName()))
                {
                    if (item1.getItemMeta().hasLore() && item2.getItemMeta().hasLore())
                    {
                        if (item1.getItemMeta().getLore().equals(item2.getItemMeta().getLore()))
                        {
                            return true;
                        }
                    } else if (!item1.getItemMeta().hasLore() && !item2.getItemMeta().hasLore())
                    {
                        return true;
                    }
                }
            }
        } else
        {
            if (item1.getType() == item2.getType() && item1.getDurability() == item2.getDurability())
            {
                return true;
            }
        }
        return false;
    }

    // The "doesInventoryTitleMatch" method allows you to check if a inventory's title matches the given text with or without color.
    public boolean doesInventoryTitleMatch(Inventory inventory, String text, boolean stripColor)
    {
        if (stripColor)
        {
            return ChatColor.stripColor(inventory.getTitle()).equals(ChatColor.stripColor(plugin.getGeneralLibrary().color(text)));
        } else
        {
            return inventory.getTitle().equals(plugin.getGeneralLibrary().color(text));
        }
    }

    // The "doesInventoryTitleMatch" method allows you to check if a inventory's title contains the given text with or without color.
    public boolean doesInventoryTitleContain(Inventory inventory, String text, boolean stripColor)
    {
        if (stripColor)
        {
            return ChatColor.stripColor(inventory.getTitle()).contains(ChatColor.stripColor(plugin.getGeneralLibrary().color(text)));
        } else
        {
            return inventory.getTitle().contains(plugin.getGeneralLibrary().color(text));
        }
    }

    // The "doesInventoryTitleMatch" method allows you to check if a inventory's title starts with the given text with or without color.
    public boolean doesInventoryTitleEndWith(Inventory inventory, String text, boolean stripColor)
    {
        if (stripColor)
        {
            return ChatColor.stripColor(inventory.getTitle()).endsWith(ChatColor.stripColor(plugin.getGeneralLibrary().color(text)));
        } else
        {
            return inventory.getTitle().endsWith(plugin.getGeneralLibrary().color(text));
        }
    }

    // The "doesInventoryTitleMatch" method allows you to check if a inventory's title ends with the given text with or without color.
    public boolean doesInventoryTitleStartWith(Inventory inventory, String text, boolean stripColor)
    {
        if (stripColor)
        {
            return ChatColor.stripColor(inventory.getTitle()).startsWith(ChatColor.stripColor(plugin.getGeneralLibrary().color(text)));
        } else
        {
            return inventory.getTitle().startsWith(plugin.getGeneralLibrary().color(text));
        }
    }

}
