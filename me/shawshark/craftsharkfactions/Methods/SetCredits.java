package me.shawshark.craftsharkfactions.Methods;

import me.shawshark.craftsharkfactions.main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SetCredits {
	
	public main m;
	
	public SetCredits(main m) {
		this.m = m;
		
	}
	
	public void setcredits(Player p, Player killed, int credits) {
		  
		if(p instanceof Player) {	  
			  
			int beforecheck = m.getConfig().getInt("credits.players.player." + p.getName());
			  
			int totalupscore = beforecheck + credits;
			  
			m.getConfig().set("credits.players.player."  + p.getName(), totalupscore);
			  
			m.saveConfig();
			  
			p.sendMessage(ChatColor.GOLD + "You now have: " + ChatColor.RED + totalupscore + ChatColor.GOLD + " Credits!");
			  
			m.usb.updatescoreboard(p);
		}  
	}
}
