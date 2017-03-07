package XZot1K.plugins.zb.packets.tablist;

import XZot1K.plugins.zb.ZotBox;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class TabList1_8R3 implements TabList
{

    private ZotBox plugin = ZotBox.getInstance();

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
