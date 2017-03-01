package XZot1K.plugins.zl.packets.holograms;

import XZot1K.plugins.zl.ZotLib;
import XZot1K.plugins.zl.utils.SerializableLocation;
import net.minecraft.server.v1_8_R2.EntityArmorStand;
import net.minecraft.server.v1_8_R2.NBTTagCompound;
import net.minecraft.server.v1_8_R2.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R2.PacketPlayOutSpawnEntityLiving;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Hologram1_8R2 implements Hologram
{

    private ZotLib plugin = ZotLib.getInstance();
    private List<EntityArmorStand> entityList;
    private SerializableLocation location;
    private List<String> lines;
    private double lineSpread;
    private String id;

    public Hologram1_8R2(String id, List<String> lines, double lineSpread, Location location)
    {
        setId(id);
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
        getEntityList().clear();
        Location location = getLocation().subtract(0, getLineSpread(), 0).add(0, getLineSpread() * getLines().size(), 0);
        for (String line : getLines())
        {
            EntityArmorStand entity = new EntityArmorStand(((CraftWorld) location.getWorld()).getHandle(),
                    location.getX(), location.getY(), location.getZ());
            entity.setCustomName(plugin.getGeneralLibrary().color(line));
            entity.setCustomNameVisible(true);
            entity.setInvisible(true);
            entity.setGravity(false);
            entity.setSmall(true);

            NBTTagCompound compoundTag = new NBTTagCompound();
            entity.c(compoundTag);
            compoundTag.setBoolean("Marker", true);
            entity.f(compoundTag);

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

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}
