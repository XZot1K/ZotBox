package XZot1K.plugins.zb.packets.actionbars;

import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBars1_12R1 implements ActionBars
{

    @Override
    public void sendActionbar(Player p, String message)
    {
        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.ACTIONBAR,
                IChatBaseComponent.ChatSerializer.a(
                        "{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message) + "\"}"));
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
    }

}
