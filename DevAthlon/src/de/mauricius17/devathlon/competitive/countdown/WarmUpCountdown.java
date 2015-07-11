package de.mauricius17.devathlon.competitive.countdown;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.mauricius17.devathlon.competitive.economy.EconomySystem;
import de.mauricius17.devathlon.competitive.system.Competitive;
import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;
import de.mauricius17.devathlon.competitive.utils.UtilsDisplay;

public class WarmUpCountdown implements Runnable {

	@Override
	public void run() {
		if(Utils.getGameState() == GameState.WARMUP) {
			if(Bukkit.getOnlinePlayers().size() >= Utils.getMinPlayers()) {
				if(Utils.getWarmUpTime() == 0) {
					Utils.setGameState(GameState.GRACE);
					
					for(Player players : Bukkit.getOnlinePlayers()) {
						
						
						EconomySystem system = new EconomySystem(1000);
						Utils.getMoney().put(players.getUniqueId(), system);
						
						UtilsDisplay.sendActionBar(players, "§7Das Spiel beginnt! §5Viel Spaß:)");
						players.sendMessage(Utils.getPrefix() + "§7Das Spiel beginnt! §5Viel Spaß:)");
						players.sendMessage(Utils.getPrefix() + "§7Die Schutzzeit endet in §52 Minuten§7!");
						players.playSound(players.getLocation(), Sound.NOTE_PLING, 1F, 1F);
					}
			
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Competitive.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							Utils.setGameState(GameState.INGAME);
							
							for(Player players: Bukkit.getOnlinePlayers()) {
								players.sendMessage(Utils.getPrefix() + "§7Die Schutzzeit ist beendet! Viel Erfolg beim wirtschaften!");
								players.sendMessage(Utils.getPrefix() + "§7Dein Vermögen wird nun alle 40 Sekunden mit §54% §7versteuert! Sobald du über §51300 Euro §7hast mit §515%§7!");
								players.playSound(players.getLocation(), Sound.NOTE_PLING, 1F, 1F);
								UtilsDisplay.sendTabList(players, Utils.getPrefix(), "§c-> Ingame Phase <-");
							}
						}
					}, 20*Utils.getGraceTime());
					
					Utils.setIngameCountdown(Bukkit.getScheduler().scheduleSyncRepeatingTask(Competitive.getInstance(), new EconomyCountdown(), 0, 20));
					Bukkit.getScheduler().cancelTask(Utils.getWarmUpCountdown());
				}
				
				if(Utils.getWarmUpTime() == 4) {
					for(Player players: Bukkit.getOnlinePlayers()) {
						for(int i = 0; i < players.getInventory().getSize(); i++) {
							players.getInventory().setItem(i, new ItemStack(Material.WOOD_SPADE));
							players.updateInventory();
						}
					}
				}
				
				if(Utils.getWarmUpTime() == 1) {
					for(Player players: Bukkit.getOnlinePlayers()) {
						players.getInventory().clear();
					}
				}
				
				if(Utils.getWarmUpTime() > 0) {
					for(Player players: Bukkit.getOnlinePlayers()) {
						UtilsDisplay.sendActionBar(players, Utils.getPrefix() + "§7Das Spiel beginnt in §5" + Utils.getWarmUpTime() + " " +
								(Utils.getWarmUpTime() == 1 ? "§7Sekunde" : "§7Sekunden"));
						players.playSound(players.getLocation(), Sound.NOTE_BASS, 1F, 1F);
					}
					
					Utils.setWarmUpTime(Utils.getWarmUpTime() -1);
				}	
			} else {
				Utils.setGameState(GameState.AFTERTIME);
				
				for(Player players: Bukkit.getOnlinePlayers()) {
					players.sendMessage(Utils.getPrefix() + "§cEs sind nicht mehr genug Spieler online, damit das Spiel starten kann!");
				}
				
				Utils.setAfterTimeCountdown(Bukkit.getScheduler().scheduleSyncRepeatingTask(Competitive.getInstance(), new RestartCountdown(), 0, 20));
				Bukkit.getScheduler().cancelTask(Utils.getWarmUpCountdown());
			}
		}
	}
}
