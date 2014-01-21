package me.shawshark.craftsharkfactions.Commands;

import me.shawshark.craftsharkfactions.main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DonateCommand implements CommandExecutor {

	public main m;
	
	public DonateCommand(main m) {
		this.m = m;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Error: This command can only be run from in-game!");
			} else {
				Player p = (Player) sender;
					p.sendMessage("" +ChatColor.GOLD + "               -- How to donate. --");
					p.sendMessage(ChatColor.GOLD + "- " + ChatColor.DARK_GREEN + "To donate go to our website at: " + ChatColor.RED + "www.craftshark.net");
					p.sendMessage(ChatColor.GOLD + "- " + ChatColor.DARK_GREEN + "Then hit the 'Donate` button.");
					p.sendMessage(ChatColor.GOLD + "- " + ChatColor.DARK_GREEN + "Then enter your minecraft username.");
					p.sendMessage(ChatColor.GOLD + "- " + ChatColor.DARK_GREEN + "Select the donation rank you want.");
					p.sendMessage(ChatColor.GOLD + "- " + ChatColor.DARK_GREEN + "Then finally follow the on screen prompts on how to donate.");
					
					p.sendMessage(ChatColor.GOLD + "                   -- Rewards! --");
					p.sendMessage(ChatColor.GOLD + "- " + ChatColor.DARK_GREEN + "To view what you get from donating type, /warp donate");
 			}
		return false;
	}
}
