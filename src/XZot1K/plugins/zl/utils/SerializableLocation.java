package XZot1K.plugins.zl.utils;

import XZot1K.plugins.zl.Manager;
import XZot1K.plugins.zl.ZotLib;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.Serializable;

public class SerializableLocation implements Serializable
{
    private static final long serialVersionUID = 1L;
    private ZotLib plugin = Manager.getPlugin();
    private double x, y, z;
    private float yaw, pitch;
    private String worldName;

    public SerializableLocation(Location location)
    {
        if (location == null)
        {
            return;
        }
        setX(location.getX());
        setY(location.getY());
        setZ(location.getZ());
        setYaw(location.getYaw());
        setPitch(location.getPitch());
        setWorldName(location.getWorld().getName());
        getWorldName().equals(location.getWorld() == null ? "null" : location.getWorld().getName());
    }

    public Location asBukkitLocation()
    {
        World world = plugin.getServer().getWorld(getWorldName());
        Location location = new Location(world, getX(), getY(), getZ(), getYaw(), getPitch());
        if (location != null)
        {
            return location;
        }

        return null;
    }

    public String getWorldName()
    {
        return worldName;
    }

    public void setWorldName(String worldName)
    {
        this.worldName = worldName;
    }

    public float getPitch()
    {
        return pitch;
    }

    public void setPitch(float pitch)
    {
        this.pitch = pitch;
    }

    public float getYaw()
    {
        return yaw;
    }

    public void setYaw(float yaw)
    {
        this.yaw = yaw;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getZ()
    {
        return z;
    }

    public void setZ(double z)
    {
        this.z = z;
    }

}
