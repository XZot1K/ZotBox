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
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (command.getName().equalsIgnoreCase("zotlib"))
        {
            if (sender.hasPermission("zotlib.reload"))
            {
                if (args.length == 1)
                {
                    if (args[0].equalsIgnoreCase("reload"))
                    {
                        reloadCommand(sender);
                        return true;
                    }
                }

                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("usage-message")));
                return true;
            } else
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
                return true;
            }
        }
        return false;
    }

    private void reloadCommand(CommandSender sender)
    {
        plugin.reloadConfig();
        plugin.getPacketLibrary().setupPackets();
        sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("reload-message")));
    }


}
