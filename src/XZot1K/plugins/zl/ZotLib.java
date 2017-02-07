package XZot1K.plugins.zl;

import XZot1K.plugins.zl.commands.ZotLibCommand;
import XZot1K.plugins.zl.libraries.CalculationLibrary;
import XZot1K.plugins.zl.libraries.DatabaseLibrary;
import XZot1K.plugins.zl.libraries.GeneralLibrary;
import XZot1K.plugins.zl.libraries.PacketLibrary;
import XZot1K.plugins.zl.libraries.inventorylib.InventoryLibrary;
import XZot1K.plugins.zl.libraries.locationlib.LocationLibrary;
import XZot1K.plugins.zl.statistichosts.MCUpdate;
import XZot1K.plugins.zl.statistichosts.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class ZotLib extends JavaPlugin
{

    private String serverVersion = getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    private InventoryLibrary inventoryLibrary;
    private GeneralLibrary generalLibrary;
    private PacketLibrary packetLibrary;
    private CalculationLibrary calculationLibrary;
    private LocationLibrary locationLibrary;
    private DatabaseLibrary databaseLibrary;

    @Override
    public void onEnable()
    {
        long startTime = System.currentTimeMillis();
        saveDefaultConfig();
        new Manager(this);
        setupLibraries();
        connectToStatisticHosts();
        getPacketLibrary().setupPackets();
        getCommand("zotlib").setExecutor(new ZotLibCommand());
        long endTime = System.currentTimeMillis();
        getGeneralLibrary().sendConsoleMessage("&aSuccessfully loaded and enabled &eZotLib&a! (Took &e" + (endTime - startTime) + "ms&a)");
    }

    private void setupLibraries()
    {
        inventoryLibrary = new InventoryLibrary();
        generalLibrary = new GeneralLibrary();
        packetLibrary = new PacketLibrary();
        calculationLibrary = new CalculationLibrary();
        locationLibrary = new LocationLibrary();
        databaseLibrary = new DatabaseLibrary();
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
            new MCUpdate(this, true);
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
        return "&bZotLib &7-> ";
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

}
