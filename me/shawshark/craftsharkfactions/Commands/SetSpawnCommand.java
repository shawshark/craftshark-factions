package me.shawshark.craftsharkfactions.Commands;

import me.shawshark.craftsharkfactions.main;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
	
	public main m;
	
	public SetSpawnCommand(main m) {
		this.m = m;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Error: This command can only be run from in-game!");
			} else {
				Player p = (Player) sender;
					if(p.isOp())  {
						Location l = p.getLocation();
						m.getConfig().set("server.spawn.x", l.getBlockX());
						m.getConfig().set("server.spawn.y", l.getBlockY());
						m.getConfig().set("server.spawn.z", l.getBlockZ());
						m.getConfig().set("server.spawn.yaw", l.getYaw());
						m.getConfig().set("server.spawn.pitch", l.getPitch());
						
						m.getConfig().set("server.spawn.world", p.getWorld().getName());
						
						m.saveConfig();
						
					} else{
						p.sendMessage(ChatColor.RED + "You have no permission for this command!");
					}
 			}
		return false;
	}
}
