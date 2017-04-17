package libraries.subcommands.tests;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import XZot1K.plugins.zb.utils.subcommands.SubCommand;
/**
 * 
 * @author Ryan Samarakoon
 *
 */
public class SubC1 implements SubCommand {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> tab=new ArrayList<>();
		tab.add("hello");
		tab.add("there");
		return tab;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage("Hello there!");
		return true;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "greet";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return "zotbox.test.greet";
	}

}
