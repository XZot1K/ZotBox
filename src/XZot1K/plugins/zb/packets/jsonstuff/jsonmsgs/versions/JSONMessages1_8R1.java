package XZot1K.plugins.zb.packets.jsonstuff.jsonmsgs.versions;

import XZot1K.plugins.zb.packets.jsonstuff.jsonmsgs.JSONMessages;
import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class JSONMessages1_8R1 implements JSONMessages
{

    public void sendJSONMessage(Player player, String JSONString)
    {
        IChatBaseComponent comp = ChatSerializer.a(JSONString);
        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(comp);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutChat);
    }

}
