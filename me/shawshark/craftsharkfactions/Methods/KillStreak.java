package me.shawshark.craftsharkfactions.Methods;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.shawshark.craftsharkfactions.main;

public class KillStreak {
	
	public main m;
	
	public KillStreak(main m) {
		this.m = m;
	}
	
  	public String KillStreakPrefix = ChatColor.GRAY + "[" + ChatColor.RED + "Kill Streak" + ChatColor.GRAY + "]";
  	
	public HashMap<Player, Integer> killstreak = new HashMap<Player, Integer>();
	
	public void killstreak( Player pkiller  , Player p  ) {
		
		
		if(killstreak.containsKey(p)) {
			killstreak.remove(p);
			m.getServer().broadcastMessage(KillStreakPrefix + " " + ChatColor.GREEN + "" + p.getName() + " Has been defeated by " + pkiller.getName());
		} else {
			//nothing
		}
		
		if(!killstreak.containsKey(pkiller)) {
			killstreak.put(pkiller, 1);
		} else {
			int score = killstreak.get(pkiller);
			
			int finalscore = score + 1;
			
			killstreak.put(pkiller, finalscore);
			
			if(killstreak.get(pkiller) > 1) {
				m.getServer().broadcastMessage(KillStreakPrefix + " " +  ChatColor.GREEN + "" + pkiller.getName() + " Is on a kill streak of " + killstreak.get(pkiller));
				
				if(killstreak.get(pkiller) == 10) {
					m.getServer().broadcastMessage(KillStreakPrefix + " " +  ChatColor.GOLD + "" + pkiller.getName() + " Has reached a kill streak of 10! Come on stop them!");
					pkiller.sendMessage(KillStreakPrefix + " " +  ChatColor.GREEN + "You have been rewarded with 400 credits!");
					m.scredits.setcredits(pkiller, p, 400);
				}
				
			}
			
		}
		m.usb.updatescoreboard(p);
		m.usb.updatescoreboard(pkiller);
	}

}
