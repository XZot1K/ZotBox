package XZot1K.plugins.zb.utils.cooldowns.events;

import XZot1K.plugins.zb.utils.cooldowns.CoolDown;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CooldownFinishEvent extends Event implements Cancellable
{

    private static final HandlerList handlers = new HandlerList();
    private CoolDown coolDown;
    private boolean cancelled;

    public CooldownFinishEvent(CoolDown coolDown)
    {
        this.coolDown = coolDown;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }

    public boolean isCancelled()
    {
        return cancelled;
    }

    public void setCancelled(boolean cancel)
    {
        cancelled = cancel;
    }

    public HandlerList getHandlers()
    {
        return handlers;
    }

    public CoolDown getCoolDown()
    {
        return coolDown;
    }

}
