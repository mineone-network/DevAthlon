package de.mauricius17.devathlon.competitive.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;
import de.mauricius17.devathlon.competitive.utils.UtilsDisplay;
import de.mauricius17.devathlon.competitive.utils.UtilsLocation;
import de.mauricius17.devathlon.competitive.utils.UtilsPrepare;
import de.mauricius17.devathlon.competitive.utils.UtilsSpectator;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
	
		if(Utils.getGameState() == GameState.LOBBY) {
			UtilsPrepare.prepareAtJoin(p);
			p.teleport(UtilsLocation.getLocation("lobby"));
			e.setJoinMessage("§7< §5" + p.getName() + " §7ist dem Spiel beigetreten!");
			UtilsDisplay.sendTabList(p, Utils.getPrefix(), "§c-> Lobby Phase <-");
			return;
		}
		
		p.teleport(UtilsLocation.getLocation("spectator"));
		UtilsSpectator.setPlayerInSpectatorMode(p);
		
		if(Utils.getGameState() == GameState.GRACE) {
			UtilsDisplay.sendTabList(p, Utils.getPrefix(), "§c-> Grace Phase <-");
		}
		
		if(Utils.getGameState() == GameState.INGAME) {
			UtilsDisplay.sendTabList(p, Utils.getPrefix(), "§c-> Ingame Phase <-");				
		}
		
		e.setJoinMessage("");
	}
}
