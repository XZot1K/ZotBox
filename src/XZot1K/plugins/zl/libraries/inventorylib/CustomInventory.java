package XZot1K.plugins.zl.libraries.inventorylib;

import XZot1K.plugins.zl.Manager;
import XZot1K.plugins.zl.ZotLib;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class CustomInventory
{
    private ZotLib plugin = Manager.getPlugin();
    private Inventory inventory;

    public CustomInventory(InventoryHolder inventoryHolder, String inventoryTitle, int inventorySize)
    {
        setInventory(plugin.getServer().createInventory(inventoryHolder, inventorySize, plugin.getGeneralLibrary().color(inventoryTitle)));
    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public void setInventory(Inventory inventory)
    {
        this.inventory = inventory;
    }

    public void setSlot(int slot, ItemStack itemStack)
    {
        getInventory().setItem(slot, itemStack);
    }

    public ItemStack getSlot(int slot)
    {
        return getInventory().getItem(slot);
    }

    public void removeItem(ItemStack itemStack)
    {
        getInventory().removeItem(itemStack);
    }

    public void fillEmptySlots(ItemStack itemStack)
    {
        for (int i = 0; i < getInventory().getSize(); i++)
        {
            ItemStack item = getInventory().getItem(i);
            if (item == null || item.getType() == Material.AIR)
            {
                setSlot(i, itemStack);
            }
        }
    }

    public boolean isFull()
    {
        return getInventory().firstEmpty() == -1;
    }

    public boolean isEmpty()
    {
        for (int i = 0; i < getInventory().getSize(); i++)
        {
            ItemStack slot = getSlot(i);
            if (slot != null || slot.getType() != Material.AIR)
            {
                return false;
            }
        }
        return true;
    }

}
