package libraries.subcommands.tests;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import XZot1K.plugins.zb.utils.subcommands.MainCommand;
/**
 * A very simple test command to see whether the sub command library works. 
 * @author Ryan Samarakoon
 */
public class TestCommand extends MainCommand {

	public TestCommand() {
		super(new Permission("zotbox.test"), "zottest");
		addSubCommand(new SubC1());
		addSubCommand(new SubC2());
	}

	@Override
	public void onBlank(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage("You gave me nothing! This is where I talk about what I do!");
		sender.sendMessage("Try doing /"+label+" test1");

	}

	@Override
	public boolean onCommandRun(CommandSender sender, Command command, String label, String[] args) {
		if(args.length>=1&&args[0].equals("potato")){
			sender.sendMessage("You like potatos! Ill cancel the subcommand search now!");
			return false;}
		return true;
		
	}

	@Override
	public void onInvalid(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage("That subcommand doesn't exist! Oh my!");
		

	}

	@Override
	public boolean onValid(CommandSender sender, Command command, String label, String[] args) {
		if(args.length>=1&&args[0].equals("melon")){
			sender.sendMessage("you entered melon! Subcommand search happened, but ill cancle the found command from running!");
			return false;
		}
			
		sender.sendMessage("Congrats! you entered a valid command! Let me run it.");
		return true;
	}

	@Override
	public void onNoPerm(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage("You dont have permission! What are you doing here!");

	}
	

}
