package de.mauricius17.devathlon.competitive.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;

import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;

public class HangingListener implements Listener {

	@EventHandler
	public void onHangingBreakByEntity(HangingBreakByEntityEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(e.getRemover() instanceof Player) {
			Player p = (Player) e.getRemover();
			
			if(!Utils.getIngamePlayers().contains(p.getUniqueId())) {
				e.setCancelled(true);
				return;
			}
			
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onHangingBreak(HangingBreakEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onHangingPlace(HangingPlaceEvent e) {		
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(e.getPlayer() instanceof Player) {
			Player p = (Player) e.getPlayer();
			
			if(!Utils.getIngamePlayers().contains(p.getUniqueId())) {
				e.setCancelled(true);
				return;
			}
			
			e.setCancelled(false);
		}
	}

}
