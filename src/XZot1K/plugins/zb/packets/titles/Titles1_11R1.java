package XZot1K.plugins.zb.packets.titles;

import XZot1K.plugins.zb.ZotBox;
import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Titles1_11R1 implements Titles
{

	private ZotBox plugin = ZotBox.getInstance();

	@Override
	public void sendTitle(Player player, String text, int fadein, int displaytime, int fadeout)
	{
		text = plugin.getGeneralLibrary().color(text);
		fadein = 20 * fadein;
		displaytime = 20 * displaytime;
		fadeout = 20 * fadeout;

		PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
				IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\"}"), fadein, displaytime, fadeout);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
	}

	@Override
	public void sendSubTitle(Player player, String text, int fadein, int displaytime, int fadeout)
	{
		text = plugin.getGeneralLibrary().color(text);
		fadein = 20 * fadein;
		displaytime = 20 * displaytime;
		fadeout = 20 * fadeout;

		PacketPlayOutTitle subtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
				IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\"}"), fadein, displaytime, fadeout);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitle);
	}

	@Override
	public void removeTitle(Player player)
	{
		PacketPlayOutTitle clear = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.CLEAR,
				IChatBaseComponent.ChatSerializer.a("{\"text\":\"Clear\"}"), 20, 40, 20);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(clear);
	}

	@Override
	public void resetTitle(Player player)
	{
		PacketPlayOutTitle reset = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET,
				IChatBaseComponent.ChatSerializer.a("{\"text\":\"Reset\"}"), 20, 40, 20);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(reset);
	}

}
