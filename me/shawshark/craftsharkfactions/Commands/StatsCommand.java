package me.shawshark.craftsharkfactions.Commands;

import me.shawshark.craftsharkfactions.main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {

	public main m;
	
	public StatsCommand(main m) {
		this.m = m;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Error: This command can only be run from in-game!");
			} else {
				Player p = (Player) sender;
					
					int credits = m.getConfig().getInt("credits.players.player." + p.getName());
					int deaths = m.getConfig().getInt("deaths.players.player."+ p.getName());
					int kills = m.getConfig().getInt("kills.players.player."+p.getName());
					
					p.sendMessage(ChatColor.GREEN + "Your stats are as follow:");
					p.sendMessage(ChatColor.GOLD + "Credits: " + ChatColor.RED + credits);
					p.sendMessage(ChatColor.GOLD + "Kills: " + ChatColor.RED + kills);
					p.sendMessage(ChatColor.GOLD + "Deaths: " + ChatColor.RED + deaths);
 			}
		return false;
	}

}
