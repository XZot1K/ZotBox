package XZot1K.plugins.zb.utils.cooldowns;

import XZot1K.plugins.zb.ZotBox;
import XZot1K.plugins.zb.utils.cooldowns.events.CooldownDurationChangeEvent;
import XZot1K.plugins.zb.utils.cooldowns.events.CooldownFinishEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class CoolDownTask extends BukkitRunnable
{

    private ZotBox plugin = ZotBox.getInstance();
    private CoolDown coolDown;

    public CoolDownTask(CoolDown coolDown)
    {
        this.coolDown = coolDown;
    }

    @Override
    public void run()
    {
        if (coolDown.getDuration() > 0)
        {
            coolDown.setFinished(false);
            CooldownDurationChangeEvent event = new CooldownDurationChangeEvent(coolDown);
            plugin.getServer().getPluginManager().callEvent(event);
            if (!event.isCancelled())
            {
                coolDown.setDuration(coolDown.getDuration() - 1);
            }
        } else
        {
            CooldownFinishEvent event = new CooldownFinishEvent(coolDown);
            plugin.getServer().getPluginManager().callEvent(event);
            if (!event.isCancelled())
            {
                coolDown.setFinished(true);
            }
            cancel();
        }
    }

}
