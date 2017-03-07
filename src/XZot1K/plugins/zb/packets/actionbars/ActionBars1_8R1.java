package XZot1K.plugins.zb.packets.actionbars;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBars1_8R1 implements ActionBars
{

	@Override
	public void sendActionbar(Player p, String message)
	{
		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");
		PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
	}

}
