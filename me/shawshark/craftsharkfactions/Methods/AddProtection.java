package me.shawshark.craftsharkfactions.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.shawshark.craftsharkfactions.main;

public class AddProtection {
	
	public main m;
	
	public AddProtection(main m) {
		this.m = m;
	}
	
	public void addprotection(final Player p) {
		if(p instanceof Player) {
			if(!m.spawnprotection.contains(p)) {
				m.spawnprotection.add(p);
				p.sendMessage(ChatColor.GOLD + "You are now under 20 seconds of protection!");
				
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(m, new Runnable() {
					@Override
					public void run() {
							
						if(p.isOnline()) {
							if(m.spawnprotection.contains(p)){
								m.spawnprotection.remove(p);
								p.sendMessage(ChatColor.GOLD + "Your 20 seconds protection is now off, You are now able to take damage!");
							}
						}
					}
				}, 400);
			}
		}
	}
}
