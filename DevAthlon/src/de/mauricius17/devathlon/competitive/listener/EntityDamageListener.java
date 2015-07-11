package de.mauricius17.devathlon.competitive.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;

public class EntityDamageListener implements Listener {

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if(Utils.getGameState() != GameState.INGAME) {
			if(e.getEntity() instanceof Player) {
				e.setCancelled(true);
				return;
			}
			
			e.setCancelled(false);
			return;
		}
		
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player damager = (Player) e.getDamager();
			Player target = (Player) e.getEntity();
			
			if(!Utils.getIngamePlayers().contains(damager.getUniqueId())) {
				e.setCancelled(true);
				return;
			}
			
			if(!Utils.getIngamePlayers().contains(target.getUniqueId())) {
				e.setCancelled(true);
				return;
			}
		}
		
		if(e.getEntity() instanceof Player && (!(e.getDamager() instanceof Player))) {
			Player target = (Player) e.getEntity();
			
			if(!Utils.getIngamePlayers().contains(target.getUniqueId())) {
				e.setCancelled(true);
				return;
			}
		}
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if(Utils.getGameState() != GameState.INGAME) {
			if(e.getEntity() instanceof Player) {
				e.setCancelled(true);
				return;
			}
			
			e.setCancelled(false);
			return;
		}
		
		if(e.getEntity() instanceof Player) {
			Player target = (Player) e.getEntity();

			if(!Utils.getIngamePlayers().contains(target.getUniqueId())) {
				e.setCancelled(true);
				return;
			}
			
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onEntityDamageByBlock(EntityDamageByBlockEvent e) {
		if(Utils.getGameState() != GameState.INGAME) {
			if(e.getEntity() instanceof Player) {
				e.setCancelled(true);
				return;
			}
			
			e.setCancelled(false);
			return;
		}
		
		if(e.getEntity() instanceof Player) {
			Player target = (Player) e.getEntity();

			if(!Utils.getIngamePlayers().contains(target.getUniqueId())) {
				e.setCancelled(true);
				return;
			}
			
			e.setCancelled(false);
		}
	}
}
