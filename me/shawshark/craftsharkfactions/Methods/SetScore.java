package me.shawshark.craftsharkfactions.Methods;

import me.shawshark.craftsharkfactions.main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SetScore {
	
	public main m;
	
	public SetScore(main m) {
		this.m = m;
	}
	
	public void setscores(Player p, Player pkiller) {
		  
		if(p instanceof Player && pkiller instanceof Player) {
			
			int beforecheck = m.getConfig().getInt("kills.players.player." + pkiller.getName());
			int totalupscore = beforecheck + 1;
			m.getConfig().set("kills.players.player." + pkiller.getName(), totalupscore);
					
			int killledbeforecheck = m.getConfig().getInt("deaths.players.player." + p.getName());
			int killedtotalupscore = killledbeforecheck + 1;
			m.getConfig().set("deaths.players.player." + p.getName(), killedtotalupscore);	
			m.saveConfig();
			
		} else {
			p.sendMessage(ChatColor.RED + "You weren't killed by a player, there for no kills or deaths were tracked!");
		}
		m.usb.updatescoreboard(pkiller);
		m.usb.updatescoreboard(p);
	}
}
