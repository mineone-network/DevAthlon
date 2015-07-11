package de.mauricius17.devathlon.competitive.system;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.mauricius17.devathlon.competitive.commands.MoneyCommand;
import de.mauricius17.devathlon.competitive.commands.SetCommand;
import de.mauricius17.devathlon.competitive.commands.StartCommand;
import de.mauricius17.devathlon.competitive.commands.StatsCommand;
import de.mauricius17.devathlon.competitive.countdown.LobbyCountdown;
import de.mauricius17.devathlon.competitive.listener.AsyncPlayerChatListener;
import de.mauricius17.devathlon.competitive.listener.BlockListener;
import de.mauricius17.devathlon.competitive.listener.EnchantmentListener;
import de.mauricius17.devathlon.competitive.listener.EntityDamageListener;
import de.mauricius17.devathlon.competitive.listener.EntityListener;
import de.mauricius17.devathlon.competitive.listener.HangingListener;
import de.mauricius17.devathlon.competitive.listener.InventoryListener;
import de.mauricius17.devathlon.competitive.listener.PlayerDeathListener;
import de.mauricius17.devathlon.competitive.listener.PlayerJoinListener;
import de.mauricius17.devathlon.competitive.listener.PlayerListener;
import de.mauricius17.devathlon.competitive.listener.PlayerLoginListener;
import de.mauricius17.devathlon.competitive.listener.PlayerMoveListener;
import de.mauricius17.devathlon.competitive.listener.PlayerQuitListener;
import de.mauricius17.devathlon.competitive.mysql.MySQL;
import de.mauricius17.devathlon.competitive.utils.Utils;
import de.mauricius17.devathlon.competitive.world.WorldBoarder;

public class Competitive extends JavaPlugin {

	private static Competitive instance;
	
	@Override
	public void onEnable() {
		instance = this;
		
		registerCommands();
		registerEvents();
		
		new MySQL();
		
		for(World worlds: Bukkit.getWorlds()) {
			worlds.setTime(0);
			worlds.setStorm(false);
			worlds.setThundering(false);
		}
		
		if(MySQL.getSql().getBoolean("mysql")) {
			MySQL.connect();
			MySQL.createTable();
		} else {
			Bukkit.getConsoleSender().sendMessage("§cYou need a MySQL connection. Add your configurations in the mysql.yml");
			Bukkit.shutdown();
		}
		
		Utils.setLobbyCountdown(Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new LobbyCountdown(), 20, 20));
	}
	
	@Override
	public void onDisable() {
		
		for(Player players: Bukkit.getOnlinePlayers()) {
			players.kickPlayer("§cServer restart!");
		}
		
		MySQL.disconnect();
	}
	
	public static Competitive getInstance() {
		return instance;
	}
	
	private void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentListener(), this);
		Bukkit.getPluginManager().registerEvents(new EntityDamageListener(), this);
		Bukkit.getPluginManager().registerEvents(new EntityListener(), this);
		Bukkit.getPluginManager().registerEvents(new HangingListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLoginListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		Bukkit.getPluginManager().registerEvents(new WorldBoarder(), this);
		Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
	}
	
	private void registerCommands() {
		getCommand("stats").setExecutor(new StatsCommand());
		getCommand("set").setExecutor(new SetCommand());
		getCommand("start").setExecutor(new StartCommand());
		getCommand("money").setExecutor(new MoneyCommand());
	}
}
