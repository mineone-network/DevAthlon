package de.mauricius17.devathlon.competitive.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.HorseJumpEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PigZapEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;

public class EntityListener implements Listener {
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onCreeperPower(CreeperPowerEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityBreakDoor(EntityBreakDoorEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityChangeBlock(EntityChangeBlockEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}

	@EventHandler
	public void onEntityCreatePortal(EntityCreatePortalEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onExplode(EntityExplodeEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onPortal(EntityPortalEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityRegainHealth(EntityRegainHealthEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if(!Utils.getIngamePlayers().contains(p.getUniqueId())) {
				e.setCancelled(true);
				return;
			}
			
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onShootBow(EntityShootBowEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if(!Utils.getIngamePlayers().contains(p.getUniqueId())) {
				e.setCancelled(true);
				return;
			}
			
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityTame(EntityTameEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityTarget(EntityTargetEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(e.getTarget() instanceof Player) {
			Player p = (Player) e.getTarget();
			
			if(!Utils.getIngamePlayers().contains(p.getUniqueId())) {
				e.setCancelled(true);
				return;
			}
			
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onEntityTargetLivingEntity(EntityTargetLivingEntityEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(e.getTarget() instanceof Player) {
			Player p = (Player) e.getTarget();
			
			if(!Utils.getIngamePlayers().contains(p.getUniqueId())) {
				e.setCancelled(true);
				return;
			}
			
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onExplosionPrime(ExplosionPrimeEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if(!Utils.getIngamePlayers().contains(p.getUniqueId())) {
				e.setCancelled(true);
				return;
			}
			
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onHorseJump(HorseJumpEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onItemDespawn(ItemDespawnEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onItemSpawn(ItemSpawnEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	
	@EventHandler
	public void onPigZap(PigZapEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) e.setCancelled(true);
	}
	@EventHandler
	public void onPlayerLeashEntity(PlayerLeashEntityEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if(!Utils.getIngamePlayers().contains(p.getUniqueId())) {
				e.setCancelled(true);
				return;
			}
			
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent e) {
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setCancelled(true);
			return;
		}
		
		if(e.getEntity().getShooter() instanceof Player) {
			Player p = (Player) e.getEntity().getShooter();
			
			if(!Utils.getIngamePlayers().contains(p.getUniqueId())) {
				e.setCancelled(true);
				return;
			}
			
			e.setCancelled(false);
		}
	}
}
