package me.shawshark.craftsharkfactions.Commands;

import me.shawshark.craftsharkfactions.main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class FeedCommand implements CommandExecutor {
	
	public main m;
	
	public FeedCommand(main m) {
		this.m = m;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Error: This command can only be run from in-game!");
			} else {
				Player p = (Player) sender;
				PermissionUser user = PermissionsEx.getUser(p);
					
					if(!user.inGroup("default")) {
						
						if(!m.feed.contains(p)) {
							
							m.feed.add(p);
							p.setHealth(20);
							p.setFoodLevel(20);
							m.feedcooldown(p);
							p.sendMessage(m.prefix + ChatColor.GREEN + " You food bar is now full!!");
						} else {
							p.sendMessage(m.prefix + ChatColor.RED + " Please wait before feeding again!");
						}
				}
 			}
		return false;
	}

}
