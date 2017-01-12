package XZot1K.plugins.zl.libraries;

import XZot1K.plugins.zl.Manager;
import XZot1K.plugins.zl.ZotLib;
import XZot1K.plugins.zl.packets.actionbars.*;
import XZot1K.plugins.zl.packets.particles.*;
import XZot1K.plugins.zl.packets.titles.*;

public class PacketLibrary
{
    private ZotLib plugin = Manager.getPlugin();
    private Titles titleManager;
    private ActionBars actionBarManager;
    private Particles particleManager;

    // The "setupPackets" is not something you should mess with unless you need to reload ZotLib's packets.
    public void setupPackets()
    {
        switch (plugin.getServerVersion())
        {
            case "v1_11_R1":
                setTitleManager(new Titles1_11R1());
                setActionBarManager(new ActionBars1_11R1());
                setParticleManager(new Particle1_11R1());
                plugin.getGeneralLibrary().sendConsoleMessage("&c" + plugin.getServerVersion() + " &ddetected setting up packets.");
                break;
            case "v1_10_R1":
                setTitleManager(new Titles1_10R1());
                setActionBarManager(new ActionBars1_10R1());
                setParticleManager(new Particle1_10R1());
                plugin.getGeneralLibrary().sendConsoleMessage("&c" + plugin.getServerVersion() + " &ddetected setting up packets.");
                break;
            case "v1_9_R2":
                setTitleManager(new Titles1_9R2());
                setActionBarManager(new ActionBars1_9R2());
                setParticleManager(new Particle1_9R2());
                plugin.getGeneralLibrary().sendConsoleMessage("&c" + plugin.getServerVersion() + " &ddetected setting up packets.");
                break;
            case "v1_9_R1":
                setTitleManager(new Titles1_9R1());
                setActionBarManager(new ActionBars1_9R1());
                setParticleManager(new Particle1_9R1());
                plugin.getGeneralLibrary().sendConsoleMessage("&c" + plugin.getServerVersion() + " &ddetected setting up packets.");
                break;
            case "v1_8_R3":
                setTitleManager(new Titles1_8R3());
                setActionBarManager(new ActionBars1_8R3());
                setParticleManager(new Particle1_8R3());
                plugin.getGeneralLibrary().sendConsoleMessage("&c" + plugin.getServerVersion() + " &ddetected setting up packets.");
                break;
            case "v1_8_R2":
                setTitleManager(new Titles1_8R2());
                setActionBarManager(new ActionBars1_8R2());
                setParticleManager(new Particle1_8R2());
                plugin.getGeneralLibrary().sendConsoleMessage("&c" + plugin.getServerVersion() + " &ddetected setting up packets.");
                break;
            case "v1_8_R1":
                setTitleManager(new Titles1_8R1());
                setActionBarManager(new ActionBars1_8R1());
                setParticleManager(new Particle1_8R1());
                plugin.getGeneralLibrary().sendConsoleMessage("&c" + plugin.getServerVersion() + " &ddetected setting up packets.");
                break;
            default:
                plugin.getGeneralLibrary().sendConsoleMessage("&cThere was a issue trying to setup packets for &d" +
                        plugin.getServerVersion() + " &cbecause it is not supported by &bZotLib &c.");
                break;
        }
    }

    // The "getTitleManager" method is what you can use to send, reset, or remove titles for 1.8+.
    public Titles getTitleManager()
    {
        return titleManager;
    }

    private void setTitleManager(Titles titleManager)
    {
        this.titleManager = titleManager;
    }

    // The "getActionBar" method is what you can use to send messages to a player's action bar for 1.8+.
    public ActionBars getActionBarManager()
    {
        return actionBarManager;
    }

    private void setActionBarManager(ActionBars actionBarManager)
    {
        this.actionBarManager = actionBarManager;
    }

    // The "getParticleManager" method is what you can use to send particles to players for 1.8+.
    public Particles getParticleManager()
    {
        return particleManager;
    }

    private void setParticleManager(Particles particleManager)
    {
        this.particleManager = particleManager;
    }
}
