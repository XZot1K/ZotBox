package XZot1K.plugins.zb.utils.subcommands;

import XZot1K.plugins.zb.ZotBox;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * A command that can contain subcommands which are accessed by /(command)
 * (subcommand).<br>
 * This should be able to be nested into another MainCommand for subcommands in
 * a subcommand. <br>
 * <br>
 * You can add your subcommands with {@link #addSubCommand(SubCommand)} <br>
 * <br>
 * The onCommand in this class will never return false unless done so in a
 * subcommand given.
 *
 * @author Ryan Samarakoon
 */
public abstract class MainCommand implements SubCommand
{
	public final String name;
	public final Permission permission;
	List<SubCommand> commands;
	// TODO add ability to change command name on the fly on command map
	// TODO add ability to chain main commands for a diverse command tree
	private ZotBox plugin;

	/**
	 * Create a main command that contains sub commands.<br>
	 * Use this constructor if this command is to be nested inside another
	 * MainCommand
	 *
	 * @param permission
	 *            the permission needed to run this command, may be null if handled
	 *            in plugin yml. <br>
	 *            Cannot be null if you are nesting this command with another
	 *            MainCommand
	 * @param name
	 *            the name of this command. May be null if handled in plugin yml.
	 *            <br>
	 *            Cannot be null if you are nesting this command with another
	 *            MainCommand
	 */
	public MainCommand(Permission permission, String name)
	{
		plugin = ZotBox.getInstance();
		this.name = name;
		this.permission = permission;
		commands = new ArrayList<SubCommand>();

	}

	/**
	 * Creates a main command that contains sub commands.<br>
	 * Use this if you are registering this command in your plugin.yml<br>
	 * This constructor should not be used if this MainCommand is to be nested
	 * inside another MainCommand <br>
	 * <br>
	 * Make sure to register this command with
	 * {@link PluginCommand#setExecutor(org.bukkit.command.CommandExecutor)}
	 *
	 * @see #MainCommand(Permission, String)
	 */
	public MainCommand()
	{
		this(null, null);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
	{
		List<String> returnValue = new ArrayList<String>();
		// always return player list if can't find anything else
		List<Player> players = new ArrayList<Player>(plugin.getServer().getOnlinePlayers());
		for (int i = -1; ++i < players.size();)
		{
			returnValue.add(players.get(i).getName());
		}

		// if tabbing through subcommands
		if (args.length == 1)
		{
			returnValue.clear();
			for (int i = -1; ++i < commands.size();)
			{
				SubCommand cmd = commands.get(i);
				if (cmd.getName().startsWith(args[0]) || args[0].equals(""))
					returnValue.add(cmd.getName());
			}

			// if tabbing through subcommand tabs
		} else if (args.length >= 2)
		{
			String[] commandArgs = Arrays.copyOfRange(args, 1, args.length);
			for (int i = -1; ++i < commands.size();)
			{
				SubCommand cmd = commands.get(i);
				if (cmd.getName().equals(args[0]))
				{
					returnValue = cmd.onTabComplete(sender, command, alias, commandArgs);
					break;
				}

			}
		}

		return returnValue;

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (!onCommandRun(sender, command, label, args))
			return true;

		if (args.length < 1)
		{
			onBlank(sender, command, label, args);
			return true;
		}

		String[] commandArgs = Arrays.copyOfRange(args, 1, args.length);
		if (args.length < 1)
		{
			onBlank(sender, command, label, args);
		}

		for (int i = -1; ++i < commands.size();)
		{
			SubCommand cmd = commands.get(i);
			if (cmd.getName().equals(args[0]))
			{
				if (cmd.getPermission() == null || sender.hasPermission(cmd.getPermission())
						|| cmd.getPermission() == null)
				{
					if (onValid(sender, command, label, args))
						return cmd.onCommand(sender, command, label, commandArgs);
				} else
					onNoPerm(sender, command, label, args);
				return true;
			}
		}

		onInvalid(sender, command, label, args);
		return true;

	}

	/**
	 * Add a sub command to this command<br>
	 * Can throw an {@link IllegalArgumentException} if the subcommand's name is
	 * already in this command.
	 *
	 * @param cmd
	 *            the command to add
	 */
	public void addSubCommand(SubCommand cmd)
	{
		commands.forEach(command ->
		{
			if (command.getName().equals(cmd.getName()))
				throw new IllegalArgumentException("That subcommand is already registered! Name: " + cmd.getName());
		});

		commands.add(cmd);
	}

	/**
	 * Removes the command under the given name
	 *
	 * @param name
	 * @return false if the name given does not exist, true if it does and the
	 *         command was successfully removed.
	 */
	public boolean removeSubCommand(String name)
	{
		Iterator<SubCommand> iterator = commands.iterator();
		while (iterator.hasNext())
		{
			if (iterator.next().getName().equals(name))
			{
				iterator.remove();
				return true;
			}
		}

		return false;
	}

	public SubCommand[] getSubCommands()
	{
		return (SubCommand[]) commands.toArray();
	}

	/**
	 * This method will be called when the user provides no Sub command <nr>This
	 * should be used to give the user information on the command
	 */
	public abstract void onBlank(CommandSender sender, Command command, String label, String[] args);

	/**
	 * This is called whenever this command is run and before the subcommands are
	 * processed, this should only be used to monitor
	 *
	 * @return Whether to continue and process the sub commands. false will cancel
	 *         quietly
	 * @see CommandExecutor#onCommand(CommandSender, Command, String, String[])
	 */
	public abstract boolean onCommandRun(final CommandSender sender, final Command command, final String label,
			final String[] args);

	/**
	 * This is run when the subcommand the user gave is not valid.
	 */
	public abstract void onInvalid(CommandSender sender, Command command, String label, String[] args);

	/**
	 * This is run when the user provides a valid subcommand, this is run before
	 * running the subcommand
	 *
	 * @return Whether to cancel running the subcommand
	 */
	public abstract boolean onValid(CommandSender sender, Command command, String label, String[] args);

	/**
	 * This is run if the user does not have permission to run the sub command
	 */
	public abstract void onNoPerm(CommandSender sender, Command command, String label, String[] args);

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public String getPermission()
	{
		return permission.getName();
	}
}
