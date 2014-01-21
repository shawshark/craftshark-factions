package me.shawshark.craftsharkfactions.Commands;

import me.shawshark.craftsharkfactions.main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand implements CommandExecutor {
	
	public main m;
	
	public VoteCommand(main m) {
		this.m = m;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Error: This command can only be run from in-game!");
			} else {
				Player p = (Player) sender;
					p.sendMessage("" +ChatColor.GOLD + "               -- How to vote. --");
					p.sendMessage(ChatColor.GOLD + "- " + ChatColor.DARK_GREEN + "To vote go to our website at: " + ChatColor.RED + "www.craftshark.net");
					p.sendMessage(ChatColor.GOLD + "- " + ChatColor.DARK_GREEN + "Then hit the 'Vote` button.");
					p.sendMessage(ChatColor.GOLD + "- " + ChatColor.DARK_GREEN + "Once you have done this, Vote on each of the voting sites on our website.");
					p.sendMessage(ChatColor.GOLD + "- " + ChatColor.DARK_GREEN + "Then wait for your reward.");
					
					p.sendMessage("");
					p.sendMessage(ChatColor.GOLD + "                   -- Rewards! --");
					p.sendMessage(ChatColor.GREEN + "- 2 Sponger (Per vote)");
 			}
		return false;
	}

}
