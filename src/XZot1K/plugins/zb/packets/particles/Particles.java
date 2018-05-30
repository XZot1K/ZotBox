package XZot1K.plugins.zb.packets.particles;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Particles
{

    void displayParticle(Player player, Location loc, float a, float b, float c, int speed, String effect, int amount);

    void broadcastParticle(Location loc, float a, float b, float c, int speed, String effect, int amount);

}
