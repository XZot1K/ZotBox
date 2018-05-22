package XZot1K.plugins.zb.packets.ping.versions;

import XZot1K.plugins.zb.packets.ping.Ping;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Ping1_9R2 implements Ping
{

    public int getPing(Player player)
    {
        return ((CraftPlayer) player).getHandle().ping;
    }

}
