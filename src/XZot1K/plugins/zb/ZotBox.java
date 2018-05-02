package XZot1K.plugins.zb;

import XZot1K.plugins.zb.commands.BaseCommand;
import XZot1K.plugins.zb.libraries.*;
import XZot1K.plugins.zb.libraries.inventorylib.InventoryLibrary;
import XZot1K.plugins.zb.libraries.locationlib.LocationLibrary;
import XZot1K.plugins.zb.listeners.HologramListeners;
import XZot1K.plugins.zb.packets.holograms.Hologram;
import XZot1K.plugins.zb.utils.holograms.HologramManager;
import XZot1K.plugins.zb.utils.holograms.HologramTask;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
		getPacketLibrary().setupPackets();

		// Register Commands.
		getCommand("zotbox").setExecutor(new BaseCommand());

		// Register Listeners.
		getServer().getPluginManager().registerEvents(new HologramListeners(), this);

		// Start tasks.
		taskStuff();

		if (getConfig().getBoolean("update-notifications") && isOutdated())
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
		for (int i = -1; ++i < getHologramManager().getHolograms().size();)
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
	}

	public boolean isOutdated()
	{
		try
		{
			final HttpURLConnection c = (HttpURLConnection) new URL("http://www.spigotmc.org/api/general.php")
					.openConnection();
			c.setDoOutput(true);
			c.setRequestMethod("POST");
			c.getOutputStream()
					.write(("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource" + "=35913")
							.getBytes("UTF-8"));
			final String oldversion = getDescription().getVersion();
			final String newversion = new BufferedReader(new InputStreamReader(c.getInputStream())).readLine();
			if (!newversion.equalsIgnoreCase(oldversion))
			{
				return true;
			}
		} catch (Exception ignored)
		{
		}
		return false;
	}

	public String getLatestVersion()
	{
		try
		{
			final HttpURLConnection c = (HttpURLConnection) new URL("http://www.spigotmc.org/api/general.php")
					.openConnection();
			c.setDoOutput(true);
			c.setRequestMethod("POST");
			c.getOutputStream()
					.write(("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource" + "=17184")
							.getBytes("UTF-8"));
			return new BufferedReader(new InputStreamReader(c.getInputStream())).readLine();
		} catch (Exception ex)
		{
			return getDescription().getVersion();
		}
	}

	// getters & setters
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
