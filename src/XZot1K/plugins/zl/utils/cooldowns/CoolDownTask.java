package XZot1K.plugins.zl.utils.cooldowns;

import XZot1K.plugins.zl.Manager;
import XZot1K.plugins.zl.ZotLib;
import org.bukkit.scheduler.BukkitRunnable;

public class CoolDownTask extends BukkitRunnable
{

    private ZotLib plugin = Manager.getPlugin();
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
            coolDown.setDuration(coolDown.getDuration() - 1);
        } else
        {
            coolDown.setFinished(true);
            cancel();
        }
    }

}
