package XZot1K.plugins.zl.packets.jsonmsgs;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class JSONMessages1_8R3 implements JSONMessages
{

    public void sendJSONMessage(Player player, String JSONString)
    {
        IChatBaseComponent comp = IChatBaseComponent.ChatSerializer.a(JSONString);
        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(comp);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutChat);
    }

}
