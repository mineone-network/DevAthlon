package de.mauricius17.devathlon.competitive.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;

public class StartCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	
		if(!(sender instanceof Player)) {
			sender.sendMessage(Utils.getConsole());
			return true;
		}
		
		Player p = (Player) sender;
		
		if(p.isOp()) {
			if(Utils.getGameState() == GameState.LOBBY) {
				if(Bukkit.getOnlinePlayers().size() >= Utils.getMinPlayers()) {
					if(Utils.getLobbyTime() > 10) {
						Utils.setLobbyTime(10);
						p.sendMessage(Utils.getPrefix() + "§aDu hast den Countdown beschleunigt");
					} else {
						p.sendMessage(Utils.getPrefix() + "§7Der Countdown ist zu weit fortgeschritten! Du kannst ihn nicht beschleunigen!");
					}
				} else {
					p.sendMessage(Utils.getPrefix() + "§7Es sind nicht genug Spieler online!");
				}
			} else {
				p.sendMessage(Utils.getPrefix() + "§7Es ist derzeit keine §5Lobby Phase §7aktiv!");
			}
		} else {
			p.sendMessage(Utils.getPrefix() + Utils.getNopermission());
		}
		
		return true;
	}
}
