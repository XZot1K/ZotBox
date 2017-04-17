package XZot1K.plugins.zb.utils.subcommands;

import org.bukkit.command.TabExecutor;

/**
 * The interface to be used when making sub commands
 *
 * @author Ryan Samarakoon
 */
public interface SubCommand extends TabExecutor
{
    /**
     * Returns the name of this sub command. This will be represented as /maincommand (name)
     *
     * @return the sub command name
     */
    String getName();

    /**
     * The permission the user must have to use this sub command.<br>
     * This will be determined as (main command permission).(this string)<br>
     * Can be null if no permission is needed to run.
     *
     * @return the permission as a string
     */
    String getPermission();
}
