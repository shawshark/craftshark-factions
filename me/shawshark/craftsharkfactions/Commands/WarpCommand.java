package me.shawshark.craftsharkfactions.Commands;

import me.shawshark.craftsharkfactions.main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {
	
	public main m;
	
	public WarpCommand(main m) {
		this.m = m;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Error: This command can only be run from in-game!");
			} else {
				Player p = (Player) sender;
					if(args.length < 1) {
						p.sendMessage(ChatColor.GOLD + "/warp [warp name]"); 
						p.sendMessage(ChatColor.GOLD + "" + m.getConfig().getStringList("server.warps"));
					} else {
						
					if(m.getConfig().getString("server.warps."+args[0]).equalsIgnoreCase("")) {
						p.sendMessage(ChatColor.RED + "This warp doesn't seem to exist!");
					} else {
						if(!m.warp.contains(p)) {
							m.warp.add(p);
							p.sendMessage(ChatColor.GREEN + "Warping to " + args[0] + ". Please wait 4 seconds...");
							m.warp(args[0], p);
						}		
					}
				}
 			}
		return false;
	}
}
