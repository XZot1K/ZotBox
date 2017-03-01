package XZot1K.plugins.zl.listeners;

import XZot1K.plugins.zl.ZotLib;
import XZot1K.plugins.zl.packets.holograms.Hologram;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class HologramListeners implements Listener
{

    private ZotLib plugin = ZotLib.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        for (Hologram hologram : plugin.getHologramManager().getHolograms())
        {
            hologram.showPlayer(e.getPlayer());
        }
    }

}
