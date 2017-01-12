package XZot1K.plugins.zl.libraries;

import XZot1K.plugins.zl.Manager;
import XZot1K.plugins.zl.ZotLib;
import org.bukkit.ChatColor;

public class GeneralLibrary
{

    private ZotLib plugin = Manager.getPlugin();

    /*
    The "color" method will go through the fed text and use the & symbol to color code it.
    After this process is done it will return your new message.
    */
    public String color(String msg)
    {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    // The "sendConsoleMessage" method sends the console a message of your choice through the ZotLib.
    public void sendConsoleMessage(String message)
    {
        plugin.getServer().getConsoleSender().sendMessage(color(plugin.getPrefix() + message));
    }

}
