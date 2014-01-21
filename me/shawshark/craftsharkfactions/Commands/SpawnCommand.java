package me.shawshark.craftsharkfactions.Commands;

import me.shawshark.craftsharkfactions.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
	
	public main m;
	
	public SpawnCommand(main m) {
		this.m = m;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "Error: This command can only be run from in-game!");
			} else {
				final Player p = (Player) sender;
					if(m.spawn_list.contains(p)) {
						p.sendMessage(ChatColor.GREEN + "You are already teleporting to spawn, Please wait...");
					} else {
						p.sendMessage(ChatColor.GREEN + "You will be teleported to spawn in 4 seconds, Don't move!");
						m.spawn_list.add(p);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(m, new Runnable() {
							@Override
							public void run() {
								if(m.spawn_list.contains(p)) {
									p.sendMessage(ChatColor.GOLD + "You have been telepoted to spawn!");
									m.s.spawn(p);
									m.spawn_list.remove(p);
								}
							 }
						}, 80);
				}
 			}
		return false;
	}
}
