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

    /**
     * Not something you should mess with unless you need to reload ZotLib's packets.
     */
    public void setupPackets()
    {
        if (!plugin.getConfig().getBoolean("setup-packets"))
        {
            plugin.getGeneralLibrary().sendConsoleMessage("&cUnable to setup packets because it is disabled in the configuration.");
            return;
        }

        switch (plugin.getServerVersion())
        {
            case "v1_11_R1":
                this.titleManager = new Titles1_11R1();
                this.actionBarManager = new ActionBars1_11R1();
                this.particleManager = new Particle1_11R1();
                plugin.getGeneralLibrary().sendConsoleMessage("&c" + plugin.getServerVersion() + " &ddetected setting up packets.");
                break;
            case "v1_10_R1":
                this.titleManager = new Titles1_10R1();
                this.actionBarManager = new ActionBars1_10R1();
                this.particleManager = new Particle1_10R1();
                plugin.getGeneralLibrary().sendConsoleMessage("&c" + plugin.getServerVersion() + " &ddetected setting up packets.");
                break;
            case "v1_9_R2":
                this.titleManager = new Titles1_9R2();
                this.actionBarManager = new ActionBars1_9R2();
                this.particleManager = new Particle1_9R2();
                plugin.getGeneralLibrary().sendConsoleMessage("&c" + plugin.getServerVersion() + " &ddetected setting up packets.");
                break;
            case "v1_9_R1":
                this.titleManager = new Titles1_9R1();
                this.actionBarManager = new ActionBars1_9R1();
                this.particleManager = new Particle1_9R1();
                plugin.getGeneralLibrary().sendConsoleMessage("&c" + plugin.getServerVersion() + " &ddetected setting up packets.");
                break;
            case "v1_8_R3":
                this.titleManager = new Titles1_8R3();
                this.actionBarManager = new ActionBars1_8R3();
                this.particleManager = new Particle1_8R3();
                plugin.getGeneralLibrary().sendConsoleMessage("&c" + plugin.getServerVersion() + " &ddetected setting up packets.");
                break;
            case "v1_8_R2":
                this.titleManager = new Titles1_8R2();
                this.actionBarManager = new ActionBars1_8R2();
                this.particleManager = new Particle1_8R2();
                plugin.getGeneralLibrary().sendConsoleMessage("&c" + plugin.getServerVersion() + " &ddetected setting up packets.");
                break;
            case "v1_8_R1":
                this.titleManager = new Titles1_8R1();
                this.actionBarManager = new ActionBars1_8R1();
                this.particleManager = new Particle1_8R1();
                plugin.getGeneralLibrary().sendConsoleMessage("&c" + plugin.getServerVersion() + " &ddetected setting up packets.");
                break;
            default:
                plugin.getGeneralLibrary().sendConsoleMessage("&cThere was a issue trying to setup packets for &d" +
                        plugin.getServerVersion() + " &cbecause it is not supported by &bZotLib &c.");
                break;
        }
    }

    /**
     * Allows you to send, reset, or remove titles for 1.8+.
     *
     * @return The TitleManager instance.
     */
    public Titles getTitleManager()
    {
        return titleManager;
    }

    /**
     * Allows you to send messages to a player's action bar for 1.8+.
     *
     * @return The ActionBarManager instance.
     */
    public ActionBars getActionBarManager()
    {
        return actionBarManager;
    }

    /**
     * Allows you to display particles to a player or all players for 1.8+.
     *
     * @return The ActionBarManager instance.
     */
    public Particles getParticleManager()
    {
        return particleManager;
    }

}
