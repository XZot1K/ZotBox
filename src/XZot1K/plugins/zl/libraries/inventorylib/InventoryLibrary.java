package XZot1K.plugins.zl.libraries.inventorylib;

import XZot1K.plugins.zl.Manager;
import XZot1K.plugins.zl.ZotLib;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class InventoryLibrary
{

    private ZotLib plugin = Manager.getPlugin();

    /**
     * Remove a specific item from a player's inventory.
     *
     * @param inventory The inventory you want to remove the item from.
     * @param itemStack The itemstack you want to remove.
     */
    public void removeItem(Inventory inventory, ItemStack itemStack, int amount)
    {
        int left = amount;

        for (int i = 0; i < inventory.getSize(); i++)
        {
            ItemStack is = inventory.getItem(i);

            if (is != null && doItemsMatch(is, itemStack))
            {
                if (left >= is.getAmount())
                {
                    inventory.clear(i);
                    left -= is.getAmount();
                } else
                {
                    if (left <= 0) break;
                    is.setAmount(is.getAmount() - left);
                    left = 0;
                }
            }
        }

        if (inventory.getHolder() instanceof Player)
        {
            ((Player) inventory.getHolder()).updateInventory();
        }
    }

    /**
     * Creates an new CustomItem, which is used to create new ItemStacks /w meta at ease.
     *
     * @param materialName The material name you want the item to be.
     * @param amount       The amount you want the stack size to be.
     * @param durability   The durability you want the item to be.
     * @return A new CustomItem object.
     */
    public CustomItem createCustomItemStack(String materialName, int amount, short durability)
    {
        return new CustomItem(materialName, amount, durability);
    }

    /**
     * Creates an new CustomItem, which is used to create new ItemStacks /w meta at ease.
     *
     * @param material   The material you want the item to be.
     * @param amount     The amount you want the stack size to be.
     * @param durability The durability you want the item to be.
     * @return A new CustomItem object.
     */
    public CustomItem createCustomItemStack(Material material, int amount, short durability)
    {
        return new CustomItem(material, amount, durability);
    }

    /**
     * Creates an new CustomItem, which is used to create new ItemStacks /w meta at ease.
     *
     * @param materialId The material id you want the item to be.
     * @param amount     The amount you want the stack size to be.
     * @param durability The durability you want the item to be.
     * @return A new CustomItem object.
     */
    public CustomItem createCustomItemStack(int materialId, int amount, short durability)
    {
        return new CustomItem(materialId, amount, durability);
    }

    /**
     * Creates a new CustomInventory, which is used to make a new Inventory (Custom GUI) with ease.
     *
     * @param inventoryHolder The inventory holder you want to hold the inventory.
     * @param inventoryTitle  The title of the inventory (Color Codes Enabled).
     * @param inventorySize   The size you want the inventory to be (Must be divisible by 9 and less than or equal to 54).
     * @return A new CustomInventory object.
     */
    public CustomInventory createCustomInventory(InventoryHolder inventoryHolder, String inventoryTitle, int inventorySize)
    {
        return new CustomInventory(inventoryHolder, inventoryTitle, inventorySize);
    }

    /**
     * The "isEmpty" method allows you to check if the fed inventory is empty or not.
     *
     * @param inventory The inventory you want to check.
     * @return Whether the inventory is full or not.
     */
    public boolean isFull(Inventory inventory)
    {
        return inventory.firstEmpty() == -1;
    }

    /**
     * Returns if the given inventory is empty or not.
     *
     * @param inventory Inventory you want to check.
     * @return If the inventory is empty.
     */
    public boolean isEmpty(Inventory inventory)
    {
        for (int i = 0; i < inventory.getSize(); i++)
        {
            ItemStack slot = inventory.getItem(i);
            if (slot != null || slot.getType() != Material.AIR)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * The "doItemsMatch" method allows you to check if two item stacks match EXACTLY (This also works with no item meta).
     *
     * @param item1 The first item you want to compare.
     * @param item2 The second item you want to compare.
     * @return Whether the two items are the same (Checks ItemMeta if exists).
     */
    public boolean doItemsMatch(ItemStack item1, ItemStack item2)
    {
        if (item1.getType() == item2.getType() && item1.getDurability() == item2.getDurability())
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
            } else
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Allows you to get a player's head!
     *
     * @param playerName  The player's name that you want a head of.
     * @param displayName The display name of the player head.
     * @param lore        The lore of the player head.
     * @param amount      The amount of the player's head you want.
     * @return The new sexy player head you created in one line of code!
     */
    public ItemStack getPlayerHead(String playerName, String displayName, List<String> lore, int amount)
    {
        CustomItem customItem = new CustomItem(Material.SKULL_ITEM, amount, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) customItem.getItemMeta();
        meta.setOwner(playerName);
        meta.setDisplayName(plugin.getGeneralLibrary().color(displayName));
        meta.setLore(plugin.getGeneralLibrary().colorLines(lore));
        customItem.setItemMeta(meta);
        return customItem.getItemStack();
    }

}
