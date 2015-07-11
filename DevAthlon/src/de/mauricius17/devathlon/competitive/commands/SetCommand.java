package de.mauricius17.devathlon.competitive.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mauricius17.devathlon.competitive.utils.Utils;
import de.mauricius17.devathlon.competitive.utils.UtilsLocation;

public class SetCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Utils.getConsole());
			return true;
		}
		
		Player p = (Player) sender;
		
		if(p.isOp()) {
			if(args.length != 1) {
				sendHelp(p);
				return true;
			} 
			
			if(args[0].equalsIgnoreCase("lobby")) {
				UtilsLocation.setLocation(p.getLocation(), "lobby");
			}  else 
				if(args[0].equalsIgnoreCase("spectator")) {
					UtilsLocation.setLocation(p.getLocation(), "spectator");
				} else {
					sendHelp(p);
				}
		} else {
			p.sendMessage(Utils.getPrefix() + Utils.getNopermission());
		}
		
		return true;
	}
	
	private void sendHelp(Player p) {
		p.sendMessage("§8==========[§5Competitive§8]==========");
		p.sendMessage("§7/set lobby & /set spectator");
		p.sendMessage("§8=================================");
	}
}
 