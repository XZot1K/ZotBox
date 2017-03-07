package XZot1K.plugins.zb.packets.jsonstuff.jsonitems;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class JSONItems1_8R3 implements JSONItems
{
    @Override
    public String getJSONItem(ItemStack itemStack)
    {
        net.minecraft.server.v1_8_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound compound = new NBTTagCompound();
        compound = nmsItemStack.save(compound);
        return compound.toString();
    }
}
