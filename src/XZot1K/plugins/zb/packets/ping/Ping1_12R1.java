package XZot1K.plugins.zb.packets.ping;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Ping1_12R1 implements Ping
{

    public int getPing(Player player)
    {
        return ((CraftPlayer) player).getHandle().ping;
    }

}
