package me.shawshark.craftsharkfactions.Commands;

import me.shawshark.craftsharkfactions.main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class HealCommand implements CommandExecutor {
	
	public main m;
	
	public HealCommand(main m) {
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
						if(!m.heal.contains(p)) {
							m.heal.add(p);
							p.setHealth(20);
							p.setFoodLevel(20);
							m.healcooldown(p);
							p.sendMessage(m.prefix + ChatColor.GREEN + " You have been healed!");
						} else {
							p.sendMessage(m.prefix + ChatColor.RED + " Please wait before healing again!");
						}
					}
 			}
		return false;
	}
}
