package de.mauricius17.devathlon.competitive.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.mauricius17.devathlon.competitive.countdown.RestartCountdown;
import de.mauricius17.devathlon.competitive.mysql.MySQL;
import de.mauricius17.devathlon.competitive.mysql.MySQLStats;
import de.mauricius17.devathlon.competitive.system.Competitive;
import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;

public class PlayerQuitListener implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		if(Utils.getIngamePlayers().contains(p.getUniqueId())) {
			Utils.getIngamePlayers().remove(p.getUniqueId());
		}
		
		if(Utils.getMoney().containsKey(p.getUniqueId())) {
			Utils.getMoney().remove(p.getUniqueId());						
		}
		
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setQuitMessage(Utils.getPrefix() + "§7Der Spieler §5" + p.getName() + " §7hat den Server verlassen!");
		} else {
			e.setQuitMessage(Utils.getPrefix() + "§7Der Spieler §5" + p.getName() + " §7ist gestorben!");
			
			MySQLStats.updateInt(p.getUniqueId().toString(), 1, MySQL.getCOLUMN_DEATHS());
		}
		
		if(Utils.getGameState() == GameState.INGAME) {
			if(Utils.getIngamePlayers().size() <= 1) {
				Utils.setGameState(GameState.AFTERTIME);
				
				for(Player players: Bukkit.getOnlinePlayers()) {
					if(Utils.getMoney().containsKey(players.getUniqueId())) {
						Utils.getMoney().remove(players.getUniqueId());						
					}
					
					if(Utils.getIngamePlayers().size() == 1) {
						players.sendMessage(Utils.getPrefix() + "§7Das Spiel ist beendet! §5" + Bukkit.getPlayer(Utils.getIngamePlayers().get(0)).getName() + " §7hat gewonnen!");						
					} else {
						players.sendMessage(Utils.getPrefix() + "§7Das Spiel ist beendet! Niemand hat gewonnen!");	
					}
				}
				
				Bukkit.getScheduler().scheduleSyncRepeatingTask(Competitive.getInstance(), new RestartCountdown(), 0, 20);
				Bukkit.getScheduler().cancelTask(Utils.getIngameCountdown());
			}	
		}
	}
}
