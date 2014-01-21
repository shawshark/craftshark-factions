package me.shawshark.craftsharkfactions.Methods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import me.shawshark.craftsharkfactions.main;

public class Spawn {

	public main m;
	
	public Spawn(main m) {
		this.m = m;
	}
	
	public void spawn( final Player p ) {
		
		m.addpro.addprotection(p);
		Location loc = p.getLocation();
		loc.setX(m.getConfig().getInt("server.spawn.x"));
		loc.setY(m.getConfig().getInt("server.spawn.y"));
		loc.setZ(m.getConfig().getInt("server.spawn.z"));
		loc.setYaw(m.getConfig().getInt("server.spawn.yaw"));
		loc.setPitch(m.getConfig().getInt("server.spawn.pitch"));
		
		World world = Bukkit.getWorld(m.getConfig().getString("server.spawn.world"));
		
		loc.setWorld(world);
		p.teleport(loc);
  }
}
