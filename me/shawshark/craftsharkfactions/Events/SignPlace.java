package me.shawshark.craftsharkfactions.Events;

import me.shawshark.craftsharkfactions.main;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignPlace implements Listener {
	
	public main m;
	
	public SignPlace(main m) {
		this.m = m;
	}
	
	@EventHandler
	public void onsignPlace(SignChangeEvent e) {
		if(e.getBlock().getState().getType() == Material.SIGN_POST || e.getBlock().getState().getType() == Material.WALL_SIGN) {
			if(e.getPlayer().isOp()) {
				String line0 = ChatColor.translateAlternateColorCodes('&', e.getLine(0));
				String line1 = ChatColor.translateAlternateColorCodes('&', e.getLine(1));
				String line2 = ChatColor.translateAlternateColorCodes('&', e.getLine(2));
				String line3 = ChatColor.translateAlternateColorCodes('&', e.getLine(3));
				  
				e.setLine(0, line0);
				e.setLine(1, line1);
				e.setLine(2, line2);
				e.setLine(3, line3);
			}
		}
	}
}
