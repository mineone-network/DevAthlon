package de.mauricius17.devathlon.competitive.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class UtilsGameTeleport {

	/*
	 * Hier ist eine Methode, mit der die Spieler auf die Map teleportiert werden! Wichtig ist, dass diese Map durch ein Skript,
	 * nach jedem Start des Servers, neu geladen wird! 
	 * 
	 */
	
	@SuppressWarnings("deprecation")
	public static void teleportPlayersToMap() {
		World world = Bukkit.getWorld("world");
		Location loc = world.getSpawnLocation();
		
		int players = Bukkit.getOnlinePlayers().size();
		
		double degree = 2 * Math.PI / players;
		int i = 1;
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			
			double x = Math.cos(degree * i) * 500;
			double y = loc.getY();
			double z = Math.sin(degree * i) * 500;
			
			Location teleportedloc = loc.clone().add(x, y + 5, z);
			
			int x1 = teleportedloc.getChunk().getX();
			int z1 = teleportedloc.getChunk().getZ();
			
			teleportedloc.getWorld().loadChunk(x1, z1);
			teleportedloc.getWorld().refreshChunk(x1,z1);
		
			all.teleport(teleportedloc);
			i++;
		}
	}
	
}
