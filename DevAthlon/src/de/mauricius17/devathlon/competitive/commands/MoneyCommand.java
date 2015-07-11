package de.mauricius17.devathlon.competitive.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;

public class MoneyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Utils.getConsole());
			return true;
		}
		
		Player p = (Player) sender;
		
		if(Utils.getGameState() == GameState.INGAME || Utils.getGameState() == GameState.GRACE) {
			if(Utils.getIngamePlayers().contains(p.getUniqueId()) && Utils.getMoney().containsKey(p.getUniqueId())) {
				p.sendMessage(Utils.getPrefix() + "§7Du hast §5" + Math.round(Utils.getMoney().get(p.getUniqueId()).getMoney()) + " §7Euro!");
			} else {
				p.sendMessage(Utils.getPrefix() + "§cNur Spieler, die am Spiel teilnehmen, verfügen über Geld!");
			}
		} else {
			p.sendMessage(Utils.getPrefix() + "§cDas Spiel befindet sich nicht in der Ingame Phase! Du verfügst über kein Geld!");
		}
		
		return true;
	}
}
