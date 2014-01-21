package me.shawshark.craftsharkfactions.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import me.shawshark.craftsharkfactions.main;

public class Home {
	
	public main m;
	
	public Home(main m) {
		this.m = m;
	}
	
	public void sendhome(final Player p) {
		  
		PermissionUser user = PermissionsEx.getUser(p);
	  
		if(m.getConfig().getString("home.players.player." + p.getName()) != null) {
			if(!m.homelist.contains(p))  {
				m.homelist.add(p);
				if(!user.inGroup("default")) {
					p.sendMessage(ChatColor.RED + "Hello, you seem to be a donator, Setting more than 1 home at the moment isn't possible, But it will be soon!");
				}
				
				p.sendMessage(ChatColor.GREEN + "Teleporting to your home, Please wait 4 seconds...");
			  
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(m, new Runnable() {
					@Override
					public void run() {
						if(m.homelist.contains(p)) {
							m.homelist.remove(p);
							
							Location loc = p.getLocation();
								  
							loc.setX(m.getConfig().getInt("home.players.player." + p.getName() + ".x"));
							loc.setY(m.getConfig().getInt("home.players.player." + p.getName() + ".y"));
							loc.setZ(m.getConfig().getInt("home.players.player." + p.getName() + ".z"));
							loc.setYaw(m.getConfig().getInt("home.players.player." + p.getName() + ".yaw"));
							loc.setPitch(m.getConfig().getInt("home.players.player." + p.getName() + ".pitch"));
							
							World world = Bukkit.getWorld(m.getConfig().getString("home.players.player." + p.getName() + ".world"));
							
							loc.setWorld(world);
							
							p.teleport(loc);  
							p.sendMessage(ChatColor.GREEN + "You have successfully teleported to your home!");
						} else {
							if(m.homelist.contains(p)) {
								m.homelist.remove(p);
							}
						}
					}
				}, 80);
			} else {
			  p.sendMessage(ChatColor.RED + "You seem to already be teleporting to your home!");
		  } 
		} else {
		  p.sendMessage(ChatColor.RED + "Error: You seem to not have a sethome yet!");
	  }
	}

}
