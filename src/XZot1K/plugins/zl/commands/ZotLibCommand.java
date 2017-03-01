package XZot1K.plugins.zl.commands;

import XZot1K.plugins.zl.ZotLib;
import XZot1K.plugins.zl.packets.holograms.Hologram;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

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
                    } else if (args[0].equalsIgnoreCase("createhologram") || args[0].equalsIgnoreCase("ch"))
                    {
                        createHologramCommand(sender, args[1]);
                        return true;
                    } else if (args[0].equalsIgnoreCase("deletehologram") || args[0].equalsIgnoreCase("dh"))
                    {
                        deleteHologramCommand(sender, args[1]);
                        return true;
                    } else if (args[0].equalsIgnoreCase("relocatehologram") || args[0].equalsIgnoreCase("rh"))
                    {
                        relocateHologramCommand(sender, args[1]);
                        return true;
                    }
                } else if (args.length == 3)
                {
                    if (args[0].equalsIgnoreCase("addhologramline") || args[0].equalsIgnoreCase("ahl"))
                    {
                        addHologramLineCommand(sender, args[1], args[2]);
                        return true;
                    } else if (args[0].equalsIgnoreCase("removehologramline") || args[0].equalsIgnoreCase("rhl"))
                    {
                        removeHologramLineCommand(sender, args[1], args[2]);
                        return true;
                    } else if (args[0].equalsIgnoreCase("sethologramlinespread") || args[0].equalsIgnoreCase("shls"))
                    {
                        setHologramLineSpreadCommand(sender, args[1], args[2]);
                        return true;
                    }
                } else if (args.length == 4)
                {
                    if (args[0].equalsIgnoreCase("modifyhologramline") || args[0].equalsIgnoreCase("mhl"))
                    {
                        modifyHologramLineCommand(sender, args[1], args[2], args[3]);
                        return true;
                    }
                }

                sender.sendMessage(plugin.getGeneralLibrary().color(" \n&7<&m--------------&r&7( &e★ &bZotLib Commands &e★ &7)&m-------------&r&7>"));
                sender.sendMessage(plugin.getGeneralLibrary().color(""));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7★ &e/zotlib reload &7- &aRe-Loads all packets and configurations."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7★ &e/zotlib info &7- &aDisplays information about the plugin."));
                sender.sendMessage(plugin.getGeneralLibrary().color(""));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7★ &e/zotlib <loadplugin/lp> <plugin> &7- &aLoads an un-loaded plugin."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7★ &e/zotlib <unloadplugin/up> <plugin> &7- &aUn-Loads a loaded plugin."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7★ &e/zotlib <reloadplugin/rp> <plugin> &7- &aRe-Loads a loaded plugin."));
                sender.sendMessage(plugin.getGeneralLibrary().color(""));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7★ &e/zotlib <createhologram/ch> <id> &7- &aCreates a new hologram."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7★ &e/zotlib <deletehologram/dh> <id> &7- &aDeletes a the defined hologram."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7★ &e/zotlib <modifyhologramline/mhl> <id> <index> <text> &7- &aModifies the line in the hologram."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7★ &e/zotlib <addhologramline/ahl> <id> <index> <text> &7- &aAdds a new line to the hologram."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7★ &e/zotlib <removehologramline/rhl> <id> <index> <text> &7- &aRemoves a line from the hologram."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7★ &e/zotlib <relocatehologram/rh> <id> &7- &aRe-locates the hologram."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&7★ &e/zotlib <sethologramlinespread/shls> <id> &7- &aSets the spread amount for the hologram's lines."));
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


    // Hologram command methods.
    private void createHologramCommand(CommandSender sender, String hologramId)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            if (sender.hasPermission("zotlib.createhologram"))
            {
                if (!plugin.getHologramManager().doesHologramExist(hologramId))
                {
                    List<String> lines = Arrays.asList(
                            "&7&m----------------------------",
                            "&aNew Hologram created by &bZotLib&a!",
                            "&7&m----------------------------");
                    Hologram hologram = plugin.getPacketLibrary().createNewHologram(hologramId, lines, 0.25, player.getLocation()).create();
                    hologram.showAll();
                    plugin.getHologramManager().registerHologram(hologram);
                    plugin.getPacketLibrary().getParticleManager().broadcastParticle(hologram.getLocation().add(0, 3, 0),
                            1, 1, 1, 0, "FIREWORKS_SPARK", 25);
                    sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-created-message")
                            .replace("{id}", hologramId)));
                } else
                {
                    sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-exists-message")
                            .replace("{id}", hologramId)));
                }
            } else
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
            }
        } else
        {
            sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("must-be-player-message")));
        }
    }

    private void deleteHologramCommand(CommandSender sender, String hologramId)
    {
        if (sender.hasPermission("zotlib.deletehologram"))
        {
            Hologram hologram = plugin.getHologramManager().getHologram(hologramId);
            if (hologram != null)
            {
                plugin.getHologramManager().deleteHologram(hologram);
                plugin.getPacketLibrary().getParticleManager().broadcastParticle(hologram.getLocation().add(0, 3, 0),
                        1, 1, 1, 0, "FIREWORKS_SPARK", 25);
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-deleted-message")
                        .replace("{id}", hologramId)));
            } else
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-invalid-message")
                        .replace("{id}", hologramId)));
            }
        } else
        {
            sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
        }
    }

    private void relocateHologramCommand(CommandSender sender, String hologramId)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            if (sender.hasPermission("zotlib.relocatehologram"))
            {
                Hologram hologram = plugin.getHologramManager().getHologram(hologramId);
                if (hologram != null)
                {
                    hologram.setLocation(player.getLocation()).hideAll().create().showAll();
                    plugin.getPacketLibrary().getParticleManager().broadcastParticle(hologram.getLocation().add(0, 3, 0),
                            1, 1, 1, 0, "FIREWORKS_SPARK", 25);
                    sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-relocated-message")
                            .replace("{id}", hologramId)));
                } else
                {
                    sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-invalid-message")
                            .replace("{id}", hologramId)));
                }
            } else
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
            }
        } else
        {
            sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("must-be-player-message")));
        }
    }

    private void addHologramLineCommand(CommandSender sender, String hologramId, String text)
    {
        if (sender.hasPermission("zotlib.addhologramline"))
        {
            Hologram hologram = plugin.getHologramManager().getHologram(hologramId);
            if (hologram != null)
            {
                hologram.getLines().add(text.replace("_", " "));
                hologram.hideAll().create().showAll();
                plugin.getPacketLibrary().getParticleManager().broadcastParticle(hologram.getLocation().add(0, 3, 0),
                        1, 1, 1, 0, "FIREWORKS_SPARK", 25);
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-line-added-message")
                        .replace("{id}", hologramId).replace("{text}", text.replace("_", " "))));
            } else
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-invalid-message")
                        .replace("{id}", hologramId)));
            }
        } else
        {
            sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
        }
    }

    private void setHologramLineSpreadCommand(CommandSender sender, String hologramId, String spread)
    {
        if (sender.hasPermission("zotlib.sethologramlinespread"))
        {
            double spreadValue;
            try
            {
                spreadValue = Double.valueOf(spread);
            } catch (Exception e)
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-spread-invalid-message")));
                return;
            }

            Hologram hologram = plugin.getHologramManager().getHologram(hologramId);
            if (hologram != null)
            {
                hologram.setLineSpread(spreadValue).hideAll().create().showAll();
                plugin.getPacketLibrary().getParticleManager().broadcastParticle(hologram.getLocation().add(0, 3, 0),
                        1, 1, 1, 0, "FIREWORKS_SPARK", 25);
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-line-spread-applied-message")
                        .replace("{id}", hologramId).replace("{spread}", String.valueOf(spreadValue))));
            } else
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-invalid-message")
                        .replace("{id}", hologramId)));
            }
        } else
        {
            sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
        }
    }

    private void removeHologramLineCommand(CommandSender sender, String hologramId, String enteredIndex)
    {
        if (sender.hasPermission("zotlib.removehologramline"))
        {
            int index;
            try
            {
                index = Integer.parseInt(enteredIndex);
            } catch (Exception e)
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-index-invalid-message")));
                return;
            }

            Hologram hologram = plugin.getHologramManager().getHologram(hologramId);
            if (hologram != null)
            {
                if (hologram.getLines().size() > 0 && (hologram.getLines().size() - 1) >= index && index >= 0)
                {
                    hologram.getLines().remove(enteredIndex);
                    hologram.hideAll().create().showAll();
                    plugin.getPacketLibrary().getParticleManager().broadcastParticle(hologram.getLocation().add(0, 3, 0),
                            1, 1, 1, 0, "FIREWORKS_SPARK", 25);
                    sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-line-removed-message")
                            .replace("{id}", hologramId).replace("{index}", String.valueOf(index))));
                } else
                {
                    sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-index-invalid-message")));
                }
            } else
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-invalid-message")
                        .replace("{id}", hologramId)));
            }
        } else
        {
            sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
        }
    }

    private void modifyHologramLineCommand(CommandSender sender, String hologramId, String enteredIndex, String text)
    {
        if (sender.hasPermission("zotlib.modifyhologramline"))
        {
            int index;
            try
            {
                index = Integer.parseInt(enteredIndex);
            } catch (Exception e)
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-index-invalid-message")));
                return;
            }

            Hologram hologram = plugin.getHologramManager().getHologram(hologramId);
            if (hologram != null)
            {
                if (hologram.getLines().size() > 0 && (hologram.getLines().size() - 1) >= index && index >= 0)
                {
                    hologram.getLines().set(index, text.replace("_", " "));
                    hologram.hideAll().create().showAll();
                    plugin.getPacketLibrary().getParticleManager().broadcastParticle(hologram.getLocation().add(0, 3, 0),
                            1, 1, 1, 0, "FIREWORKS_SPARK", 25);
                    sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-line-modified-message")
                            .replace("{index}", String.valueOf(index)).replace("{id}", hologramId).replace("{text}", text.replace("_", " "))));
                } else
                {
                    sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-index-invalid-message")));
                }
            } else
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getConfig().getString("hologram-invalid-message")
                        .replace("{id}", hologramId)));
            }
        } else
        {
            sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
        }
    }

    // Plugin management command methods.
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


    // General command methods.
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
