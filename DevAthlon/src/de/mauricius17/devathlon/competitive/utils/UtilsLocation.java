package de.mauricius17.devathlon.competitive.utils;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class UtilsLocation {

	public static void setLocation(Location loc, String path) {
		String world = loc.getWorld().getName();
		
		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();
		double yaw = loc.getYaw();
		double pitch = loc.getPitch();
		
		Utils.getLocations().set("location." + path + ".world", world);
		Utils.getLocations().set("location." + path + ".x", x);
		Utils.getLocations().set("location." + path + ".y", y);
		Utils.getLocations().set("location." + path + ".z", z);
		Utils.getLocations().set("location." + path + ".yaw", yaw);
		Utils.getLocations().set("location." + path + ".pitch", pitch);

		try {
			Utils.getLocations().save(Utils.getLocationFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Location getLocation(String path) {
		String world = Utils.getLocations().getString("location." + path + ".world");
		double x = Utils.getLocations().getDouble("location." + path + ".x");
		double y = Utils.getLocations().getDouble("location." + path + ".y");
		double z = Utils.getLocations().getDouble("location." + path + ".z");
		double yaw = Utils.getLocations().getDouble("location." + path + ".yaw");
		double pitch = Utils.getLocations().getDouble("location." + path + ".pitch");
		
		Location loc = new Location(Bukkit.getWorld(world), x, y, z);
		loc.setYaw((float) yaw);
		loc.setPitch((float) pitch);
		
		return loc;
	}
}
