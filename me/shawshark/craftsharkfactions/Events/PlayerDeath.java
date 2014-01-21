package me.shawshark.craftsharkfactions.Events;

import java.util.Iterator;
import java.util.Random;

import me.shawshark.craftsharkfactions.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class PlayerDeath implements Listener {

	
	public main m;
	
	public PlayerDeath(main m) {
		this.m = m;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler (priority=EventPriority.HIGHEST)
	  public void onDeathEvent(final PlayerDeathEvent e) {
		  
		  final Player p = e.getEntity();
		  Player pkiller = e.getEntity().getKiller();
		  PermissionUser user = PermissionsEx.getUser(p);
		  PermissionUser pkilleruser = PermissionsEx.getUser(pkiller);
		
			  
			  if(p.isDead()) {
				  p.setHealth(20);
				  p.setFoodLevel(20);
				  
				  
			  }
			  
			  for (PotionEffect effect : p.getActivePotionEffects()) {
				  p.removePotionEffect(effect.getType());
        	
			  }
			  	ApplicableRegionSet ar = m.getWG().getRegionManager(p.getWorld()).getApplicableRegions(p.getLocation());
		        Iterator<ProtectedRegion> prs = ar.iterator();
		        
		        while (prs.hasNext()) {
		        	ProtectedRegion pr = prs.next();
		        	if (pr.getId().startsWith("pvp") == true) {
		        		
		        		m.pvp.add(p);
		        		
		        		p.sendMessage(""+ChatColor.GRAY + ChatColor.STRIKETHROUGH + "------------------------------------------");
		        		p.sendMessage(ChatColor.GREEN + "You died while in the pvp arena, You lost your items!");
		        		p.sendMessage(""+ChatColor.GRAY + ChatColor.STRIKETHROUGH + "------------------------------------------");   
		        		
		        		for(ItemStack items : p.getInventory().getContents()) {
							if(items != null) {
								Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), items);
							}
						}
		        		
		        		ItemStack helmet = p.getInventory().getHelmet();
		        		m.helmets.put(e.getEntity(), helmet);
						ItemStack chest = p.getInventory().getChestplate();

						m.chests.put(e.getEntity(), chest);
						ItemStack legging = p.getInventory().getLeggings();

						m.leggings.put(e.getEntity(), legging);
						ItemStack boot = p.getInventory().getBoots();

						m.boots.put(e.getEntity(), boot);
						
						if(m.helmets.get(p) != null) {
							Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), m.helmets.get(p));
						}
						
						if(m.chests.get(p) != null) {
							Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), m.chests.get(p));
						}
						

						
						if(m.leggings.get(p) != null) {
							Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), m.leggings.get(p));
						}
						

						
						if(m.boots.get(p) != null) {
							Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), m.boots.get(p));
						}
						
						m.helmets.remove(p);
						m.chests.remove(p);
						m.leggings.remove(p);
						m.boots.remove(p);
						
						p.getInventory().setHelmet(null);
						p.getInventory().setChestplate(null);
						p.getInventory().setLeggings(null);
						p.getInventory().setBoots(null);
						
						e.getDrops().clear();
						p.getInventory().clear();
						p.updateInventory();
						
						
						p.setExp(0);
		        		
		        		
		        	}
		        }
		        
		        
		        
		        
		        if(!m.pvp.contains(p)) {
		        	
		        	if(user.inGroup("default") ||
							(user.inGroup("donor") ||
									(user.inGroup("supporter")))) {
					  
						p.sendMessage("");
						p.sendMessage(ChatColor.GREEN + "You lost your items upon death, If you wish to keep your items next time purchase VIP or above from www.craftshark.net");
						p.sendMessage("");
						
						for(ItemStack items : p.getInventory().getContents()) {
							if(items != null) {
								Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), items);
							}
						}
						
						ItemStack helmet = p.getInventory().getHelmet();
						m.helmets.put(e.getEntity(), helmet);
						ItemStack chest = p.getInventory().getChestplate();

						m.chests.put(e.getEntity(), chest);
						ItemStack legging = p.getInventory().getLeggings();

						m.leggings.put(e.getEntity(), legging);
						ItemStack boot = p.getInventory().getBoots();

						m.boots.put(e.getEntity(), boot);
						
						if(m.helmets.get(p) != null) {
							Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), m.helmets.get(p));
						}
						
						if(m.chests.get(p) != null) {
							Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), m.chests.get(p));
						}
						

						
						if(m.leggings.get(p) != null) {
							Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), m.leggings.get(p));
						}
						

						
						if(m.boots.get(p) != null) {
							Bukkit.getWorld(p.getWorld().getName()).dropItem(p.getLocation(), m.boots.get(p));
						}
						
						m.helmets.remove(p);
						m.chests.remove(p);
						m.leggings.remove(p);
						m.boots.remove(p);
						
						p.getInventory().setHelmet(null);
						p.getInventory().setChestplate(null);
						p.getInventory().setLeggings(null);
						p.getInventory().setBoots(null);
						
						e.getDrops().clear();
						p.getInventory().clear();
						p.updateInventory();
						
						
						p.setExp(0);
					} else {
						
						m.getitems(p, e);
						
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(m, new Runnable() {
							@Override
							public void run() {
								m.setitems(p, e);
								p.sendMessage("");
								p.sendMessage(ChatColor.GREEN + "You kept your items because you are a VIP or above!"); 
								p.sendMessage("");
							}
					  }, 60);
						
					}
		        	
		        }
			  
			  if(p instanceof Player  && pkiller instanceof Player) {
				  
				  Random rand = new Random();
				  int randomNumber = rand.nextInt(200);
				  int i = Math.round(randomNumber);
				  
				  pkiller.sendMessage(m.onDeathPrefix + " " +  ChatColor.GOLD + 
						  "You got " + ChatColor.RED  + i + ChatColor.GOLD + " credits for killing " +  p.getName());
				  
				  String message = m.onDeathPrefix + " " +  user.getPrefix() + p.getName() + ChatColor.GOLD + " Has been slain by " + pkilleruser.getPrefix() + pkiller.getName();
				  e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message));
				  
				  m.scredits.setcredits(pkiller, p, i);
				  m.sscore.setscores(p, pkiller);
				  
				  m.killstreak.killstreak(pkiller, p);

			  }  else {
				  String message = m.onDeathPrefix + " " + user.getPrefix() + p.getName() + ChatColor.GOLD + " Has died!";
				  e.setDeathMessage(ChatColor.translateAlternateColorCodes('&', message));
			  }


		  
		  
	      m.addpro.addprotection(p);
		  m.s.spawn(p);
	  }

}
