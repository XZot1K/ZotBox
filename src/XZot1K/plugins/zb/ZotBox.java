package XZot1K.plugins.zb;

import XZot1K.plugins.zb.commands.BaseCommand;
import XZot1K.plugins.zb.libraries.*;
import XZot1K.plugins.zb.libraries.inventorylib.InventoryLibrary;
import XZot1K.plugins.zb.libraries.locationlib.LocationLibrary;
import XZot1K.plugins.zb.listeners.HologramListeners;
import XZot1K.plugins.zb.listeners.WorldPropertyListeners;
import XZot1K.plugins.zb.utils.UpdateChecker;
import XZot1K.plugins.zb.utils.holograms.HologramManager;
import XZot1K.plugins.zb.utils.holograms.HologramTask;
import XZot1K.plugins.zb.utils.worldmanagment.WorldManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ZotBox extends JavaPlugin
{

    private static ZotBox instance;
    private UpdateChecker updateChecker;
    private String serverVersion = getServer().getClass().getPackage().getName().replace(".", ",")
            .split(",")[3];

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
    private WorldManager worldManager;

    // ZotBox Tasks
    private HologramTask hologramTask;

    @Override
    public void onEnable()
    {
        long startTime = System.currentTimeMillis();
        instance = this;
        updateChecker = new UpdateChecker(35913);
        saveDefaultConfig();
        setupLibraries();
        setupManagers();
        getPacketLibrary().setupPackets();

        // Register Commands.
        getCommand("zotbox").setExecutor(new BaseCommand());

        // Register Listeners.
        getServer().getPluginManager().registerEvents(new HologramListeners(), this);

        if (getConfig().getBoolean("use-world-manager"))
        {
            getWorldManager().syncWorldData();
            getServer().getPluginManager().registerEvents(new WorldPropertyListeners(), this);
        }

        // Start tasks.
        taskStuff();

        if (getConfig().getBoolean("update-notifications") && getUpdateChecker().checkForUpdates())
            getGeneralLibrary().sendConsoleMessage(
                    "&cIt seems &bZot&7Box &cis outdated. " + "Visit the spigot page to retrieve the latest version.");
        else
            getGeneralLibrary().sendConsoleMessage("&bZot&7Box &ais currently up to date!");

        long endTime = System.currentTimeMillis();
        getGeneralLibrary().sendConsoleMessage(
                "&aSuccessfully loaded and enabled &bZot&7Box&a! (Took &e" + (endTime - startTime) + "ms&a)");
    }

    @Override
    public void onDisable()
    {
        getWorldManager().saveWorldData(false);
        getHologramManager().saveHolograms();
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
        getGeneralLibrary().sendConsoleMessage(
                "&aSuccessfully setup and started all task related things! (Took &e" + (endTime - startTime) + "ms&a)");
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
        worldManager = new WorldManager(getInstance());
    }

    // getters & setters
    public static ZotBox getInstance()
    {
        return instance;
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

    public WorldManager getWorldManager()
    {
        return worldManager;
    }

    public UpdateChecker getUpdateChecker()
    {
        return updateChecker;
    }
}
