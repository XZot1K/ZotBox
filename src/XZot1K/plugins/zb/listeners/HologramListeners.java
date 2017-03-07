package XZot1K.plugins.zb.listeners;

import XZot1K.plugins.zb.ZotBox;
import XZot1K.plugins.zb.packets.holograms.Hologram;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class HologramListeners implements Listener
{

    private ZotBox plugin = ZotBox.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        for (Hologram hologram : plugin.getHologramManager().getHolograms())
        {
            hologram.showPlayer(e.getPlayer());
        }
    }

}
