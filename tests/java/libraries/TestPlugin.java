package libraries;

import org.bukkit.plugin.java.JavaPlugin;

import libraries.subcommands.tests.TestCommand;

public class TestPlugin extends JavaPlugin {
@Override
public void onEnable() {
	getLogger().info("Starting Zotbox testing plugin");
	getCommand("zottest").setExecutor(new TestCommand());
}
}
