package XZot1K.plugins.zb.packets.particles.versions;

import XZot1K.plugins.zb.packets.particles.Particles;
import net.minecraft.server.v1_9_R1.EnumParticle;
import net.minecraft.server.v1_9_R1.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Particle1_9R1 implements Particles
{

    @Override
    public void displayParticle(Player player, Location loc, float a, float b, float c, int speed, String effect, int amount)
    {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.valueOf(effect), false,
                (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), a, b, c, speed, amount);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    @Override
    public void broadcastParticle(Location loc, float a, float b, float c, int speed, String effect, int amount)
    {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.valueOf(effect), false,
                (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), a, b, c, speed, amount);
        List<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
        for (int i = -1; ++i < playerList.size(); )
            ((CraftPlayer) playerList.get(i)).getHandle().playerConnection.sendPacket(packet);
    }
}
