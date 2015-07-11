package de.mauricius17.devathlon.competitive.world;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import de.mauricius17.devathlon.competitive.system.Competitive;
import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;

public class WorldBoarder implements Listener {

	private List<String> message = new ArrayList<String>();
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		final Player p = e.getPlayer();
		
		if(Utils.getGameState() == GameState.INGAME || Utils.getGameState() == GameState.GRACE) {
			if(p.getWorld().getName().equalsIgnoreCase("world")) {
				Location loc = p.getLocation();
				Location middle = p.getWorld().getSpawnLocation();
				
				if(middle.distance(loc) > 505) {
					
					int ax = middle.getBlockX();
					int ay = middle.getBlockY();
					int az = middle.getBlockZ();
					
					int bx = loc.getBlockX();
					int by = loc.getBlockY();
					int bz = loc.getBlockZ();
					
					int x = ax - bx;
					int y = ay - by;
					int z = az - bz;
					
					Vector vec = new Vector(x, y, z).normalize();
					vec.multiply(0.5D);
					vec.setY(0.3D);
					p.setVelocity(vec);
					p.getWorld().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 3);
					p.playSound(loc, Sound.ENDERDRAGON_WINGS, 1, 1);
					
					if(!message.contains(p.getName())) {
						p.sendMessage(Utils.getPrefix() + "§7Die Map ist hier zuende!");
						message.add(p.getName());
						
						Bukkit.getScheduler().scheduleSyncDelayedTask(Competitive.getInstance(), new Runnable() {

							@Override
							public void run() {
								message.remove(p.getName());
							}
							
						}, 20*1);
					}
				}	
			}
		}
	}
}
