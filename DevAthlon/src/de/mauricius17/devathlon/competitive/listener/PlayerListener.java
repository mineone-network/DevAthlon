package de.mauricius17.devathlon.competitive.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerAchievementsAwarded(PlayerAchievementAwardedEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBedEnter(PlayerBedEnterEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(!Utils.getIngamePlayers().contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			return;
		}
		
		e.setCancelled(false);
	}
	
	@EventHandler
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(!Utils.getIngamePlayers().contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			return;
		}
		
		e.setCancelled(false);
	}
	
	@EventHandler
	public void onPlayerBucketFill(PlayerBucketFillEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(!Utils.getIngamePlayers().contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			return;
		}
		
		e.setCancelled(false);
	}
	 
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(!Utils.getIngamePlayers().contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			return;
		}
		
		e.setCancelled(false);
	}
	
	@EventHandler
	public void onPlayerEditBook(PlayerEditBookEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(!Utils.getIngamePlayers().contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			return;
		}
		
		e.setCancelled(false);
	}
	
	@EventHandler
	public void onPlayerFish(PlayerFishEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(!Utils.getIngamePlayers().contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			return;
		}
		
		e.setCancelled(false);
	}
	
	@EventHandler
	public void onItemConsume(PlayerItemConsumeEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(!Utils.getIngamePlayers().contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			return;
		}
		
		e.setCancelled(false);
	}
	
	@EventHandler
	public void onPlayerPickUpItem(PlayerPickupItemEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(!Utils.getIngamePlayers().contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			return;
		}
		
		e.setCancelled(false);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(!Utils.getIngamePlayers().contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			return;
		}
		
		e.setCancelled(false);
	}
}
