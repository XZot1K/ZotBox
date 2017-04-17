package XZot1K.plugins.zb;

import XZot1K.plugins.zb.commands.BaseCommand;
import XZot1K.plugins.zb.libraries.*;
import XZot1K.plugins.zb.libraries.inventorylib.InventoryLibrary;
import XZot1K.plugins.zb.libraries.locationlib.LocationLibrary;
import XZot1K.plugins.zb.listeners.HologramListeners;
import XZot1K.plugins.zb.packets.holograms.Hologram;
import XZot1K.plugins.zb.statistichosts.MCUpdate;
import XZot1K.plugins.zb.statistichosts.Metrics;
import XZot1K.plugins.zb.utils.holograms.HologramManager;
import XZot1K.plugins.zb.utils.holograms.HologramTask;
import org.bukkit.plugin.java.JavaPlugin;

public class ZotBox extends JavaPlugin
{

    private static ZotBox instance;
    private String serverVersion = getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

    // ZotBox Libraries.
    private InventoryLibrary inventoryLibrary;
    private GeneralLibrary generalLibrary;
    private PacketLibrary packetLibrary;
    private CalculationLibrary calculationLibrary;
    private LocationLibrary locationLibrary;
    private DatabaseLibrary databaseLibrary;
    private PluginManagementLibrary pluginManagementLibrary;

    // ZotBox Managers
    private HologramManager hologramManager;

    // ZotBox Tasks
    private HologramTask hologramTask;

    public static ZotBox getInstance()
    {
        return instance;
    }

    @Override
    public void onEnable()
    {
        long startTime = System.currentTimeMillis();
        instance = this;
        saveDefaultConfig();
        setupLibraries();
        setupManagers();
        connectToStatisticHosts();
        getPacketLibrary().setupPackets();

        // Register Commands.
        getCommand("zotbox").setExecutor(new BaseCommand());

        // Register Listeners.
        getServer().getPluginManager().registerEvents(new HologramListeners(), this);

        // Start tasks.
        taskStuff();

        long endTime = System.currentTimeMillis();
        getGeneralLibrary().sendConsoleMessage("&aSuccessfully loaded and enabled &bZot&7Box&a! (Took &e" + (endTime - startTime) + "ms&a)");
    }

    @Override
    public void onDisable()
    {
        for (int i = -1; ++i < getHologramManager().getHolograms().size(); )
        {
            Hologram hologram = getHologramManager().getHolograms().get(i);
            hologram.hideAll();
            getHologramManager().saveHologram(hologram);
        }
    }

    private void taskStuff()
    {
        long startTime = System.currentTimeMillis();
        if (getConfig().getBoolean("use-hologram-task"))
        {
            hologramTask = new HologramTask();
            hologramTask.runTaskTimerAsynchronously(this, 0, 20 * 15);
        }

        long endTime = System.currentTimeMillis();
        getGeneralLibrary().sendConsoleMessage("&aSuccessfully setup and started all task related things! (Took &e" + (endTime - startTime) + "ms&a)");
    }

    private void setupLibraries()
    {
        inventoryLibrary = new InventoryLibrary();
        generalLibrary = new GeneralLibrary();
        packetLibrary = new PacketLibrary();
        calculationLibrary = new CalculationLibrary();
        locationLibrary = new LocationLibrary();
        databaseLibrary = new DatabaseLibrary();
        pluginManagementLibrary = new PluginManagementLibrary();
    }

    private void setupManagers()
    {
        hologramManager = new HologramManager();
    }

    private void connectToStatisticHosts()
    {
        try
        {
            long startTime = System.currentTimeMillis();
            Metrics metrics = new Metrics(this);
            metrics.start();
            long endTime = System.currentTimeMillis();
            getGeneralLibrary().sendConsoleMessage("&aMetrics has been successfully connected. (Took &e" + (endTime - startTime) + "ms&a)");
        } catch (Exception e)
        {
            getGeneralLibrary().sendConsoleMessage("&cMetrics failed to start. Please check your connection.");
        }

        try
        {
            long startTime = System.currentTimeMillis();
            new MCUpdate(this);
            long endTime = System.currentTimeMillis();
            getGeneralLibrary().sendConsoleMessage("&aMCUpdate has been successfully connected. (Took &e" + (endTime - startTime) + "ms&a)");
        } catch (Exception e)
        {
            getGeneralLibrary().sendConsoleMessage("&cMCUpdate failed to start. Please check your connection.");
        }
    }

    public InventoryLibrary getInventoryLibrary()
    {
        return inventoryLibrary;
    }

    public GeneralLibrary getGeneralLibrary()
    {
        return generalLibrary;
    }

    public PacketLibrary getPacketLibrary()
    {
        return packetLibrary;
    }

    public String getServerVersion()
    {
        return serverVersion;
    }

    public String getPrefix()
    {
        return "&bZot&7Box &8-> ";
    }

    public CalculationLibrary getCalculationLibrary()
    {
        return calculationLibrary;
    }

    public LocationLibrary getLocationLibrary()
    {
        return locationLibrary;
    }

    public DatabaseLibrary getDatabaseLibrary()
    {
        return databaseLibrary;
    }

    public PluginManagementLibrary getPluginManagementLibrary()
    {
        return pluginManagementLibrary;
    }

    public HologramManager getHologramManager()
    {
        return hologramManager;
    }

    public HologramTask getHologramTask()
    {
        return hologramTask;
    }

    public void setHologramTask(HologramTask hologramTask)
    {
        this.hologramTask = hologramTask;
    }

}
