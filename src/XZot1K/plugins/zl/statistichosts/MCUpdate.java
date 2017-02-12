// Would be good advice to move this into its own API, similar to MCStats
package XZot1K.plugins.zl.statistichosts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MCUpdate implements Listener
{
    private final static String NAME = "MCUpdate";
    private final static String VERSION = "1.0";
    private static final String URL = "http://report.mcupdate.org";
    /**
     * Server received information.
     */
    private static String updateMessage = "";
    private static boolean upToDate = true;
    private Plugin pl;
    /**
     * Interval of time to ping (seconds)
     */
    private int PING_INTERVAL;
    /**
     * The scheduled task
     */
    private volatile BukkitTask task = null;
    private boolean areMetricsEnabled = true;
    private boolean isUpdaterEnabled = false;
    private Configuration cfg;

    public MCUpdate(Plugin plugin) throws IOException
    {
        if (plugin != null)
        {
            this.pl = plugin;
            this.cfg = new Configuration(pl, "config.yml");

            Bukkit.getPluginManager().registerEvents(this, plugin);
            setPingInterval(cfg.getInt("interval", 900));
            setUpdaterEnabled(cfg.getBoolean("updater", true));
            setMetricsEnabled(cfg.getBoolean("enabled", true));

            if (areMetricsEnabled())
            {
                start();
            }
        }
    }

    private static String toJson(String key, String value)
    {
        return "\"" + key + "\":\"" + value + "\"";
    }

    private static String format(String format)
    {
        return ChatColor.translateAlternateColorCodes('&', format);
    }

    public boolean start()
    {
        // Is MCUpdate already running?
        if (task == null)
        {
            // Begin hitting the server with glorious data
            task = pl.getServer().getScheduler().runTaskTimerAsynchronously(pl, () ->
            {
                report();

                if (!upToDate && isUpdaterEnabled())
                {
                    pl.getServer().getConsoleSender().sendMessage(format(updateMessage));
                }
            }, 0, PING_INTERVAL * 20);
        }

        return true;
    }

    private int getOnlinePlayers()
    {
        try
        {
            Method onlinePlayerMethod = Server.class.getMethod("getOnlinePlayers");
            if (onlinePlayerMethod.getReturnType().equals(Collection.class))
            {
                return ((Collection<?>) onlinePlayerMethod.invoke(Bukkit.getServer())).size();
            } else
            {
                return ((Player[]) onlinePlayerMethod.invoke(Bukkit.getServer())).length;
            }
        } catch (Exception ex)
        {
        }
        return 0;
    }

    private void report()
    {
        String ver = pl.getDescription().getVersion();
        String name = pl.getDescription().getName();
        int playersOnline = this.getOnlinePlayers();
        boolean onlineMode = pl.getServer().getOnlineMode();
        String serverVersion = pl.getServer().getVersion();

        String osname = System.getProperty("os.name");
        String osarch = System.getProperty("os.arch");
        String osversion = System.getProperty("os.version");
        String java_version = System.getProperty("java.version");
        int coreCount = Runtime.getRuntime().availableProcessors();

        /*
         * This parsing system works for now
         * may have to look into using the JSON Structure
         * defaulted by Java.
         */
        String report = "{ \"report\": {";
        report += toJson("plugin", name) + ",";
        report += toJson("version", ver) + ",";
        report += toJson("playersonline", playersOnline + "") + ",";
        report += toJson("onlinemode", onlineMode + "") + ",";
        report += toJson("serverversion", serverVersion) + ",";

        report += toJson("osname", osname) + ",";
        report += toJson("osarch", osarch) + ",";
        report += toJson("osversion", osversion) + ",";
        report += toJson("javaversion", java_version) + ",";
        report += toJson("corecount", coreCount + "") + "";
        report += "} }";

        byte[] data = report.getBytes();

        try
        {

            URL url = new URL(URL);
            URLConnection c = url.openConnection();
            c.setConnectTimeout(2500);
            c.setReadTimeout(3500);

            c.addRequestProperty("User-Agent", NAME + "/" + VERSION);
            c.addRequestProperty("Content-Type", "application/json");
            c.addRequestProperty("Content-Length", Integer.toString(data.length));
            c.addRequestProperty("Accept", "application/json");
            c.addRequestProperty("Connection", "close");

            c.setDoOutput(true);

            OutputStream os = c.getOutputStream();
            os.write(data);
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
            String endData = br.readLine().trim();

            String serverMessage = getString(endData, "message");
            String cVersion = getString(endData, "pl_Version");
            updateMessage = getString(endData, "update_Message");

            if (!serverMessage.equals("ERROR"))
            {
                if (!ver.equals(cVersion))
                {
                    upToDate = false;
                }
            }
            br.close();

        } catch (IOException ignored)
        {
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        if (p.isOp() && !upToDate && isUpdaterEnabled())
        {
            p.sendMessage(format(updateMessage));
        }
    }

    private String getString(String data, String key)
    {
        String dat = data.replace("{ \"Response\": {\"", "");
        dat = dat.replace("\"} }", "");
        List<String> list = Arrays.asList(dat.split("\",\""));

        for (String stub : list)
        {
            List<String> list2 = Arrays.asList(stub.split("\":\""));
            if (key.equals(list2.get(0)))
            {
                return list2.get(1);
            }
        }

        return null;
    }

    public void setPingInterval(int interval)
    {
        this.PING_INTERVAL = interval;
    }

    public void setMetricsEnabled(boolean enabled)
    {
        this.areMetricsEnabled = enabled;
    }

    private boolean isUpdaterEnabled()
    {
        return this.isUpdaterEnabled;
    }

    public void setUpdaterEnabled(boolean enabled)
    {
        this.isUpdaterEnabled = enabled;
    }

    private boolean areMetricsEnabled()
    {
        return this.areMetricsEnabled;
    }

    /**
     * Returns the Configuration Object that stores various Config nodes.
     *
     * @return Configuration
     */
    public Configuration getConfig()
    {
        return this.cfg;
    }

    public class Configuration extends YamlConfiguration
    {
        private Plugin pl;
        private String fn;
        private File cfg;

        public Configuration(Plugin plugin, String filename)
        {
            this.pl = plugin;
            this.fn = filename;

            // Check Config Directory Exists
            if (!getConfigDirectory().exists())
            {
                // Create Directory
                getConfigDirectory().mkdirs();
            }

            // Define Config File
            cfg = new File(getConfigDirectory(), fn);

            // Check Config File Exists
            if (!cfg.exists())
            {
                try
                {
                    // Define Defaults
                    this.set("enabled", true);
                    this.set("updater", true);
                    this.set("interval", 900);

                    // Save the Config
                    this.save(cfg);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            // Load Config
            try
            {
                this.load(cfg);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        private File getPluginDirectory()
        {
            return pl.getDataFolder().getParentFile();
        }

        private File getConfigDirectory()
        {
            return new File(getPluginDirectory(), NAME);
        }
    }
}