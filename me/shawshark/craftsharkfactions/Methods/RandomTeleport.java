package me.shawshark.craftsharkfactions.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.shawshark.craftsharkfactions.main;

public class RandomTeleport {
	
	public main m;
	
	public RandomTeleport(main m) {
		this.m = m;
	}
	
	public void randomteleport(final Player p) {
		p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 1));
		p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 1 ));
		p.setVelocity(new Vector(0, 10, 0));
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(m, new Runnable() {
			@Override
			public void run() {
				double randomX = Math.random()*1000000;
				double randomZ = Math.random()*1000000;
				Location loc = new Location(m.getServer().getWorld("world"), randomX, 279, randomZ);
				p.teleport(loc);
				p.sendMessage(ChatColor.RED + " You have been teleported to " + ChatColor.GREEN + randomX + ", " + "279 " + ", " + randomZ);
				p.setAllowFlight(false);
			}
		}, 20);
	}

}
