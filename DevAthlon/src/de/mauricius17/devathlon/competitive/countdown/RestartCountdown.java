package de.mauricius17.devathlon.competitive.countdown;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;
import de.mauricius17.devathlon.competitive.utils.UtilsDisplay;

public class RestartCountdown implements Runnable {

	@Override
	public void run() {
		if(Utils.getGameState() == GameState.AFTERTIME) {
			if(Utils.getAfterTime() == 30
					|| Utils.getAfterTime() == 20
					|| Utils.getAfterTime() == 15
					|| Utils.getAfterTime() <= 10
					&& Utils.getAfterTime() > 0) {
				for(Player players: Bukkit.getOnlinePlayers()) {
					UtilsDisplay.sendActionBar(players, Utils.getPrefix() + "§cDer Server restartet in §5" + Utils.getAfterTime() + " " +
							(Utils.getAfterTime() == 1 ? "§cSekunde" : "§cSekunden"));
					players.playSound(players.getLocation(), Sound.NOTE_BASS, 1F, 1F);
				}
			}
			
			if(Utils.getAfterTime() == 0) {
				for(Player players: Bukkit.getOnlinePlayers()) {
					players.kickPlayer("§cDer Server restartet jetzt!");
				}
				
				Bukkit.shutdown();
			}
			
			if(Utils.getAfterTime() > 0) {
				for(Player players: Bukkit.getOnlinePlayers()) {
					players.setLevel(Utils.getAfterTime());
				}
				
				Utils.setAfterTime(Utils.getAfterTime() - 1);
			}
		}
	}
}
