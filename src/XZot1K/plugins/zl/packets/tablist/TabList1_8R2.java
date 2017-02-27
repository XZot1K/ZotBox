package XZot1K.plugins.zl.packets.tablist;

import XZot1K.plugins.zl.ZotLib;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R2.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class TabList1_8R2 implements TabList
{

    private ZotLib plugin = ZotLib.getInstance();

    public void sendCustomTabList(Player player, String headerText, String footerText)
    {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        PlayerConnection playerConnection = craftPlayer.getHandle().playerConnection;
        IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + plugin.getGeneralLibrary().color(headerText) + "\"}");
        IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + plugin.getGeneralLibrary().color(footerText) + "\"}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(header);
        try
        {
            Field field = packet.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(packet, footer);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        playerConnection.sendPacket(packet);
    }

    public void sendCustomTabListToAll(String headerText, String footerText)
    {
        for (Player player : plugin.getServer().getOnlinePlayers())
        {
            sendCustomTabList(player, headerText, footerText);
        }
    }

}
