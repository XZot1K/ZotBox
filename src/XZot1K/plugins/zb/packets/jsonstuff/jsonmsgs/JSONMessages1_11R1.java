package XZot1K.plugins.zb.packets.jsonstuff.jsonmsgs;

import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class JSONMessages1_11R1 implements JSONMessages
{

    public void sendJSONMessage(Player player, String JSONString)
    {
        IChatBaseComponent comp = IChatBaseComponent.ChatSerializer.a(JSONString);
        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(comp);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutChat);
    }

}
