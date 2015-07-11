package de.mauricius17.devathlon.competitive.listener;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockMultiPlaceEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.NotePlayEvent;
import org.bukkit.event.block.SignChangeEvent;

import de.mauricius17.devathlon.competitive.economy.BlockEconomy;
import de.mauricius17.devathlon.competitive.economy.EconomySystem;
import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;

public class BlockListener implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(!Utils.getIngamePlayers().contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			return;
		}
		
		Location loc = e.getBlock().getLocation();
		Location middle = e.getPlayer().getWorld().getSpawnLocation();
		
		if(middle.distance(loc) >= 505) {
			e.getPlayer().sendMessage(Utils.getPrefix() + "§cDu darfst auserhalb der Map keine Blöcke abbauen!");
			e.setCancelled(true);
			return;
		}
		
		EconomySystem system = Utils.getMoney().get(e.getPlayer().getUniqueId());
	
		double m = 0;
		
		for(int i = 0; i < BlockEconomy.values().length; i++) {
			String block = BlockEconomy.values()[i].toString();
			String type = e.getBlock().getType().toString();
			
			if(block.equals(type)) {
				m = BlockEconomy.values()[i].getPrize();
			}
		}

		system.addMoney(m);
		e.setCancelled(false);
	}
	
	@EventHandler
	public void onBlockBurn(BlockBurnEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockDamage(BlockDamageEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockDiespense(BlockDispenseEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockFade(BlockFadeEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockForm(BlockFormEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockGrow(BlockGrowEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockIgnite(BlockIgniteEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockMultiPlace(BlockMultiPlaceEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(!Utils.getIngamePlayers().contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
			return;
		}
		
		Location loc = e.getBlock().getLocation();
		Location middle = e.getPlayer().getWorld().getSpawnLocation();
		
		if(middle.distance(loc) >= 505) {
			e.getPlayer().sendMessage(Utils.getPrefix() + "§cDu darfst auserhalb der Map keine Blöcke setzen!");
			e.setCancelled(true);
			return;
		}
		
		e.setCancelled(false);
	}
	
	@EventHandler
	public void onBlockSpread(BlockSpreadEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityBlockForm(EntityBlockFormEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onNotePlay(NotePlayEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
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
	public void onLeavesDecay(LeavesDecayEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}	
}
