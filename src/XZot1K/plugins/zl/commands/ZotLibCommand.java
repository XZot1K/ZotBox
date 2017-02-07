package XZot1K.plugins.zl.commands;

import XZot1K.plugins.zl.Manager;
import XZot1K.plugins.zl.ZotLib;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ZotLibCommand implements CommandExecutor
{
    private ZotLib plugin = Manager.getPlugin();

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
                        plugin.reloadConfig();
                        plugin.getPacketLibrary().setupPackets();
                        commandSender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("reload-message")));
                        return true;
                    }
                }

                commandSender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("usage-message")));
                return true;
            } else
            {
                commandSender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
                return true;
            }
        }
        return false;
    }
}
