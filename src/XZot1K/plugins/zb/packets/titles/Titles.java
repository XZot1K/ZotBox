package XZot1K.plugins.zb.packets.titles;

import org.bukkit.entity.Player;

public interface Titles
{

    void sendTitle(Player player, String text, int fadein, int displaytime, int fadeout);

    void sendSubTitle(Player player, String text, int fadein, int displaytime, int fadeout);

    void removeTitle(Player player);

    void resetTitle(Player player);

}
