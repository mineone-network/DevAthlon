package de.mauricius17.devathlon.competitive.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;

public class EnchantmentListener implements Listener {
	
	@EventHandler
	public void onEnchatnItem(EnchantItemEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(!Utils.getIngamePlayers().contains(e.getEnchanter().getUniqueId())) {
			e.setCancelled(true);
			return;
		}
		
		e.setCancelled(false);
	}

	@EventHandler
	public void onEnchatnItem(PrepareItemEnchantEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(!Utils.getIngamePlayers().contains(e.getEnchanter().getUniqueId())) {
			e.setCancelled(true);
			return;
		}
		
		e.setCancelled(false);
	}
}
