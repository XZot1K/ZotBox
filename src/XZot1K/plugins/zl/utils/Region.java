package XZot1K.plugins.zl.utils;

import XZot1K.plugins.zl.ZotLib;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Region
{

    private ZotLib plugin = ZotLib.getInstance();
    private SerializableLocation point1, point2;
    private String regionId;

    public Region(String regionId, Location point1, Location point2)
    {
        setRegionId(regionId);
        setPoint1(point1);
        setPoint2(point2);
    }

    public boolean isInRegion(Location location)
    {
        return plugin.getLocationLibrary().isLocationBetweenTwoLocations(location, getPoint1(), getPoint2());
    }

    public void delete(String deletePath, boolean notifyConsole)
    {
        try
        {
            File file = new File(deletePath + getRegionId() + ".yml");
            file.delete();

            if (notifyConsole)
            {
                plugin.getGeneralLibrary().sendConsoleMessage("&aThe region with the id &e" + getRegionId() + " &ahas been successfully deleted!");
            }
        } catch (Exception e)
        {
            if (notifyConsole)
            {
                e.printStackTrace();
                plugin.getGeneralLibrary().sendConsoleMessage("&cThere was a issue trying to delete the region with the id &e" + getRegionId() + "&c.");
            }
        }
    }

    public void save(String savePath, boolean notifyConsole)
    {
        try
        {
            File file = new File(savePath + getRegionId() + ".yml");
            FileConfiguration yaml = YamlConfiguration.loadConfiguration(file);

            // save region id
            yaml.set("region-id", getRegionId());

            // save point 1.
            Location point1 = getPoint1();
            yaml.set("point-1.world", point1.getWorld().getName());
            yaml.set("point-1.x", point1.getX());
            yaml.set("point-1.y", point1.getY());
            yaml.set("point-1.z", point1.getZ());

            // save point 2.
            Location point2 = getPoint2();
            yaml.set("point-2.world", point2.getWorld().getName());
            yaml.set("point-2.x", point2.getX());
            yaml.set("point-2.y", point2.getY());
            yaml.set("point-2.z", point2.getZ());

            // save file.
            yaml.save(file);

            if (notifyConsole)
            {
                plugin.getGeneralLibrary().sendConsoleMessage("&aThe region with the id &e" + getRegionId() + " &ahas been successfully saved!");
            }
        } catch (Exception e)
        {
            if (notifyConsole)
            {
                e.printStackTrace();
                plugin.getGeneralLibrary().sendConsoleMessage("&cThere was a issue trying to save the region with the id &e" + getRegionId() + "&c.");
            }
        }
    }

    public Location getPoint1()
    {
        return point1.asBukkitLocation();
    }

    public void setPoint1(Location point1)
    {
        this.point1 = new SerializableLocation(point1);
    }

    public Location getPoint2()
    {
        return point2.asBukkitLocation();
    }

    public void setPoint2(Location point2)
    {
        this.point2 = new SerializableLocation(point2);
    }

    public String getRegionId()
    {
        return regionId;
    }

    public void setRegionId(String regionId)
    {
        this.regionId = regionId;
    }

}
