package de.mauricius17.devathlon.competitive.countdown;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.mauricius17.devathlon.competitive.economy.EconomySystem;
import de.mauricius17.devathlon.competitive.system.GameState;
import de.mauricius17.devathlon.competitive.utils.Utils;
import de.mauricius17.devathlon.competitive.utils.UtilsDisplay;

public class EconomyCountdown implements Runnable{

	int i = 40;
	int i2 = 0;
	
	@Override
	public void run() {
		if(Utils.getGameState() == GameState.INGAME || Utils.getGameState() == GameState.GRACE) {
			for(Player players: Bukkit.getOnlinePlayers()) {
				if(Utils.getIngamePlayers().contains(players.getUniqueId()) && Utils.getMoney().containsKey(players.getUniqueId())) {
					EconomySystem system = Utils.getMoney().get(players.getUniqueId());
					double money = system.getMoney();
					
					UtilsDisplay.sendActionBar(players, "§7Dein Geld: §5" + Math.round(money) + " §7Euro");
				}
 			}
			
			if(i == 0) {
				for(Player players: Bukkit.getOnlinePlayers()) {
					if(Utils.getIngamePlayers().contains(players.getUniqueId()) && Utils.getMoney().containsKey(players.getUniqueId())) {
						EconomySystem system = Utils.getMoney().get(players.getUniqueId());
						
						double tax;
						
						if(i2 < 600) {
							if(system.getMoney() > 1300) {
								tax = EconomySystem.getPart(15, system.getMoney());
							} else {
								tax = EconomySystem.getPart(4, system.getMoney());						
							}		
							
							system.removeMoney(tax);
						} else {
							if(system.getMoney() > 1300) {
								tax = EconomySystem.getPart(30, system.getMoney());
							} else {
								tax = EconomySystem.getPart(20, system.getMoney());						
							}		
							
							system.removeMoney(tax);
						}
						
						
						if(system.getMoney() <= 0) {
							players.setHealth(0.0D);
						}	
					}
				}
				
				i = 40;
			}
			
			if(i2 == 600) {
				for(Player players: Bukkit.getOnlinePlayers()) {
					players.sendMessage(Utils.getPrefix() + "§510 Minuten §7sind vorbei! Ab sofort wird das Vermögen von Spielern, alle 40 Sekunden, unter §51300 Euro mit 20% §7und das Vermögen von Spielern §5über 1300 Euro mit 30% §7versteuert!");
				}
			}
			
			if(i > 0) {
				i--;
			}
			
			i2++;
		}
	}
}
