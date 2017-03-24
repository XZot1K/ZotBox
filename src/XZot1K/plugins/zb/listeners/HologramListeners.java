package XZot1K.plugins.zb.listeners;

import XZot1K.plugins.zb.ZotBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class HologramListeners implements Listener
{

    private ZotBox plugin = ZotBox.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        for (int i = -1; ++i < plugin.getHologramManager().getHolograms().size(); )
        {
            plugin.getHologramManager().getHolograms().get(i).showPlayer(e.getPlayer());
        }
    }

}
