package de.mauricius17.devathlon.competitive.utils;

import java.lang.reflect.Field;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class UtilsDisplay {
	
	public static void sendTitle(Player p, String title) {
		IChatBaseComponent message = ChatSerializer.a("{\"text\": \"\"}").a(title);
		PacketPlayOutTitle packet = new PacketPlayOutTitle(EnumTitleAction.TITLE, message);
		    
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	}
	
	public static void sendSubTitle(Player p, String subtitle) {
		IChatBaseComponent message = ChatSerializer.a("{\"text\": \"\"}").a(subtitle);
	    PacketPlayOutTitle packet = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, message);
	    
	    ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	}
	
	public static void reset(Player p) {
		PacketPlayOutTitle packet = new PacketPlayOutTitle(EnumTitleAction.RESET, null);
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	}
	
	public static void clear(Player p) {
		PacketPlayOutTitle packet = new PacketPlayOutTitle(EnumTitleAction.CLEAR, null);
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	}

	public static void sendTabList(Player p, String header, String footer) {
		
		if(header == null)
			header = "";
		if(footer == null) 
			footer = "";
		
		header = ChatColor.translateAlternateColorCodes('&', header);
		footer = ChatColor.translateAlternateColorCodes('&', footer);
	
		IChatBaseComponent tabheader = ChatSerializer.a("{\"text\": \"" + header + "\"}");
		IChatBaseComponent tabfooter = ChatSerializer.a("{\"text\": \"" + footer + "\"}");
		
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(tabheader);
		
		try {
			Field f = packet.getClass().getDeclaredField("b");
		    f.setAccessible(true);
		    f.set(packet, tabfooter);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
		}
	}
	
	public static void sendActionBar(Player p, String message) {
		if(message == null) 
			message = "";
		
		message = ChatColor.translateAlternateColorCodes('&', message);
		
		PlayerConnection con = ((CraftPlayer)p).getHandle().playerConnection;
		
		IChatBaseComponent chat = ChatSerializer.a("{\"text\": \"" + message + "\"}");
		PacketPlayOutChat packet = new PacketPlayOutChat(chat, (byte) 2);
		
		con.sendPacket(packet);
	}
}