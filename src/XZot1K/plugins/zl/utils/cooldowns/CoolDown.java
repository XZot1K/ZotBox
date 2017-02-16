package XZot1K.plugins.zl.utils.cooldowns;

import XZot1K.plugins.zl.Manager;
import XZot1K.plugins.zl.ZotLib;

public class CoolDown
{

    private ZotLib plugin = Manager.getPlugin();
    private String id, extraInformation;
    private int duration;
    private boolean isFinished;
    private CoolDownTask task;

    public CoolDown(String id, int duration, boolean notify)
    {
        setId(id);
        setDuration(duration);
        setFinished(false);
        if (notify)
        {
            plugin.getGeneralLibrary().sendConsoleMessage("&aA cool-down with the id &e" + getId() + " &a has been created.");
        }
    }

    public void stop(boolean notify)
    {
        if (getTask() != null)
        {
            getTask().cancel();
            setTask(null);
            if (notify)
            {
                plugin.getGeneralLibrary().sendConsoleMessage("&aSuccessfully stopped the cool-down task for the &e" + getId() + " &acool-down.");
            }
        } else
        {
            if (notify)
            {
                plugin.getGeneralLibrary().sendConsoleMessage("&cFailed to stop the cool-down task for the &e" + getId()
                        + " &ccool-down, due to the task being invalid or not running.");
            }
        }
    }

    public void start(boolean notify)
    {
        if (getTask() == null)
        {
            setTask(new CoolDownTask(this));
            getTask().runTaskTimerAsynchronously(plugin, 0, 20);
            if (notify)
            {
                plugin.getGeneralLibrary().sendConsoleMessage("&aSuccessfully started the cool-down task for the &e" + getId() + " &acool-down.");
            }
        } else
        {
            if (notify)
            {
                plugin.getGeneralLibrary().sendConsoleMessage("&cFailed to start the cool-down task for the &e" + getId()
                        + " &ccool-down, due to it already running.");
            }
        }
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public boolean isFinished()
    {
        return isFinished;
    }

    public void setFinished(boolean finished)
    {
        isFinished = finished;
    }

    public CoolDownTask getTask()
    {
        return this.task;
    }

    public void setTask(CoolDownTask coolDownTask)
    {
        this.task = coolDownTask;
    }

    public String getExtraInformation()
    {
        return extraInformation;
    }

    public void setExtraInformation(String extraInformation)
    {
        this.extraInformation = extraInformation;
    }

}
