package XZot1K.plugins.zl.packets.actionbars;

import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;

public class ActionBars1_11R1 implements ActionBars
{

	@Override
	public void sendActionbar(Player p, String message)
	{

		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + message + "\"}");

		PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);

		((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
	}

}
