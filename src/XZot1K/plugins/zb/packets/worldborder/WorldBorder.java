package XZot1K.plugins.zb.packets.worldborder;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface WorldBorder
{
    void sendWorldBorder(Player player, Location centerPoint, double borderSize, double damageAmount,
                         double damageBuffer, int warningDistance, int warningTime);
}
