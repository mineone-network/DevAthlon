package de.mauricius17.devathlon.competitive.utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class UtilsPrepare {

	public static void prepareAtJoin(Player p) {
		p.setGameMode(GameMode.ADVENTURE);

		p.getInventory().clear();
		p.getInventory().setArmorContents(null);

		p.setFlying(false);
		p.setAllowFlight(false);
		
		p.setLevel(0);
		p.setExp(0F);
		p.setMaxHealth(20.0D);
		p.setHealth(20.0D);
		p.setFireTicks(0);
		p.setFoodLevel(60);
	}
	
	public static void prepareAtGameStart(Player p) {
		p.setGameMode(GameMode.SURVIVAL);
		p.getInventory().clear();
		p.setLevel(0);
	}
}