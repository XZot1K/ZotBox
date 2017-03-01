package XZot1K.plugins.zl.utils.holograms;

import XZot1K.plugins.zl.ZotLib;
import XZot1K.plugins.zl.packets.holograms.Hologram;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HologramTask extends BukkitRunnable
{

    private ZotLib plugin = ZotLib.getInstance();
    private List<String> recentLoadedIds;
    private boolean loadHolograms, saveHolograms;

    public HologramTask()
    {
        loadHolograms = plugin.getConfig().getBoolean("auto-load-holograms");
        saveHolograms = plugin.getConfig().getBoolean("auto-save-holograms");
        recentLoadedIds = new ArrayList<>();
    }

    @Override
    public void run()
    {

        if (loadHolograms)
        {
            File dir = new File(plugin.getDataFolder(), "/holograms");
            if (dir.exists() && dir.isDirectory())
            {
                for (File file : dir.listFiles())
                {
                    if (file.getName().toLowerCase().endsWith(".yml"))
                    {
                        Hologram hologram = plugin.getHologramManager().loadHologram(file);
                        if (hologram != null)
                        {
                            getRecentLoadedIds().add(hologram.getId());
                        }
                    }
                }
            }
        }

        if (saveHolograms)
        {
            for (Hologram hologram : plugin.getHologramManager().getHolograms())
            {
                if (!getRecentLoadedIds().contains(hologram.getId()))
                {
                    hologram.hideAll();
                    hologram.showAll();
                    plugin.getHologramManager().saveHologram(hologram);
                }
            }
        }

        getRecentLoadedIds().clear();
    }

    public List<String> getRecentLoadedIds()
    {
        return recentLoadedIds;
    }

}
