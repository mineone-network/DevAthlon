package de.mauricius17.devathlon.competitive.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;

public class InventoryListener implements Listener {
	
	@EventHandler
	public void onBrew(BrewEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onCraft(CraftItemEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			
			if(!Utils.getIngamePlayers().contains(p.getUniqueId())) {
				e.setCancelled(true);
				return;
			}
			
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onFurnacBurn(FurnaceBurnEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onFurnaceSmelt(FurnaceSmeltEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			
			if(!Utils.getIngamePlayers().contains(p.getUniqueId())) {
				e.setCancelled(true);
				return;
			}
			
			e.setCancelled(false);
		}
	}
}
