package XZot1K.plugins.zb.packets.ping.versions;

import XZot1K.plugins.zb.packets.ping.Ping;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Ping1_10R1 implements Ping
{

    public int getPing(Player player)
    {
        return ((CraftPlayer) player).getHandle().ping;
    }

}
