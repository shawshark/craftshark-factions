package me.shawshark.craftsharkfactions.tasks;

import me.shawshark.craftsharkfactions.main;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class spawn extends BukkitRunnable {

	public final main m;
	
	public spawn(main m) {
		this.m = m;
	}
	
	@Override
	public void run() {
		new BukkitRunnable() {
			@Override
			public void run() {
				if(!m.spawn_list.contains(Bukkit.getPlayer("shawshark"))) {
					cancel();
				} else {
					m.getServer().broadcastMessage("testing spawn task.");
				}
			}
		}.runTaskLater(m, 20);
	}
	
}
