package libraries.subcommands.tests;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import XZot1K.plugins.zb.utils.subcommands.SubCommand;

public class SubC2 implements SubCommand {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> tab=new ArrayList<>();
		tab.add("yummy");
		tab.add("melon");
		return tab;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage("I LIKE MELONS...YOU SHOULDNT BE HERE ACTUALLY");
		return true;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "melon";
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return "zotbox.melon";
	}

}
