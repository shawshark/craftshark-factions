package me.shawshark.craftsharkfactions.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.shawshark.craftsharkfactions.main;

public class UpdateScoreboard {

	public main m;
	
	public UpdateScoreboard(main m) {
		this.m = m;
	}
	
	  public void updatescoreboard(Player p) {
		  if(p instanceof Player) {
			  ScoreboardManager sbm = Bukkit.getScoreboardManager();
			  Scoreboard sb = sbm.getNewScoreboard();
			  Objective ob = sb.registerNewObjective("ststa", "dummy");
			  ob.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Stats");
			  ob.setDisplaySlot(DisplaySlot.SIDEBAR);

			  Score online = ob.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Online"));
			  Score kills = ob.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "kills"));
			  Score deaths = ob.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Deaths"));
			  Score credits = ob.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Credits"));
			  online.setScore(Bukkit.getServer().getOnlinePlayers().length);
			  kills.setScore(m.getConfig().getInt("kills.players.player." + p.getName()));
			  deaths.setScore(m.getConfig().getInt("deaths.players.player." + p.getName()));
			  credits.setScore(m.getConfig().getInt("credits.players.player." + p.getName()));
			  
			  if(m.killstreak.killstreak.containsKey(p)) {
				  int score = m.killstreak.killstreak.get(p);
				  Score killstreak = ob.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Kill Streak"));
				  killstreak.setScore(score);
			  }
			  p.setScoreboard(sb);
		  } 
	  }
	  
	public void updatescoreboardforeveryone() {
		for(Player everyone : m.getServer().getOnlinePlayers()) {
			updatescoreboard(everyone);
		}
	}
}
