package de.mauricius17.devathlon.competitive.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;

public class PlayerLoginListener implements Listener {

	@EventHandler
	public void onLogin(PlayerLoginEvent e) { 
		Player p = e.getPlayer();
		
		if(Utils.getGameState() != GameState.LOBBY && Utils.getGameState() != GameState.GRACE && Utils.getGameState() != GameState.INGAME) {
			p.sendMessage("§cDas Spiel befindet sich in einer Phase, in der du nicht beitreten kannst!");
			e.setResult(Result.KICK_OTHER);
			return;
		}
		
		e.allow();
	}
}
