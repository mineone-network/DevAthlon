package de.mauricius17.devathlon.competitive.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.mauricius17.devathlon.competitive.consumer.Consumer;
import de.mauricius17.devathlon.competitive.mysql.MySQL;
import de.mauricius17.devathlon.competitive.mysql.MySQLStats;
import de.mauricius17.devathlon.competitive.utils.Utils;

public class StatsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Utils.getConsole());
			return true;
		}
		
		final Player p = (Player) sender;
		
		if(args.length == 0) {
			p.sendMessage("§7Stats von §5" + p.getName());
			
			MySQLStats.getInt(p.getUniqueId().toString(), MySQL.getCOLUMN_GAMES(), new Consumer<Integer>() {

				@Override
				public void accept(Integer result) {
					p.sendMessage("§7Spiele: §5" + (result == -1 ? "0" : result));
				}
			});
			
			MySQLStats.getInt(p.getUniqueId().toString(), MySQL.getCOLUMN_WINS(), new Consumer<Integer>() {

				@Override
				public void accept(Integer result) {
					p.sendMessage("§7Siege: §5" + (result == -1 ? "0" : result));
				}
			});
			
			MySQLStats.getInt(p.getUniqueId().toString(), MySQL.getCOLUMN_KILLS(), new Consumer<Integer>() {

				@Override
				public void accept(Integer result) {
					p.sendMessage("§7Kills: §5" + (result == -1 ? "0" : result));
				}
			});
			
			MySQLStats.getInt(p.getUniqueId().toString(), MySQL.getCOLUMN_DEATHS(), new Consumer<Integer>() {

				@Override
				public void accept(Integer result) {
					p.sendMessage("§7Tode: §5" + (result == -1 ? "0" : result));
				}
			});
			
		} else {
			p.sendMessage(Utils.getPrefix() + "§cLeider ist dieser Befehl nicht so verfügbar! Verwende /stats");
		}
		
		return true;
	}
}
