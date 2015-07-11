package de.mauricius17.devathlon.competitive.countdown;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import de.mauricius17.devathlon.competitive.mysql.MySQL;
import de.mauricius17.devathlon.competitive.mysql.MySQLStats;
import de.mauricius17.devathlon.competitive.system.Competitive;
import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;
import de.mauricius17.devathlon.competitive.utils.UtilsDisplay;
import de.mauricius17.devathlon.competitive.utils.UtilsGameTeleport;
import de.mauricius17.devathlon.competitive.utils.UtilsPrepare;

public class LobbyCountdown implements Runnable {

	private boolean waitingMessage = true;
	
	@Override
	public void run() {
		if(Utils.getGameState() == GameState.LOBBY) {
			if(Bukkit.getOnlinePlayers().size() < Utils.getMinPlayers()) {
				if(Utils.getLobbyTime() != 60) {
					Utils.setLobbyTime(60);
				}
				
				for(Player players: Bukkit.getOnlinePlayers()) {
					if(players.getLevel() != 0) {
						players.setLevel(0);
					}
				}
				
				if(waitingMessage) {
					waitingMessage = false;
					
					for(Player players: Bukkit.getOnlinePlayers()) {
						UtilsDisplay.sendActionBar(players, "§cWarten auf weitere Spieler ...");
						players.playSound(players.getLocation(), Sound.NOTE_SNARE_DRUM, 1F, 1F);
					}
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Competitive.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							waitingMessage = true;
						}
					}, 20*15);
				}
			} else {
				
				if(Utils.getLobbyTime() == 60
						|| Utils.getLobbyTime() == 40
						|| Utils.getLobbyTime() == 30
						|| Utils.getLobbyTime() == 20
						|| Utils.getLobbyTime() == 15
						|| Utils.getLobbyTime() <= 10
						&& Utils.getLobbyTime() > 0) {
					for(Player players: Bukkit.getOnlinePlayers()) {
						UtilsDisplay.sendActionBar(players, Utils.getPrefix() + "§7Das Spiel startet in §5" + Utils.getLobbyTime() + " " +
								(Utils.getLobbyTime() == 1 ? "§7Sekunde" : "§7Sekunden"));
						players.playSound(players.getLocation(), Sound.NOTE_BASS, 1F, 1F);
					}
				}
				
				if(Utils.getLobbyTime() == 0) {
					Utils.setGameState(GameState.WARMUP);
					
					for(Player players: Bukkit.getOnlinePlayers()) {
						Utils.getIngamePlayers().add(players.getUniqueId());
						UtilsPrepare.prepareAtGameStart(players);
						UtilsDisplay.sendActionBar(players, "§aDas Spiel startet nun! Mach dich bereit!");
						players.sendMessage(Utils.getPrefix() +  "§aDas Spiel startet nun! Mach dich bereit!");
						
						UtilsDisplay.sendTabList(players, Utils.getPrefix(), "§c-> WarmUp Phase <-");
						
						MySQLStats.updateInt(players.getUniqueId().toString(), 1, MySQL.getCOLUMN_GAMES());
						
						players.playSound(players.getLocation(), Sound.NOTE_PLING, 1F, 1F);
					}
					
					UtilsGameTeleport.teleportPlayersToMap();
					
					Utils.setWarmUpCountdown(Bukkit.getScheduler().scheduleSyncRepeatingTask(Competitive.getInstance(), new WarmUpCountdown(), 20, 20));
					Bukkit.getScheduler().cancelTask(Utils.getLobbyCountdown());
				}
				
				if(Utils.getLobbyTime() > 0) {
					for(Player players: Bukkit.getOnlinePlayers()) {
						players.setLevel(Utils.getLobbyTime());
					}
					
					Utils.setLobbyTime(Utils.getLobbyTime() - 1);
				}
			}
		}
	}
}
