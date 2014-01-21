package me.shawshark.craftsharkfactions.Commands;

import me.shawshark.craftsharkfactions.main;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetwarpCommand implements CommandExecutor {
	
	public main m;
	
	public SetwarpCommand(main m) {
		this.m = m;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Error: This command can only be run from in-game!");
			} else {
				Player p = (Player) sender;
					if(p.isOp()) {
						Location l = p.getLocation();
						m.getConfig().set("server.warps."+args[0]+".x", l.getBlockX());
						m.getConfig().set("server.warps."+args[0]+".y", l.getBlockY());
						m.getConfig().set("server.warps."+args[0]+".z", l.getBlockZ());
						m.getConfig().set("server.warps."+args[0]+".yaw", l.getYaw());
						m.getConfig().set("server.warps."+args[0]+".pitch", l.getPitch());
						m.saveConfig();
					} else {
						p.sendMessage(ChatColor.RED + "You have no permissions to set warps!");
					}
 			}
		return false;
	}
}
