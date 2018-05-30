package XZot1K.plugins.zb.listeners;

import XZot1K.plugins.zb.ZotBox;
import XZot1K.plugins.zb.packets.holograms.Hologram;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

public class HologramListeners implements Listener
{

    private ZotBox plugin = ZotBox.getInstance();

    @EventHandler(ignoreCancelled = true)
    public void onChunkLoad(ChunkLoadEvent e)
    {
        for (int i = -1; ++i < plugin.getHologramManager().getHolograms().size(); )
        {
            Hologram hologram = plugin.getHologramManager().getHolograms().get(i);
            Location location = hologram.getLocation();
            if (location.getChunk().getX() == e.getChunk().getX() && location.getChunk().getZ() == e.getChunk().getZ())
                hologram.showAll();
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onChunkUnload(ChunkUnloadEvent e)
    {
        for (int i = -1; ++i < plugin.getHologramManager().getHolograms().size(); )
        {
            Hologram hologram = plugin.getHologramManager().getHolograms().get(i);
            Location location = hologram.getLocation();
            if (location.getChunk().getX() == e.getChunk().getX() && location.getChunk().getZ() == e.getChunk().getZ())
                hologram.hideAll();
        }
    }

}
