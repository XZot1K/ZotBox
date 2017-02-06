package XZot1K.plugins.zl;

import XZot1K.plugins.zl.libraries.CalculationLibrary;
import XZot1K.plugins.zl.libraries.DatabaseLibrary;
import XZot1K.plugins.zl.libraries.GeneralLibrary;
import XZot1K.plugins.zl.libraries.PacketLibrary;
import XZot1K.plugins.zl.libraries.inventorylib.InventoryLibrary;
import XZot1K.plugins.zl.libraries.locationlib.LocationLibrary;
import XZot1K.plugins.zl.statistichosts.mcupdate.MCUpdate;
import XZot1K.plugins.zl.statistichosts.metrics.Metrics;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
        saveDefaultConfig();
        new Manager(this);
        inventoryLibrary = new InventoryLibrary();
        generalLibrary = new GeneralLibrary();
        packetLibrary = new PacketLibrary();
        calculationLibrary = new CalculationLibrary();
        locationLibrary = new LocationLibrary();
        databaseLibrary = new DatabaseLibrary();
        connectToStatisticHosts();
        getPacketLibrary().setupPackets();
        getCommand("zotlib").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if (command.getName().equalsIgnoreCase("zotlib"))
        {
            if (commandSender.hasPermission("zotlib.reload"))
            {
                if (args.length == 1)
                {
                    if (args[0].equalsIgnoreCase("reload"))
                    {
                        reloadConfig();
                        getPacketLibrary().setupPackets();
                        commandSender.sendMessage(getGeneralLibrary().color(getPrefix() + getConfig().getString("reload-message")));
                        return true;
                    }
                }

                commandSender.sendMessage(getGeneralLibrary().color(getPrefix() + getConfig().getString("usage-message")));
                return true;
            } else
            {
                commandSender.sendMessage(getGeneralLibrary().color(getPrefix() + getConfig().getString("no-permission-message")));
                return true;
            }
        }
        return false;
    }

    private void connectToStatisticHosts()
    {
        try
        {
            Metrics metrics = new Metrics(this);
            metrics.start();
            getGeneralLibrary().sendConsoleMessage("&aMetrics has been enabled and loaded successfully.");
        } catch (Exception e)
        {
            getGeneralLibrary().sendConsoleMessage("&cMetrics failed to start. Please check your connection.");
        }

        try
        {
            new MCUpdate(this, true);
            getGeneralLibrary().sendConsoleMessage("&aMCUpdate has been enabled and loaded successfully.");
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
