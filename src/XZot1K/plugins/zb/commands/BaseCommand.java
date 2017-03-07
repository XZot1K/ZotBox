package XZot1K.plugins.zb.commands;

import XZot1K.plugins.zb.ZotBox;
import XZot1K.plugins.zb.packets.holograms.Hologram;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class BaseCommand implements CommandExecutor
{
    private ZotBox plugin = ZotBox.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (command.getName().equalsIgnoreCase("zotbox"))
        {
            if (sender.hasPermission("zotbox.use"))
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
                    if (args[0].equalsIgnoreCase("sendmessage") || args[0].equalsIgnoreCase("sm"))
                    {
                        sendMessage(sender, args[1], args[2]);
                        return true;
                    } else if (args[0].equalsIgnoreCase("addhologramline") || args[0].equalsIgnoreCase("ahl"))
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

                sender.sendMessage(plugin.getGeneralLibrary().color(" \n&8<&m--------------&r&8( &e★ &bZot&7Box &bCommands &e★ &8)&m-------------&r&8>"));
                sender.sendMessage(plugin.getGeneralLibrary().color(""));
                sender.sendMessage(plugin.getGeneralLibrary().color("&8★ &e/zotbox reload &8- &aRe-Loads all packets and configurations."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&8★ &e/zotbox info &8- &aDisplays information about the plugin."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&8★ &e/zotbox <sendmessage/sm> <player> <message> &8- &aSends the player a custom message."));
                sender.sendMessage(plugin.getGeneralLibrary().color(""));
                sender.sendMessage(plugin.getGeneralLibrary().color("&8★ &e/zotbox <loadplugin/lp> <plugin> &8- &aLoads an un-loaded plugin."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&8★ &e/zotbox <unloadplugin/up> <plugin> &8- &aUn-Loads a loaded plugin."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&8★ &e/zotbox <reloadplugin/rp> <plugin> &8- &aRe-Loads a loaded plugin."));
                sender.sendMessage(plugin.getGeneralLibrary().color(""));
                sender.sendMessage(plugin.getGeneralLibrary().color("&8★ &e/zotbox <createhologram/ch> <id> &8- &aCreates a new hologram."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&8★ &e/zotbox <deletehologram/dh> <id> &8- &aDeletes a the defined hologram."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&8★ &e/zotbox <modifyhologramline/mhl> <id> <index> <text> &8- &aModifies the line in the hologram."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&8★ &e/zotbox <addhologramline/ahl> <id> <index> <text> &8- &aAdds a new line to the hologram."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&8★ &e/zotbox <removehologramline/rhl> <id> <index> <text> &8- &aRemoves a line from the hologram."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&8★ &e/zotbox <relocatehologram/rh> <id> &8- &aRe-locates the hologram."));
                sender.sendMessage(plugin.getGeneralLibrary().color("&8★ &e/zotbox <sethologramlinespread/shls> <id> <value> &8- &aSets the spread amount for the hologram's lines."));
                sender.sendMessage(plugin.getGeneralLibrary().color(""));
                sender.sendMessage(plugin.getGeneralLibrary().color("&8<&m---------------------------------------------&r&8>\n "));
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
            if (sender.hasPermission("zotbox.createhologram"))
            {
                if (!plugin.getHologramManager().doesHologramExist(hologramId))
                {
                    List<String> lines = Arrays.asList(
                            "&8&m----------------------------",
                            "&aNew Hologram created by &bZotBox&a!",
                            "&8&m----------------------------");
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
        if (sender.hasPermission("zotbox.deletehologram"))
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
            if (sender.hasPermission("zotbox.relocatehologram"))
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
        if (sender.hasPermission("zotbox.addhologramline"))
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
        if (sender.hasPermission("zotbox.sethologramlinespread"))
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
        if (sender.hasPermission("zotbox.removehologramline"))
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
        if (sender.hasPermission("zotbox.modifyhologramline"))
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
        if (sender.hasPermission("zotbox.reloadplugin"))
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
        if (sender.hasPermission("zotbox.unloadplugin"))
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
        if (sender.hasPermission("zotbox.loadplugin"))
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
        if (sender.hasPermission("zotbox.info"))
        {
            sender.sendMessage(plugin.getGeneralLibrary().color(" \n&8<&m--------------&r&8( &e★ &bZot&7Box &e★ &8)&m-------------&r&8>"));
            sender.sendMessage(plugin.getGeneralLibrary().color(""));
            sender.sendMessage(plugin.getGeneralLibrary().color("&e★ &8Version: &b" + plugin.getDescription().getVersion()));
            sender.sendMessage(plugin.getGeneralLibrary().color(""));
            sender.sendMessage(plugin.getGeneralLibrary().color("&e★ &8Author(s): &bXZot1K"));
            sender.sendMessage(plugin.getGeneralLibrary().color(""));
            sender.sendMessage(plugin.getGeneralLibrary().color("&8<&m-------------------------------------&r&8>\n "));
        } else
        {
            sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
        }
    }

    private void reloadCommand(CommandSender sender)
    {
        if (sender.hasPermission("zotbox.reload"))
        {
            plugin.reloadConfig();
            plugin.getPacketLibrary().setupPackets();
            sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("reload-message")));
        } else
        {
            sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
        }
    }

    private void sendMessage(CommandSender sender, String playerName, String message)
    {
        if (sender.hasPermission("zotbox.sendmessage"))
        {
            Player player = plugin.getServer().getPlayer(playerName);
            if (player != null && player.isOnline())
            {
                String newMessage = plugin.getGeneralLibrary().color(message.replace("_", " "));
                player.sendMessage(newMessage);
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("message-sent-message")
                        .replace("{player}", player.getName()).replace("{message}", newMessage)));
            } else
            {
                sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("invalid-player-message")));
            }
        } else
        {
            sender.sendMessage(plugin.getGeneralLibrary().color(plugin.getPrefix() + plugin.getConfig().getString("no-permission-message")));
        }
    }

}
