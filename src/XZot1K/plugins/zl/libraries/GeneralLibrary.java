package XZot1K.plugins.zl.libraries;

import XZot1K.plugins.zl.Manager;
import XZot1K.plugins.zl.ZotLib;
import XZot1K.plugins.zl.packets.ping.PingEffectivity;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

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
     * Converts all color codes in every line of the string array.
     *
     * @param stringList The list of strings.
     * @return The colored version of the list of strings.
     */
    public List<String> colorLines(List<String> stringList)
    {
        List<String> newList = new ArrayList<>();
        for (String line : stringList)
        {
            newList.add(color(line));
        }
        return newList;
    }

    /**
     * Converts all color codes in every line of the string array.
     *
     * @param stringList The array list of strings.
     * @return The colored version of the list of strings.
     */
    public ArrayList<String> colorLines(ArrayList<String> stringList)
    {
        ArrayList<String> newList = new ArrayList<>();
        for (String line : stringList)
        {
            newList.add(color(line));
        }
        return newList;
    }

    /**
     * Replace a value with another value in a string.
     *
     * @param text        The text you want to replace the placeholder in.
     * @param placeHolder The placeholder you want to replace.
     * @param value       The value you want to replace the placeholder with.
     * @return The result.
     */
    public String replacePlaceHolder(String text, String placeHolder, String value)
    {
        return text.replace(placeHolder, value);
    }

    /**
     * Replace a value with another value in a string.
     *
     * @param stringList  The list you want to replace the placeholder in.
     * @param placeHolder The placeholder you want to replace.
     * @param value       The value you want to replace the placeholder with.
     * @return The result.
     */
    public List<String> replacePlaceHolder(List<String> stringList, String placeHolder, String value)
    {
        List<String> newList = new ArrayList<>();
        for (String line : stringList)
        {
            newList.add(line.replace(placeHolder, value));
        }
        return newList;
    }

    /**
     * Replace a value with another value in a string.
     *
     * @param stringList  The array list you want to replace the placeholder in.
     * @param placeHolder The placeholder you want to replace.
     * @param value       The value you want to replace the placeholder with.
     * @return The result.
     */
    public ArrayList<String> replacePlaceHolder(ArrayList<String> stringList, String placeHolder, String value)
    {
        ArrayList<String> newList = new ArrayList<>();
        for (String line : stringList)
        {
            newList.add(line.replace(placeHolder, value));
        }
        return newList;
    }

    /**
     * Sends the console a message of your choice through the ZotLib.
     *
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

    /**
     * Allows you to get a players ping/latency.
     *
     * @param player The player you want to get the ping from.
     * @return The player's ping/latency.
     */
    public int getPing(Player player)
    {
        return plugin.getPacketLibrary().getPingGetter().getPing(player);
    }

    /**
     * Allows you to get how strong the player's ping/latency is at ease.
     *
     * @param ping The player's ping/latency (Recommend feeding this method the getPing() method).
     * @return Whether the ping is very strong, strong, average, weak, or very weak.
     */
    public PingEffectivity getPingEffectivity(int ping)
    {
        if (ping <= 30)
        {
            return PingEffectivity.VERY_STRONG;
        } else if (ping > 30 && ping <= 130)
        {
            return PingEffectivity.STRONG;
        } else if (ping > 130 && ping <= 230)
        {
            return PingEffectivity.AVERAGE;
        } else if (ping > 230 && ping <= 330)
        {
            return PingEffectivity.WEAK;
        }

        return PingEffectivity.VERY_WEAK;
    }

}
