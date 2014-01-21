package me.shawshark.craftsharkfactions.Events;

import me.shawshark.craftsharkfactions.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {
	
	public main m;
	
	public EntityDamage(main m) {
		this.m = m;
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player)e.getEntity();
			if (p instanceof Player) { 
				if(m.spawnprotection.contains(p)) {
					e.setCancelled(true);
				}
			}
			if(m.randomportal.contains(p)) {
				e.setCancelled(true);
			}
		}
	}
}
