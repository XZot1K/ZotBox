package XZot1K.plugins.zl.libraries;

import XZot1K.plugins.zl.ZotLib;
import XZot1K.plugins.zl.packets.actionbars.*;
import XZot1K.plugins.zl.packets.bossbar.BossBar1_7R4;
import XZot1K.plugins.zl.packets.bossbar.BossBar1_8R3;
import XZot1K.plugins.zl.packets.bossbar.NewerBossBar;
import XZot1K.plugins.zl.packets.bossbar.OlderBossBar;
import XZot1K.plugins.zl.packets.holograms.*;
import XZot1K.plugins.zl.packets.jsonstuff.jsonitems.*;
import XZot1K.plugins.zl.packets.jsonstuff.jsonmsgs.*;
import XZot1K.plugins.zl.packets.particles.*;
import XZot1K.plugins.zl.packets.ping.*;
import XZot1K.plugins.zl.packets.tablist.*;
import XZot1K.plugins.zl.packets.titles.*;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;

import java.util.List;

public class PacketLibrary
{
    private ZotLib plugin = ZotLib.getInstance();
    private Titles titleManager;
    private ActionBars actionBarManager;
    private Particles particleManager;
    private JSONMessages JSONMessageSender;
    private JSONItems JSONItemGetter;
    private Ping pingGetter;
    private TabList tabListManager;

    /**
     * Not something you should mess with unless you need to reload ZotLib's packets.
     */
    public void setupPackets()
    {
        long startTime = System.currentTimeMillis();
        if (!plugin.getConfig().getBoolean("setup-packets"))
        {
            plugin.getGeneralLibrary().sendConsoleMessage("&cUnable to setup packets because it is disabled in the configuration. " +
                    "(Took &e" + (System.currentTimeMillis() - startTime) + "ms&c)");
            return;
        }

        switch (plugin.getServerVersion())
        {
            case "v1_11_R1":
                this.titleManager = new Titles1_11R1();
                this.actionBarManager = new ActionBars1_11R1();
                this.particleManager = new Particle1_11R1();
                this.JSONMessageSender = new JSONMessages1_11R1();
                this.pingGetter = new Ping1_11R1();
                this.JSONItemGetter = new JSONItems1_11R1();
                this.tabListManager = new TabList1_11R1();
                plugin.getGeneralLibrary().sendConsoleMessage("&e" + plugin.getServerVersion()
                        + " &adetected successfully set up packets. (Took &e" + (System.currentTimeMillis() - startTime) + "ms&a)");
                break;
            case "v1_10_R1":
                this.titleManager = new Titles1_10R1();
                this.actionBarManager = new ActionBars1_10R1();
                this.particleManager = new Particle1_10R1();
                this.JSONMessageSender = new JSONMessages1_10R1();
                this.pingGetter = new Ping1_10R1();
                this.JSONItemGetter = new JSONItems1_10R1();
                this.tabListManager = new TabList1_10R1();
                plugin.getGeneralLibrary().sendConsoleMessage("&e" + plugin.getServerVersion()
                        + " &adetected successfully set up packets. (Took &e" + (System.currentTimeMillis() - startTime) + "ms&a)");
                break;
            case "v1_9_R2":
                this.titleManager = new Titles1_9R2();
                this.actionBarManager = new ActionBars1_9R2();
                this.particleManager = new Particle1_9R2();
                this.JSONMessageSender = new JSONMessages1_9R2();
                this.pingGetter = new Ping1_9R2();
                this.JSONItemGetter = new JSONItems1_9R2();
                this.tabListManager = new TabList1_9R2();
                plugin.getGeneralLibrary().sendConsoleMessage("&e" + plugin.getServerVersion()
                        + " &adetected successfully set up packets. (Took &e" + (System.currentTimeMillis() - startTime) + "ms&a)");
                break;
            case "v1_9_R1":
                this.titleManager = new Titles1_9R1();
                this.actionBarManager = new ActionBars1_9R1();
                this.particleManager = new Particle1_9R1();
                this.JSONMessageSender = new JSONMessages1_9R1();
                this.pingGetter = new Ping1_9R1();
                this.JSONItemGetter = new JSONItems1_9R1();
                this.tabListManager = new TabList1_9R1();
                plugin.getGeneralLibrary().sendConsoleMessage("&e" + plugin.getServerVersion()
                        + " &adetected successfully set up packets. (Took &e" + (System.currentTimeMillis() - startTime) + "ms&a)");
                break;
            case "v1_8_R3":
                this.titleManager = new Titles1_8R3();
                this.actionBarManager = new ActionBars1_8R3();
                this.particleManager = new Particle1_8R3();
                this.JSONMessageSender = new JSONMessages1_8R3();
                this.pingGetter = new Ping1_8R3();
                this.JSONItemGetter = new JSONItems1_8R3();
                this.tabListManager = new TabList1_8R3();
                plugin.getGeneralLibrary().sendConsoleMessage("&e" + plugin.getServerVersion()
                        + " &adetected successfully set up packets. (Took &e" + (System.currentTimeMillis() - startTime) + "ms&a)");
                break;
            case "v1_8_R2":
                this.titleManager = new Titles1_8R2();
                this.actionBarManager = new ActionBars1_8R2();
                this.particleManager = new Particle1_8R2();
                this.JSONMessageSender = new JSONMessages1_8R2();
                this.pingGetter = new Ping1_8R2();
                this.JSONItemGetter = new JSONItems1_8R2();
                this.tabListManager = new TabList1_8R2();
                plugin.getGeneralLibrary().sendConsoleMessage("&e" + plugin.getServerVersion()
                        + " &adetected successfully set up packets. (Took &e" + (System.currentTimeMillis() - startTime) + "ms&a)");
                break;
            case "v1_8_R1":
                this.titleManager = new Titles1_8R1();
                this.actionBarManager = new ActionBars1_8R1();
                this.particleManager = new Particle1_8R1();
                this.JSONMessageSender = new JSONMessages1_8R1();
                this.pingGetter = new Ping1_8R1();
                this.JSONItemGetter = new JSONItems1_8R1();
                this.tabListManager = new TabList1_8R1();
                plugin.getGeneralLibrary().sendConsoleMessage("&e" + plugin.getServerVersion()
                        + " &adetected successfully set up packets. (Took &e" + (System.currentTimeMillis() - startTime) + "ms&a)");
                break;
            case "v1_7_R4":
                this.JSONMessageSender = new JSONMessages1_7R4();
                this.pingGetter = new Ping1_7R4();
                this.JSONItemGetter = new JSONItems1_7R4();
                plugin.getGeneralLibrary().sendConsoleMessage("&cSome of the packets in ZotLib have been loaded due to &e1.7_R4 &csupport, " +
                        "but most packets will not load for this version.");
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

    /**
     * Allows you to send a player a clickable or hoverable message in chat.
     *
     * @return The JSONMessageSender instance.
     */
    public JSONMessages getJSONMessageSender()
    {
        return JSONMessageSender;
    }

    /**
     * Allows you to get a player's ping.
     *
     * @return The ping getter.
     */
    public Ping getPingGetter()
    {
        return pingGetter;
    }

    /**
     * Allows you to convert item stacks into JSON messages.
     *
     * @return The JSON item getter.
     */
    public JSONItems getJSONItemGetter()
    {
        return JSONItemGetter;
    }

    /**
     * Allows you to create a packet hologram which you can customize based on the server's version.
     *
     * @param lines      A list of the lines for the hologram to display (include color codes).
     * @param lineSpread How far apart should each line be from each other?
     * @param location   The location where it should be displayed.
     * @return The pre-built hologram object. Use the .create() method to create the packet before showing players.
     */
    public Hologram getNewHologram(List<String> lines, double lineSpread, Location location)
    {
        switch (plugin.getServerVersion())
        {
            case "v1_11_R1":
                return new Hologram1_11R1(lines, lineSpread, location);
            case "v1_10_R1":
                return new Hologram1_10R1(lines, lineSpread, location);
            case "v1_9_R2":
                return new Hologram1_9R2(lines, lineSpread, location);
            case "v1_9_R1":
                return new Hologram1_9R1(lines, lineSpread, location);
            case "v1_8_R3":
                return new Hologram1_8R3(lines, lineSpread, location);
            case "v1_8_R2":
                return new Hologram1_8R2(lines, lineSpread, location);
            case "v1_8_R1":
                return new Hologram1_8R1(lines, lineSpread, location);
            default:
                return null;
        }
    }

    /**
     * Sends an older boss bar for 1.7.10-1.8.9.
     *
     * @param player player to send to.
     * @param text   the text that appears.
     * @return the OlderBossBar object.
     */
    public OlderBossBar getOlderBossBar(Player player, String text)
    {
        switch (plugin.getServerVersion())
        {
            case "v1_8_R3":
                return new BossBar1_8R3(player, text);
            case "v1_8_R2":
                return new BossBar1_8R3(player, text);
            case "v1_8_R1":
                return new BossBar1_8R3(player, text);
            case "v1_7_R4":
                return new BossBar1_7R4(player, text);
            default:
                return null;
        }
    }

    /**
     * Sends an newer boss bar for 1.9+.
     * @param text the text that appears.
     * @param barColor the color of the boss bar
     * @param barStyle the boss bar style.
     * @param barFlag the flags for the boss bar.
     * @return the NewerBossBar object.
     */
    public NewerBossBar getNewerBossBar(String text, BarColor barColor, BarStyle barStyle, BarFlag barFlag)
    {
        return new NewerBossBar(text, barColor, barStyle, barFlag);
    }

    /**
     * Allows you to set the header and footers of the tab list for players.
     *
     * @return The tab list packet manager.
     */
    public TabList getTabListManager()
    {
        return tabListManager;
    }

}
