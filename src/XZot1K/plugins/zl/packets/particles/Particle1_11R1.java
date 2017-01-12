package XZot1K.plugins.zl.packets.particles;

import XZot1K.plugins.zl.packets.particles.Particles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_11_R1.EnumParticle;
import net.minecraft.server.v1_11_R1.PacketPlayOutWorldParticles;

public class Particle1_11R1 implements Particles
{

    public void displayParticle(Player player, Location loc, float x, float y, float z, int speed, String effect, int amount)
    {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.valueOf(effect), false,
                (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), x, y, z, speed, amount);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public void broadcastParticle(Location loc, float x, float y, float z, int speed, String effect, int amount)
    {
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.valueOf(effect), false,
                (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), x, y, z, speed, amount);
        for (Player player : Bukkit.getOnlinePlayers())
        {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }

}
