package XZot1K.plugins.zb.utils.holograms;

import XZot1K.plugins.zb.ZotBox;
import XZot1K.plugins.zb.packets.holograms.Hologram;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HologramTask extends BukkitRunnable
{

    private ZotBox plugin = ZotBox.getInstance();
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
                for (int i = -1; ++i < dir.listFiles().length; )
                {
                    File file = dir.listFiles()[i];
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

        for (int i = -1; ++i < plugin.getHologramManager().getHolograms().size(); )
        {
            Hologram hologram = plugin.getHologramManager().getHolograms().get(i);
            if (getRecentLoadedIds().contains(hologram.getId()))
            {
                continue;
            }

            if (saveHolograms)
            {
                plugin.getHologramManager().saveHologram(hologram);
            }

            hologram.hideAll().create().showAll();
        }

        getRecentLoadedIds().clear();
    }

    public List<String> getRecentLoadedIds()
    {
        return recentLoadedIds;
    }

}
