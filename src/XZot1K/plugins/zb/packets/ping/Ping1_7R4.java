package XZot1K.plugins.zb.packets.ping;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Ping1_7R4 implements Ping
{

    public int getPing(Player player)
    {
        return ((CraftPlayer) player).getHandle().ping;
    }

}
