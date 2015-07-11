package de.mauricius17.devathlon.competitive.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.mauricius17.devathlon.competitive.countdown.RestartCountdown;
import de.mauricius17.devathlon.competitive.mysql.MySQL;
import de.mauricius17.devathlon.competitive.mysql.MySQLStats;
import de.mauricius17.devathlon.competitive.system.Competitive;
import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;
import de.mauricius17.devathlon.competitive.utils.UtilsSpectator;

public class PlayerDeathListener implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		
		if(e.getEntity().getKiller() instanceof Player) {
			Player killer = e.getEntity().getKiller();
			
			double money = Utils.getMoney().get(p.getUniqueId()).getMoney();
			
			if(Utils.getMoney().containsKey(killer.getUniqueId())) {
				Utils.getMoney().get(killer.getUniqueId()).addMoney(money);
				killer.sendMessage(Utils.getPrefix() + "§7Du hast §5" + Math.round(money) + " §7Euro durch den Kill an §5" + p.getName() + " §7erhalten!");
				MySQLStats.updateInt(killer.getUniqueId().toString(), 1, MySQL.getCOLUMN_KILLS());
			}
			
			e.setDeathMessage(Utils.getPrefix() + "§7Der Spieler §5" + p.getName() + " §7wurde von §5" + killer.getName() + " §7getötet!");
		} else {
			Location loc = p.getLocation();
			e.setDeathMessage(Utils.getPrefix() + "§7Der Spieler §5" + p.getName() + " §7ist gestorben bei §8[§5" + loc.getBlockX() + "§8|§5" + loc.getBlockY() + "§8|§5" + loc.getBlockZ() + "§8]");
		}
		
		if(Utils.getMoney().containsKey(p.getUniqueId())) {
			Utils.getMoney().remove(p.getUniqueId());			
		}
		
		if(Utils.getIngamePlayers().contains(p.getUniqueId())) {
			Utils.getIngamePlayers().remove(p.getUniqueId());
		}
		
		UtilsSpectator.setPlayerInSpectatorMode(p);
		
		if(Utils.getIngamePlayers().size() <= 1) {
			Utils.setGameState(GameState.AFTERTIME);
			
			for(Player players: Bukkit.getOnlinePlayers()) {
				if(Utils.getMoney().containsKey(players.getUniqueId())) {
					Utils.getMoney().remove(players.getUniqueId());						
				}
				
				if(Utils.getIngamePlayers().size() == 1) {
					players.sendMessage(Utils.getPrefix() + "§7Das Spiel ist beendet! §5" + Bukkit.getPlayer(Utils.getIngamePlayers().get(0)).getName() + " §7hat gewonnen!");
					MySQLStats.updateInt(Utils.getIngamePlayers().get(0).toString(), 1, MySQL.getCOLUMN_WINS());
				} else {
					players.sendMessage(Utils.getPrefix() + "§7Das Spiel ist beendet! Niemand hat gewonnen!");	
				}
			}
			
			Utils.setAfterTimeCountdown(Bukkit.getScheduler().scheduleSyncRepeatingTask(Competitive.getInstance(), new RestartCountdown(), 0, 20));
		}
		
		MySQLStats.updateInt(p.getUniqueId().toString(), 1, MySQL.getCOLUMN_DEATHS());
	}
}
