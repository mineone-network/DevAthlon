package de.mauricius17.devathlon.competitive.mysql;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MySQL {

	private static Connection connection;
	private static String host;
	private static int port;
	private static String database;
	private static String username;
	private static String password;
	private static ExecutorService executor;
	private static String TABLE;
	
	private static String COLUMN_GAMES = "games", COLUMN_WINS = "wins", COLUMN_KILLS = "kills", COLUMN_DEATHS = "deaths";
	
	private static File MySQLFile = new File("plugins/Competitive", "mysql.yml");
	private static FileConfiguration sql = YamlConfiguration.loadConfiguration(MySQLFile);

	static {
		executor = Executors.newCachedThreadPool();
	}
	
	public static String getCOLUMN_DEATHS() {
		return COLUMN_DEATHS;
	}
	
	public static String getCOLUMN_GAMES() {
		return COLUMN_GAMES;
	}
	
	public static String getCOLUMN_KILLS() {
		return COLUMN_KILLS;
	}
	
	public static String getCOLUMN_WINS() {
		return COLUMN_WINS;
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public static FileConfiguration getSql() {
		return sql;
	}
	
	public static ExecutorService getExecutor() {
		return executor;
	}
	
	public static String getTABLE() {
		return TABLE;
	}
	
	public MySQL() {

		sql.addDefault("mysql", false);
		sql.addDefault("allowPassword", true);
		sql.addDefault("host", "host");
		sql.addDefault("port", Integer.valueOf(3306));
		sql.addDefault("database", "Database");
		sql.addDefault("username", "Username");
		sql.addDefault("password", "Password");
		sql.addDefault("table_name", "competitive");

		sql.options().copyDefaults(true);

		try {
			sql.save(MySQLFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		host = sql.getString("host");
		port = sql.getInt("port");
		database = sql.getString("database");
		username = sql.getString("username");
		TABLE = sql.getString("table_name");

		if (sql.getBoolean("allowPassword"))
			password = sql.getString("password");
		else
			password = "";
	}
	
	public static void connect() {
		if(!isConnected()) {
			try {
				connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static boolean isConnected() {
		try {
			if(connection != null && connection.isValid(1)) {
				return true;
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public static void disconnect() {
		if(isConnected()) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void createTable() {
		try {
			String qry = "CREATE TABLE IF NOT EXISTS " + TABLE + " (playeruuid text, " + COLUMN_GAMES + " int, " + COLUMN_WINS + " int, " + COLUMN_KILLS + " int, " + COLUMN_DEATHS + " int)";
			PreparedStatement stmt = connection.prepareStatement(qry);
			stmt.executeUpdate();
			stmt.close();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
}
