package me.shawshark.craftsharkfactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.UPlayer;

public class chat implements Listener {

	public main m;
	
	public chat(main m) {
		this.m = m;
	}
	
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void Scorechat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		PermissionUser user = PermissionsEx.getUser(p);
		String pprefix = user.getPrefix();
		
		runtake1(p);
		if(!muted.contains(p)) {
			if(!spamming.containsKey(p)) {
				spamming.put(p, 1);
			} else {
				int beforecheck = spamming.get(p);
				spamming.put(p, beforecheck + 1);
				if(beforecheck + 1 == 2) {
					p.sendMessage(ChatColor.RED + "Wooooh, Slow down before you get kicked!");
				} else if(beforecheck + 1 > 3) {
					p.sendMessage(ChatColor.GOLD + "You have been muted for spamming! (30 seconds)");
						muted.add(p);
						removemuted(p);
				}
			}
		}
		
		if(muted.contains(p)) {
			p.sendMessage("");
			p.sendMessage("");
			p.sendMessage(ChatColor.GOLD + "You can't chat while muted!");
			p.sendMessage(ChatColor.GOLD + "Reason you are muted:");
			p.sendMessage(ChatColor.GREEN + "- Spamming. ");
			p.sendMessage(ChatColor.GOLD + "Mute will be expired in (30 seconds)");
		}
		if(!antispam.contains(p) && (!muted.contains(p))) {
			
			antispam.add(p);
			removeantispam(p);
			
			Player player = Bukkit.getPlayerExact(p.getName());
			UPlayer uplayer = UPlayer.get(player);
			Faction faction = uplayer.getFaction();
			
			String faction_name = ChatColor.GRAY  + "[" + ChatColor.GREEN + faction.getName() + ChatColor.GRAY + "] ";
			
			int getscore = m.getConfig().getInt("kills.players.player." + p.getName());
			int getscore_deaths = m.getConfig().getInt("deaths.players.player." + e.getPlayer().getName());
			String message = faction_name + ChatColor.RED + "K: " + getscore + " " + "D: " + getscore_deaths + " " + pprefix + p.getName() + ": " + e.getMessage();
			
			m.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
			
		}
		

		
		e.setCancelled(true);
	}
	
	List<Player> antispam = new ArrayList<Player>();
	public HashMap<Player, Integer> spamming = new HashMap<Player, Integer>();
	List<Player> muted = new ArrayList<Player>();
	
  
	public void removemuted(final Player p ) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(m, new Runnable() {

			@Override
			public void run() {
				muted.remove(p);
				p.sendMessage(ChatColor.GOLD + "You are now un-muted!");
			}
			
		}, 500);
	}

	
	
	public void removeantispam(final Player p ) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(m, new Runnable() {

			@Override
			public void run() {
				antispam.remove(p); // array list.
				spamming.remove(p); //hash map.
			}
			
		}, 80);
	}
	
	public void runtake1(final Player p) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(m, new Runnable() {

			@Override
			public void run() {
				int beforetake1 = spamming.get(p);
				spamming.put(p, beforetake1 - 1 );
			}
			
		}, 40);
	}
	
	
	
	
}
