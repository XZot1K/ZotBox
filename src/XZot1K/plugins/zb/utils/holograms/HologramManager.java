package XZot1K.plugins.zb.utils.holograms;

import XZot1K.plugins.zb.ZotBox;
import XZot1K.plugins.zb.packets.holograms.Hologram;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HologramManager
{

    private ZotBox plugin = ZotBox.getInstance();
    private List<Hologram> holograms;

    public HologramManager()
    {
        holograms = new ArrayList<>();
    }

    public boolean doesHologramExist(String id)
    {
        for (int i = -1; ++i < getHolograms().size(); )
        {
            if (getHolograms().get(i).getId().equalsIgnoreCase(id))
            {
                return true;
            }
        }

        return false;
    }

    public Hologram getHologram(String id)
    {
        for (int i = -1; ++i < getHolograms().size(); )
        {
            Hologram hologram = getHolograms().get(i);
            if (hologram.getId().equalsIgnoreCase(id))
            {
                return hologram;
            }
        }

        return null;
    }

    public void registerHologram(Hologram hologram)
    {
        if (!getHolograms().contains(hologram))
        {
            getHolograms().add(hologram);
        }

    }

    public void unRegisterHologram(Hologram hologram)
    {
        if (getHolograms().contains(hologram))
        {
            getHolograms().remove(hologram);
        }

    }

    public void saveHologram(Hologram hologram)
    {
        try
        {
            File file = new File(plugin.getDataFolder(), "/holograms/" + hologram.getId() + ".yml");
            FileConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            yaml.set("id", hologram.getId());
            yaml.set("line-spread", hologram.getLineSpread());

            Location location = hologram.getLocation();
            yaml.set("location.world", location.getWorld().getName());
            yaml.set("location.x", location.getX());
            yaml.set("location.y", location.getY());
            yaml.set("location.z", location.getZ());

            yaml.set("lines", hologram.getLines());
            yaml.save(file);
        } catch (Exception e)
        {
            e.printStackTrace();

        }

    }

    public void deleteHologram(Hologram hologram)
    {
        try
        {
            File file = new File(plugin.getDataFolder(), "/holograms/" + hologram.getId() + ".yml");
            file.delete();
            unRegisterHologram(hologram);
            hologram.hideAll();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public Hologram loadHologram(File file)
    {
        try
        {
            FileConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            if (!doesHologramExist(yaml.getString("id")))
            {
                Location location = new Location(plugin.getServer().getWorld(yaml.getString("location.world")),
                        (float) yaml.getDouble("location.x"), (float) yaml.getDouble("location.y"), (float) yaml.getDouble("location.z"));
                Hologram hologram = plugin.getPacketLibrary().createNewHologram(yaml.getString("id"), yaml.getStringList("lines"),
                        yaml.getDouble("line-spread"), location).create().showAll();
                plugin.getHologramManager().registerHologram(hologram);
                return hologram;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    // Getters & Setters
    public List<Hologram> getHolograms()
    {
        return holograms;
    }

}
