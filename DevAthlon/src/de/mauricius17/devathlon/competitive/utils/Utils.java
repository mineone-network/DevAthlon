package de.mauricius17.devathlon.competitive.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.mauricius17.devathlon.competitive.economy.EconomySystem;
import de.mauricius17.devathlon.competitive.system.GameState;

public class Utils {
	
	private static String prefix = "§8[§5Competitive§8] ", console = "[Competitive] Nur ein Spieler kann diese Aktion ausfuehren!", nopermission = "§cDu hast keine Operator Rechte!";
	
	private static List<UUID> ingamePlayers = new ArrayList<>();
	private static GameState gameState = GameState.LOBBY;
	private static HashMap<UUID, EconomySystem> money = new HashMap<>();
	
	private static int lobbyTime = 60, lobbyCountdown;
	private static int warmUpTime = 5, warmUpCountdown;
	private static int graceTime = 120;
	private static int ingameCountdown;
	private static int afterTime = 30, afterTimeCountdown;
	
	private static int minPlayers = 4, maxPlayers = 12;
	
	private static File locationFile = new File("plugins/Competitive", "location.yml");
	private static FileConfiguration locations = YamlConfiguration.loadConfiguration(locationFile);
	
	public static void setIngameCountdown(int ingameCountdown) {
		Utils.ingameCountdown = ingameCountdown;
	}
	
	public static int getIngameCountdown() {
		return ingameCountdown;
	}
	
	public static int getMaxPlayers() {
		return maxPlayers;
	}
	
	public static int getMinPlayers() {
		return minPlayers;
	}
	
	public static void setAfterTime(int afterTime) {
		Utils.afterTime = afterTime;
	}
	
	public static void setAfterTimeCountdown(int afterTimeCountdown) {
		Utils.afterTimeCountdown = afterTimeCountdown;
	}
	
	public static void setLobbyCountdown(int lobbyCountdown) {
		Utils.lobbyCountdown = lobbyCountdown;
	}
	
	public static void setLobbyTime(int lobbyTime) {
		Utils.lobbyTime = lobbyTime;
	}
	
	public static void setWarmUpCountdown(int warmUpCountdown) {
		Utils.warmUpCountdown = warmUpCountdown;
	}
	
	public static void setWarmUpTime(int warmUpTime) {
		Utils.warmUpTime = warmUpTime;
	}
	
	public static void setIngamePlayers(List<UUID> ingamePlayers) {
		Utils.ingamePlayers = ingamePlayers;
	}
	
	public static int getAfterTime() {
		return afterTime;
	}
	
	public static int getAfterTimeCountdown() {
		return afterTimeCountdown;
	}
	
	public static int getGraceTime() {
		return graceTime;
	}
	
	public static int getLobbyCountdown() {
		return lobbyCountdown;
	}
	
	public static int getLobbyTime() {
		return lobbyTime;
	}

	public static int getWarmUpCountdown() {
		return warmUpCountdown;
	}
	
	public static int getWarmUpTime() {
		return warmUpTime;
	}
	
	public static File getLocationFile() {
		return locationFile;
	}
	
	public static FileConfiguration getLocations() {
		return locations;
	}
	
	public static String getNopermission() {
		return nopermission;
	}
	
	public static HashMap<UUID, EconomySystem> getMoney() {
		return money;
	}
	
	public static List<UUID> getIngamePlayers() {
		return ingamePlayers;
	}
	
	public static void setGameState(GameState gameState) {
		Utils.gameState = gameState;
	}
	
	public static GameState getGameState() {
		return gameState;
	}
	
	public static String getConsole() {
		return console;
	}
	
	public static String getPrefix() {
		return prefix;
	}
}
