package XZot1K.plugins.zl.commands;

import XZot1K.plugins.zl.ZotLib;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ZotLibCommand implements CommandExecutor
{
    private ZotLib plugin = ZotLib.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (command.getName().equalsIgnoreCase("zotlib"))
        {
            if (sender.hasPermission("zotlib.use"))
            {
                if (args.length == 1)
                {
                    if (args[0].equalsIgnoreCase("reload"))
                    {
                        reloadCommand(sender);
                        return true;
                    } else if (args[0].equalsIgnoreCase("info"))
                    {
                        infoCommand(sender);
                        return true;
                    }
                } else if (args.length == 2)
                {
                    if (args[0].equalsIgnoreCase("loadplugin") || args[0].equalsIgnoreCase("lp"))
                    {
                        loadPluginCommand(sender, args[1].replace("_", " "));
                        return true;
                    } else if (args[0].equalsIgnoreCase("unloadplugin") || args[0].equalsIgnoreCase("up"))
                    {
                        unloadPluginCommand(sender, args[1].replace("_", " "));
                        return true;
                    } else if (args[0].equalsIgnoreCase("reloadplugin") || args[0].equalsIgnoreCase("rp"))
                    {
                        reloadPluginCommand(sender, args[1].replace("_", " "));
                        return true;
                    }
                }

                sender.sendMessage(plugin.getGeneralLibrary().color(" \n&7<&m--------------&r&7( &e★ &bZotLib Commands &e★ &7)&m-------------&r&7>"));
                sender.sendMessage(plugin.getGeneralLibrary().color(""));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7★ &e/zotlib reload &7- &aRe-Loads all packets and configurations."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7★ &e/zotlib info &7- &aDisplays information about the plugin."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7★ &e/zotlib <loadplugin/lp> <plugin> &7- &aLoads an un-loaded plugin."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7★ &e/zotlib <unloadplugin/up> <plugin> &7- &aUn-Loads a loaded plugin."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7★ &e/zotlib <reloadplugin/rp> <plugin> &7- &aRe-Loads a loaded plugin."));
                sender.sendMessage(plugin.getGeneralLibrary().color(""));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7<&m---------------------------------------------&r&7>\n "));
                return true;
            } else
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
            }
            return true;
        }
        return false;
    }

    private void reloadPluginCommand(CommandSender sender, String pluginName)
    {
        if (sender.hasPermission("zotlib.reloadplugin"))
        {
            long startTime = System.currentTimeMillis();
            if (plugin.getPluginManagementLibrary().reloadPlugin(pluginName, true))
            {
                long endTime = System.currentTimeMillis();
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("plugin-reloaded-message")
                        .replace("{time}", String.valueOf(endTime - startTime))
                        .replace("{plugin}", plugin.getPluginManagementLibrary().getProperPluginName(pluginName))));
            } else
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("plugin-reload-fail-message")
                        .replace("{plugin}", plugin.getPluginManagementLibrary().getProperPluginName(pluginName))));
            }
        } else
        {
            sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
        }
    }

    private void unloadPluginCommand(CommandSender sender, String pluginName)
    {
        if (sender.hasPermission("zotlib.unloadplugin"))
        {
            if (plugin.getPluginManagementLibrary().unLoadPlugin(pluginName, true))
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("plugin-unloaded-message")
                        .replace("{plugin}", plugin.getPluginManagementLibrary().getProperPluginName(pluginName))));
            } else
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("plugin-load-fail-message")
                        .replace("{plugin}", plugin.getPluginManagementLibrary().getProperPluginName(pluginName))));
            }
        } else
        {
            sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
        }
    }

    private void loadPluginCommand(CommandSender sender, String pluginName)
    {
        if (sender.hasPermission("zotlib.loadplugin"))
        {
            if (plugin.getPluginManagementLibrary().loadPlugin(pluginName, true))
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("plugin-loaded-message")
                        .replace("{plugin}", plugin.getPluginManagementLibrary().getProperPluginName(pluginName))));
            } else
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("plugin-load-fail-message")
                        .replace("{plugin}", plugin.getPluginManagementLibrary().getProperPluginName(pluginName))));
            }
        } else
        {
            sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
        }
    }

    private void infoCommand(CommandSender sender)
    {
        if (sender.hasPermission("zotlib.info"))
        {
            sender.sendMessage(plugin.getGeneralLibrary().color(" \n&7<&m--------------&r&7( &e★ &bZotLib &e★ &7)&m-------------&r&7>"));
            sender.sendMessage(plugin.getGeneralLibrary().color(""));
            sender.sendMessage(plugin.getGeneralLibrary().color("&e★ &7Version: &b" + plugin.getDescription().getVersion()));
            sender.sendMessage(plugin.getGeneralLibrary().color(""));
            sender.sendMessage(plugin.getGeneralLibrary().color("&e★ &7Author(s): &bXZot1K"));
            sender.sendMessage(plugin.getGeneralLibrary().color(""));
            sender.sendMessage(plugin.getGeneralLibrary().color("&7<&m-------------------------------------&r&7>\n "));
        } else
        {
            sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
        }
    }

    private void reloadCommand(CommandSender sender)
    {
        if (sender.hasPermission("zotlib.reload"))
        {
            plugin.reloadConfig();
            plugin.getPacketLibrary().setupPackets();
            sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("reload-message")));
        } else
        {
            sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
        }
    }


}
