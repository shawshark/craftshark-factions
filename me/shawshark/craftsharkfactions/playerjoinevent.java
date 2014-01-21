package me.shawshark.craftsharkfactions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class playerjoinevent {

	public main m;
	
	public playerjoinevent(main m) {
		this.m = m;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
	final Player p = e.getPlayer();
			
	PermissionUser user = PermissionsEx.getUser(p);
	
		m.sendmotd(p);
			
		m.usb.updatescoreboardforeveryone();
			
		if(!p.hasPlayedBefore()) {
				
			e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "CraftShark" + ChatColor.GRAY + "]" + 
					ChatColor.GRAY + " Welcome " + ChatColor.GREEN + p.getName() + ChatColor.GRAY + " to the server!"); 
				   
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(m, new Runnable() {
				@Override
				public void run() {
					m.s.spawn(p);
				}
			}, 5);
				   
			m.setdefaultconfigvalues(p);
			
			if(!user.inGroup("default") ){
				m.givestarter(p);
			} else 
				p.performCommand("kit default");
			
			} else {
				e.setJoinMessage(null);
			}
	}
	
	
	@EventHandler
	public void ScoreonJoin(PlayerJoinEvent e) {
		Player p = (Player)e.getPlayer();
		
		if(!p.hasPlayedBefore()) {
			m.getConfig().set("kills.players.player." + e.getPlayer().getName(), 0);
			m.getConfig().set("deaths.players.player." + e.getPlayer().getName(), 0);
			m.saveConfig();
		}
	}
}
