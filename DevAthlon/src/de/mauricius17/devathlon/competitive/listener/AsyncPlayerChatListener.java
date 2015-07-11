package de.mauricius17.devathlon.competitive.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;

public class AsyncPlayerChatListener implements Listener {

	@EventHandler
	public void onAsnycPlayerChat(AsyncPlayerChatEvent e) {
	
		Player p = e.getPlayer();
		
		if(Utils.getGameState() != GameState.INGAME && Utils.getGameState() != GameState.GRACE) {
			e.setFormat("§7" + p.getName() + " ➟ §7" + e.getMessage());
		} else {
			if(Utils.getIngamePlayers().contains(p.getUniqueId())) {
				e.setFormat("§7" + p.getName() + " ➟  §7" + e.getMessage());
			} else {
				e.setCancelled(true);
				
				for(Player players: Bukkit.getOnlinePlayers()) {
					if(!(Utils.getIngamePlayers().contains(players.getUniqueId()))) {
						players.sendMessage("§8[§4✘§8] §7" + p.getName() + " ➟  §7" + e.getMessage());
					}
				}
			}
		}
	}
}
