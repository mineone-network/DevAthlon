package de.mauricius17.devathlon.competitive.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.mauricius17.devathlon.competitive.consumer.Consumer;

public class MySQLStats {
	
	public static void getInt(final String uuid, final String column, final Consumer<Integer> consumer) {
		MySQL.getExecutor().execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					PreparedStatement stmt = MySQL.getConnection().prepareStatement("SELECT " + column + " FROM " + MySQL.getTABLE() + " WHERE playeruuid = ?");
					stmt.setString(1, uuid);
					ResultSet rs = stmt.executeQuery();
					
					if(rs.next()) {
						consumer.accept(rs.getInt(column));
					} else {
						consumer.accept(-1);
					}
					
					rs.close();
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void updateInt(final String uuid, final int value, final String column) {
		MySQL.getExecutor().execute(new Runnable() {
			
			@Override
			public void run() {
				getInt(uuid, column, new Consumer<Integer>() {

					@Override
					public void accept(Integer result) {
						try {
							if(result == -1) {
								PreparedStatement stmt = MySQL.getConnection().prepareStatement("INSERT INTO " + MySQL.getTABLE() + " (playeruuid, " 
										+ MySQL.getCOLUMN_GAMES() + ", "
										+ MySQL.getCOLUMN_WINS() + ", "
										+ MySQL.getCOLUMN_KILLS() + ", "
										+ MySQL.getCOLUMN_DEATHS() + ") VALUES (?, ?, ?, ?, ?) ");
								
								stmt.setString(1, uuid);
								
								if(column.equals(MySQL.getCOLUMN_GAMES())) {
									stmt.setInt(2, value);
									stmt.setInt(3, 0);
									stmt.setInt(4, 0);
									stmt.setInt(5, 0);
								} 
								
								if(column.equals(MySQL.getCOLUMN_WINS())) {
									stmt.setInt(2, 0);
									stmt.setInt(3, value);
									stmt.setInt(4, 0);
									stmt.setInt(5, 0);
								} 
								
								if(column.equals(MySQL.getCOLUMN_KILLS())) {
									stmt.setInt(2, 0);
									stmt.setInt(3, 0);
									stmt.setInt(4, value);
									stmt.setInt(5, 0);
								}  
								
								if(column.equals(MySQL.getCOLUMN_DEATHS())) {
									stmt.setInt(2, 0);
									stmt.setInt(3, 0);
									stmt.setInt(4, 0);
									stmt.setInt(5, value);
								}
								
								stmt.executeUpdate();
								stmt.close();
							} else {
								PreparedStatement stmt = MySQL.getConnection().prepareStatement("UPDATE " + MySQL.getTABLE() + " SET " + column + " = ? WHERE playeruuid = ?");
								stmt.setInt(1, value);
								stmt.setString(2, uuid);
								stmt.executeUpdate();
								stmt.close();
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
	}
}
