package XZot1K.plugins.zl.packets.jsonstuff.jsonitems;

import net.minecraft.server.v1_7_R4.NBTTagCompound;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class JSONItems1_7R4 implements JSONItems
{
    @Override
    public String getJSONItem(ItemStack itemStack)
    {
        net.minecraft.server.v1_7_R4.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound compound = new NBTTagCompound();
        compound = nmsItemStack.save(compound);
        return compound.toString();
    }
}
