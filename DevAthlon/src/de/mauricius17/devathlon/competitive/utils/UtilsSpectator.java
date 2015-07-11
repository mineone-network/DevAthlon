package de.mauricius17.devathlon.competitive.utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class UtilsSpectator {

	public static void setPlayerInSpectatorMode(Player p) {
		p.setGameMode(GameMode.ADVENTURE);

		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		
		p.setAllowFlight(true);
		p.setFlying(true);
		
		p.setLevel(0);
		p.setExp(0F);
		p.setMaxHealth(20.0D);
		p.setHealth(20.0D);
		p.setFireTicks(0);
		p.setFoodLevel(20);
		
		Vector vec = p.getLocation().getDirection().multiply(2D).setY(1D);
		p.setVelocity(vec);
		
		for(Player players: Bukkit.getOnlinePlayers()) {
			players.hidePlayer(p);
 		}
	
		UtilsDisplay.sendTitle(p, "§cDu nun Spectator");
	}
}