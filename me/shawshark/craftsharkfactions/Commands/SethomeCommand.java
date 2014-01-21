package me.shawshark.craftsharkfactions.Commands;

import me.shawshark.craftsharkfactions.main;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class SethomeCommand implements CommandExecutor {

	public main m;
	
	public SethomeCommand(main m) {
		this.m = m;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Error: This command can only be run from in-game!");
			} else {
				Player p = (Player) sender;
				PermissionUser user = PermissionsEx.getUser(p);
					
					Location l = p.getLocation();
					
					m.getConfig().set("home.players.player." + p.getName() + ".x", l.getBlockX());
					m.getConfig().set("home.players.player." + p.getName() + ".y", l.getBlockY());
					m.getConfig().set("home.players.player." + p.getName() + ".z", l.getBlockZ());
					m.getConfig().set("home.players.player." + p.getName() + ".yaw", l.getYaw());
					m.getConfig().set("home.players.player." + p.getName() + ".pitch", l.getPitch());
					m.getConfig().set("home.players.player." + p.getName() + ".world", p.getWorld().getName());
					
					
					m.saveConfig();
					
					p.sendMessage(ChatColor.GOLD + "You have successfully set your home at: "
							+ ChatColor.RED + l.getBlockX() + "x, " + l.getBlockY() + "y, " + l.getBlockZ() + "z");
					
					if(!user.inGroup("default")) {
						p.sendMessage(ChatColor.RED + "Hello, you seem to be a donator, Setting more than 1 home at the moment isn't possible, But it will be soon!");
					}
 			}
		return false;
	}
}
