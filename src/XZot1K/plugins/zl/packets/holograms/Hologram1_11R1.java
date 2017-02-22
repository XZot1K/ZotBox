package XZot1K.plugins.zl.packets.holograms;

import XZot1K.plugins.zl.ZotLib;
import XZot1K.plugins.zl.utils.SerializableLocation;
import net.minecraft.server.v1_11_R1.EntityArmorStand;
import net.minecraft.server.v1_11_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_11_R1.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Hologram1_11R1 implements Hologram
{

    private ZotLib plugin = ZotLib.getInstance();
    private List<EntityArmorStand> entityList;
    private SerializableLocation location;
    private List<String> lines;
    private double lineSpread;

    public Hologram1_11R1(List<String> lines, double lineSpread, Location location)
    {
        setLines(lines);
        setLocation(location);
        setEntityList(new ArrayList<>());
        setLineSpread(lineSpread);
    }

    public Hologram showPlayer(Player p, int displayTime)
    {
        showPlayer(p);
        plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, () -> hidePlayer(p), displayTime);
        return this;
    }


    public Hologram showAll(int displayTime)
    {
        showAll();
        plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, this::hideAll, displayTime);
        return this;
    }

    public Hologram showPlayer(Player p)
    {
        for (EntityArmorStand armor : getEntityList())
        {
            PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(armor);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        }

        return this;
    }

    public Hologram hidePlayer(Player p)
    {
        for (EntityArmorStand armor : getEntityList())
        {
            PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(armor.getId());
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        }

        return this;
    }

    public Hologram showAll()
    {
        for (Player player : plugin.getServer().getOnlinePlayers())
        {
            for (EntityArmorStand armor : getEntityList())
            {
                PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(armor);
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        }

        return this;
    }

    public Hologram hideAll()
    {
        for (Player player : plugin.getServer().getOnlinePlayers())
        {
            for (EntityArmorStand armor : getEntityList())
            {
                PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(armor.getId());
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        }

        return this;
    }

    public Hologram create()
    {
        Location location = getLocation().subtract(0, getLineSpread(), 0).add(0, getLineSpread() * getLines().size(), 0);
        for (String line : getLines())
        {
            EntityArmorStand entity = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle(),
                    location.getX(), location.getY(), location.getZ());
            entity.setCustomName(plugin.getGeneralLibrary().color(line));
            entity.setCustomNameVisible(true);
            entity.setInvisible(true);
            entity.setNoGravity(false);
            getEntityList().add(entity);
            location = location.subtract(0, getLineSpread(), 0);
        }

        return this;
    }

    // Getters & Setters
    public Location getLocation()
    {
        return location.asBukkitLocation();
    }

    public Hologram setLocation(Location location)
    {
        this.location = new SerializableLocation(location);
        return this;
    }

    public List<String> getLines()
    {
        return lines;
    }

    public Hologram setLines(List<String> lines)
    {
        this.lines = lines;
        return this;
    }

    public List<EntityArmorStand> getEntityList()
    {
        return entityList;
    }

    public void setEntityList(List<EntityArmorStand> entityList)
    {
        this.entityList = entityList;
    }

    public Hologram setLineSpread(double lineSpread)
    {
        this.lineSpread = lineSpread;
        return this;
    }

    public double getLineSpread()
    {
        return lineSpread;
    }

}
