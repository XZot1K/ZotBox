package XZot1K.plugins.zl.libraries;

import XZot1K.plugins.zl.Manager;
import XZot1K.plugins.zl.ZotLib;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class GeneralLibrary
{

    private ZotLib plugin = Manager.getPlugin();

    /**
     * Color codes the given text.
     *
     * @param msg The message you want colored.
     * @return The message color coded.
     */
    public String color(String msg)
    {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    /**
     * Sends the console a message of your choice through the ZotLib.
     * @param message The message you want to send to console.
     */
    public void sendConsoleMessage(String message)
    {
        plugin.getServer().getConsoleSender().sendMessage(color(plugin.getPrefix() + message));
    }

    /**
     * @param plugin  Your plugin instance.
     * @param message The message you wish to send (You must add your own prefix).
     */
    public void sendConsoleMessage(Plugin plugin, String message)
    {
        plugin.getServer().getConsoleSender().sendMessage(color(message));
    }

}
