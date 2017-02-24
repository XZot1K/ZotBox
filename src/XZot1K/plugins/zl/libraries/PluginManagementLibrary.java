package XZot1K.plugins.zl.libraries;

import XZot1K.plugins.zl.ZotLib;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public class PluginManagementLibrary
{

    private ZotLib plugin = ZotLib.getInstance();

    public boolean isPluginLoaded(String pluginName)
    {
        for (Plugin pl : plugin.getServer().getPluginManager().getPlugins())
        {
            if (pl.getDescription().getName().equalsIgnoreCase(pluginName))
            {
                return true;
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    public boolean unLoadPlugin(String pluginName, boolean useDependDetector)
    {
        long startTime = System.currentTimeMillis();
        for (Plugin pl : plugin.getServer().getPluginManager().getPlugins())
        {
            if (pl.getDescription().getName().equalsIgnoreCase(pluginName))
            {

                if (isPrevented(pl.getDescription().getName()))
                {
                    plugin.getGeneralLibrary().sendConsoleMessage("&e" + pl.getDescription().getName()
                            + " &cwas in the prevented plugins list in the &econfig.yml&c. Skipping...");
                    return false;
                }

                if (useDependDetector)
                {
                    for (Plugin otherPl : plugin.getServer().getPluginManager().getPlugins())
                    {
                        if (otherPl.getDescription().getDepend().contains(pl.getDescription().getName()))
                        {
                            plugin.getGeneralLibrary().sendConsoleMessage("&aFound that &e" + otherPl.getDescription().getName()
                                    + " &adepends on &e" + pl.getDescription().getName() + "&a. " + "Trying to un-load this plugin as well.");
                            if (isPrevented(otherPl.getDescription().getName()))
                            {
                                plugin.getGeneralLibrary().sendConsoleMessage("&e" + pl.getDescription().getName()
                                        + " &cwas in the prevented plugins list in the &econfig.yml&c. Skipping...");
                                return false;
                            }

                            unLoadPlugin(otherPl.getDescription().getName(), useDependDetector);
                        }
                    }
                }

                String name = pl.getName();
                SimpleCommandMap commandMap;
                List<Plugin> plugins;
                Map<String, Plugin> names;
                Map<String, Command> commands;
                Map<Event, SortedSet<RegisteredListener>> listeners = null;
                boolean reloadListeners = true;

                try
                {
                    plugin.getServer().getPluginManager().disablePlugin(pl);
                    Field pluginsField = plugin.getServer().getPluginManager().getClass().getDeclaredField("plugins");
                    pluginsField.setAccessible(true);
                    plugins = (List<Plugin>) pluginsField.get(plugin.getServer().getPluginManager());
                    Field lookupNamesField = plugin.getServer().getPluginManager().getClass().getDeclaredField("lookupNames");
                    lookupNamesField.setAccessible(true);
                    names = (Map<String, Plugin>) lookupNamesField.get(plugin.getServer().getPluginManager());
                    try
                    {
                        Field listenersField = plugin.getServer().getPluginManager().getClass().getDeclaredField("listeners");
                        listenersField.setAccessible(true);
                        listeners = (Map<Event, SortedSet<RegisteredListener>>) listenersField.get(plugin.getServer().getPluginManager());
                    } catch (Exception e)
                    {
                        reloadListeners = false;
                    }

                    Field commandMapField = plugin.getServer().getPluginManager().getClass().getDeclaredField("commandMap");
                    commandMapField.setAccessible(true);
                    commandMap = (SimpleCommandMap) commandMapField.get(plugin.getServer().getPluginManager());

                    Field knownCommandsField = SimpleCommandMap.class.getDeclaredField("knownCommands");
                    knownCommandsField.setAccessible(true);
                    commands = (Map<String, Command>) knownCommandsField.get(commandMap);
                } catch (NoSuchFieldException | IllegalAccessException e)
                {
                    e.printStackTrace();
                    return false;
                }

                plugin.getServer().getPluginManager().disablePlugin(pl);
                if (plugins != null && plugins.contains(pl))
                    plugins.remove(pl);
                if (names != null && names.containsKey(name))
                    names.remove(name);
                if (listeners != null && reloadListeners)
                {
                    for (SortedSet<RegisteredListener> set : listeners.values())
                    {
                        set.removeIf(value -> value.getPlugin() == pl);
                    }
                }

                if (commandMap != null)
                {
                    for (Iterator<Map.Entry<String, Command>> it = commands.entrySet().iterator(); it.hasNext(); )
                    {
                        Map.Entry<String, Command> entry = it.next();
                        if (entry.getValue() instanceof PluginCommand)
                        {
                            PluginCommand c = (PluginCommand) entry.getValue();
                            if (c.getPlugin() == pl)
                            {
                                c.unregister(commandMap);
                                it.remove();
                            }
                        }
                    }
                }

                ClassLoader cl = plugin.getClass().getClassLoader();
                if (cl instanceof URLClassLoader)
                {
                    try
                    {
                        ((URLClassLoader) cl).close();
                    } catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }

                System.gc();
                long endTime = System.currentTimeMillis();
                plugin.getGeneralLibrary().sendConsoleMessage("&aSuccessfully un-loaded &e" + pl.getDescription().getName()
                        + "&a! (Took &e" + (endTime - startTime) + "ms&a)");
                return true;
            }
        }

        return false;
    }

    public boolean loadPlugin(String pluginName, boolean useDependDetector)
    {
        if (!isPluginLoaded(pluginName))
        {
            long startTime = System.currentTimeMillis();
            File pluginsFolder = new File("plugins");
            if (pluginsFolder.exists() && pluginsFolder.isDirectory())
            {
                for (File file : pluginsFolder.listFiles())
                {
                    if (file.getName().toLowerCase().endsWith(".jar"))
                    {
                        try
                        {
                            PluginDescriptionFile desc = plugin.getPluginLoader().getPluginDescription(file);
                            if (desc.getName().equalsIgnoreCase(pluginName))
                            {

                                if (isPrevented(desc.getName()))
                                {
                                    plugin.getGeneralLibrary().sendConsoleMessage("&e" + desc.getName()
                                            + " &cwas in the prevented plugins list in the &econfig.yml&c. Skipping...");
                                    return false;
                                }

                                if (useDependDetector)
                                {
                                    for (String dependent : desc.getDepend())
                                    {
                                        if (!isPluginLoaded(dependent))
                                        {
                                            plugin.getGeneralLibrary().sendConsoleMessage("&aFound that &e" + desc.getName() + " &adepends on &e" + dependent + "&a, but it is not installed. " +
                                                    "Trying to load the dependency if it is in the plugins folder.");

                                            if (isPrevented(dependent))
                                            {
                                                plugin.getGeneralLibrary().sendConsoleMessage("&e" + dependent
                                                        + " &cwas in the prevented plugins list in the &econfig.yml&c. Skipping...");
                                                return false;
                                            }

                                            if (!loadPlugin(dependent, useDependDetector))
                                            {
                                                plugin.getGeneralLibrary().sendConsoleMessage("&cTried to load the dependencies for &e"
                                                        + desc.getName() + " &c, but was un-successful. Therefore &e" + desc.getName() + " &cwas not loaded.");
                                                return false;
                                            }
                                        }
                                    }
                                }

                                Plugin pl = plugin.getServer().getPluginManager().loadPlugin(file);
                                pl.onLoad();
                                plugin.getServer().getPluginManager().enablePlugin(pl);
                                long endTime = System.currentTimeMillis();
                                plugin.getGeneralLibrary().sendConsoleMessage("&aSuccessfully loaded &e" + pl.getDescription().getName()
                                        + "&a! (Took &e" + (endTime - startTime) + "ms&a)");
                                return true;
                            }
                        } catch (InvalidPluginException | InvalidDescriptionException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean reloadPlugin(String pluginName, boolean useDependDetector)
    {
        return unLoadPlugin(pluginName, useDependDetector) && loadPlugin(pluginName, useDependDetector);
    }

    public String getProperPluginName(String pluginName)
    {
        for (Plugin pl : plugin.getServer().getPluginManager().getPlugins())
        {
            if (pl.getDescription().getName().equalsIgnoreCase(pluginName))
            {
                return pl.getDescription().getName();
            }
        }

        return pluginName;
    }

    public boolean isPrevented(String pluginName)
    {
        for (String pl : plugin.getConfig().getStringList("prevented-plugins"))
        {
            if (pluginName.equalsIgnoreCase(pl))
            {
                return true;
            }
        }

        return false;
    }

}
