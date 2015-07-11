package de.mauricius17.devathlon.competitive.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;

public class PlayerMoveListener implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if(Utils.getGameState() == GameState.WARMUP) {
			Player p = e.getPlayer();
	        Location from = e.getFrom();
	        Location to = e.getTo();
	        double x = Math.floor(from.getX());
	        double z = Math.floor(from.getZ());
	        
	      	 if(Math.floor(to.getX()) !=x || Math.floor(to.getZ()) !=z){
	       		 x+=.5;
	             z+=.5;
	             p.teleport(new Location(from.getWorld(), x, from.getY(), z, from.getYaw(), from.getPitch()));
	         }	
		}
	}
}
