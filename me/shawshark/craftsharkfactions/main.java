package me.shawshark.craftsharkfactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.shawshark.craftsharkfactions.Commands.DonateCommand;
import me.shawshark.craftsharkfactions.Commands.FeedCommand;
import me.shawshark.craftsharkfactions.Commands.HealCommand;
import me.shawshark.craftsharkfactions.Commands.SetSpawnCommand;
import me.shawshark.craftsharkfactions.Commands.SethomeCommand;
import me.shawshark.craftsharkfactions.Commands.SpawnCommand;
import me.shawshark.craftsharkfactions.Commands.StatsCommand;
import me.shawshark.craftsharkfactions.Commands.VoteCommand;
import me.shawshark.craftsharkfactions.Commands.WarpCommand;
import me.shawshark.craftsharkfactions.Events.EntityDamage;
import me.shawshark.craftsharkfactions.Events.PlayerDeath;
import me.shawshark.craftsharkfactions.Events.SignPlace;
import me.shawshark.craftsharkfactions.Methods.AddProtection;
import me.shawshark.craftsharkfactions.Methods.Home;
import me.shawshark.craftsharkfactions.Methods.KillStreak;
import me.shawshark.craftsharkfactions.Methods.RandomTeleport;
import me.shawshark.craftsharkfactions.Methods.SetCredits;
import me.shawshark.craftsharkfactions.Methods.SetScore;
import me.shawshark.craftsharkfactions.Methods.Spawn;
import me.shawshark.craftsharkfactions.Methods.UpdateScoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class main extends JavaPlugin implements Listener {
	
	public List<Player> spawnprotection = new ArrayList<Player>();
	public final List<Player> teleport = new ArrayList<Player>();
	public final List<Player> heal = new ArrayList<Player>();
	public final List<Player> feed = new ArrayList<Player>();
	public final List<Player> homelist = new ArrayList<Player>();
	public List<Player> randomportal = new ArrayList<Player>();
	public List<Player> spawn_list = new ArrayList<Player>();
	public List<Player> tpa_player = new ArrayList<Player>();
	public List<Player> pvp = new ArrayList<Player>();
	public final List<Player> warp = new ArrayList<Player>();
	
    public WorldGuardPlugin getWG() {
        return wg;
    }
    
    public chat chat = new chat(this);
    public me.shawshark.craftsharkfactions.tasks.spawn spawn = new me.shawshark.craftsharkfactions.tasks.spawn(this);
    
    
    public Boolean alltohub = true;
    public HashMap<Player, Player> tpa = new HashMap<Player, Player>();
	private Map<Player, ItemStack[]> pArmor = new HashMap<Player, ItemStack[]>();
	public HashMap<Player, ItemStack[]> items = new HashMap<Player, ItemStack[]>();
	public HashMap<Player, ItemStack> helmets = new HashMap<Player, ItemStack>();
	public HashMap<Player, ItemStack> chests = new HashMap<Player, ItemStack>();
	public HashMap<Player, ItemStack> leggings = new HashMap<Player, ItemStack>();
	public HashMap<Player, ItemStack> boots = new HashMap<Player, ItemStack>();
	public WorldGuardPlugin wg;
	public String prefix = ChatColor.YELLOW + "[" + ChatColor.RED + "CraftShark" + ChatColor.YELLOW + "]:";
	public String onDeathPrefix = ChatColor.GRAY + "[" + ChatColor.RED + "Death" + ChatColor.GRAY + "]";
	
	public Home home = new Home(this);
	public Spawn s = new Spawn(this);
	public RandomTeleport randomtp = new RandomTeleport(this);
	public KillStreak killstreak = new KillStreak(this);
	public UpdateScoreboard usb = new UpdateScoreboard(this);
	public SetScore sscore = new SetScore(this);
	public SetCredits scredits = new SetCredits(this);
	public AddProtection addpro = new AddProtection(this);
	
	public void onEnable() {
		alltohub = true;
		wg = (WorldGuardPlugin) getServer().getPluginManager().getPlugin("WorldGuard");
		
		saveDefaultConfig();
		
		RegisyerEvents();
		RegisterCommands();
	}
	
	public void onDisable() {
		if(alltohub) {
			for(Player online : getServer().getOnlinePlayers()) {
				online.performCommand("server hub");
			}
		}

	}
	
	public void RegisyerEvents() {
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new EntityDamage(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDeath(this), this);
		Bukkit.getPluginManager().registerEvents(new SignPlace(this), this);
		Bukkit.getPluginManager().registerEvents(new chat(this), this);
		System.out.println("[Craftshark Factions] Registered Events!");
	}
	
	public void RegisterCommands() {
		getCommand("warp").setExecutor(new WarpCommand(this));
		getCommand("setwarp").setExecutor(new WarpCommand(this));
		getCommand("heal").setExecutor(new HealCommand(this));
		getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
		getCommand("spawn").setExecutor(new SpawnCommand(this));
		getCommand("vote").setExecutor(new VoteCommand(this));
		getCommand("feed").setExecutor(new FeedCommand(this));
		getCommand("stats").setExecutor(new StatsCommand(this));
		getCommand("sethome").setExecutor(new SethomeCommand(this));
		getCommand("donate").setExecutor(new DonateCommand(this));
		System.out.println("[Craftshark Factions] Registered Commands!");
	}

 
   /* - To do, Move rest of commands to there own class. */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		final Player p = (Player)sender;
		
		if(command.getName().equalsIgnoreCase("motd")) {
			sendmotd(p);
		} else if(command.getName().equalsIgnoreCase("bal") || (command.getName().equalsIgnoreCase("balance"))) {
			p.performCommand("money");
		} else if(command.getName().equalsIgnoreCase("restartserver")) {
			if(p.isOp()) {
				getServer().broadcastMessage(ChatColor.GOLD + "Server restarting, Returning all players to the hub!");
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					@Override
					public void run() {
						for(Player online : getServer().getOnlinePlayers()) {
							online.performCommand("server hub");
						}
					}
				}, 40);
			
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
					@Override
					public void run() {
						getServer().shutdown();
					}
				}, 100);
		} else {
			p.sendMessage(ChatColor.RED + "Error: You don't have permissiosn!");
		}
	} else if(command.getName().equalsIgnoreCase("alltohub")) {
		if(p.isOp()) {
			if(alltohub)  {
				alltohub = false;
				p.sendMessage(ChatColor.GREEN  + "You have toggle all to hub: off, When reloading players will not get redirected!");
			} else {
				alltohub = true;
				p.sendMessage(ChatColor.GREEN  + "You have toggle all to hub: on, When reloading players will get redirected!");
			}
		}
	} else if(command.getName().equalsIgnoreCase("setworldspawn")) {
		if(p.isOp()) {
			Location l = p.getLocation();
			getServer().getWorld("world").setSpawnLocation(l.getBlockX(), l.getBlockY(), l.getBlockZ());
			Location loc = getServer().getWorld("world").getSpawnLocation();
			p.teleport(loc);
			
			
		} else {
			p.sendMessage(ChatColor.RED + "You don't have permissions for this command!");
		}
	}
		
		return true;
	}
 
	public void healcooldown(final Player p) {
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {
				if(p.isOnline()) {
					heal.remove(p);
				}
			}
		}, 5000);
	}


	public void feedcooldown(final Player p) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {
				if(p.isOnline()) {
					feed.remove(p);
				}
			}
	 	}, 5000);
	}
  
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		final Player p = (Player)e.getPlayer();
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {
				s.spawn(p);
			}
		}, 10);
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void moveEvent(PlayerMoveEvent e) {
		Player p = e.getPlayer();
	    
		String canceled = ChatColor.GREEN + "You moved, Teleportation canceled!";
	    
		if(spawn_list.contains(p)) {
			spawn_list.remove(p);
	    	p.sendMessage(canceled);
	    }
	    
	    if(homelist.contains(p))  {
	    	homelist.remove(p);
	    	p.sendMessage(canceled);
	    }
	    
	    
	    if(warp.contains(p)) {
	    	warp.remove(p);
	    	p.sendMessage(canceled);
	    }
	    
	    if(tpa_player.contains(p)) {
	    	tpa_player.remove(p);
	    	p.sendMessage(canceled);
	    }
	    
	    ApplicableRegionSet ar = getWG().getRegionManager(p.getWorld()).getApplicableRegions(p.getLocation());
        Iterator<ProtectedRegion> prs = ar.iterator();
        
        while (prs.hasNext()) {
        	ProtectedRegion pr = prs.next();
        	if (pr.getId().startsWith("randomport") == true) {
        		if(!randomportal.contains(p)) {
        			randomportal.add(p);
                    p.setAllowFlight(true);
                    teleportremove(p);
                    randomtp.randomteleport(p);
                 }      
        	}
        	
        	if(pr.getId().startsWith("pvp") == true) {
        		if(!pvp.contains(p)) {
        			pvp.add(p);
            		p.sendMessage(""+ChatColor.GRAY + ChatColor.STRIKETHROUGH + "------------------------------------------");
            		
            		p.sendMessage(ChatColor.GOLD + "You have entered the pvp arena!");
            		p.sendMessage(ChatColor.GREEN + "If you are a VIP or above you can loss your items upon death in here!");
            		
            		p.sendMessage(""+ChatColor.GRAY + ChatColor.STRIKETHROUGH + "------------------------------------------");
            		
        		}

        	}
        }
	}
	
	public void warp(final String warpname, final Player p) {
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {
				if(p.isOnline()) {
					if(warp.contains(p)) {
						Location loc = p.getLocation();
						loc.setX(getConfig().getInt("server.warps."+warpname + ".x"));
						loc.setY(getConfig().getInt("server.warps."+warpname + ".y"));
						loc.setZ(getConfig().getInt("server.warps."+warpname + ".z"));
						loc.setYaw(getConfig().getInt("server.warps."+warpname + ".yaw"));
						loc.setPitch(getConfig().getInt("server.warps."+warpname + ".pitch"));
						p.teleport(loc);
						warp.remove(p);
						p.sendMessage(ChatColor.RED + "You have teleported to: " + warpname);	
					}
				}	
			}
		  }, 80);
	}
	
	@EventHandler
	public void onTeleport(PlayerTeleportEvent e){
		Player p =e.getPlayer();
		if(pvp.contains(p)) {
			pvp.remove(p);
		}
		
		addpro.addprotection(p);
		
		
	}
	
	public void teleportremove(final Player p) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {
				if(p.isOnline()) {
					randomportal.remove(p);	
				}
			}
		}, 350);
	}
		
	@EventHandler (priority=EventPriority.HIGHEST)
	public void fallevent(EntityDamageEvent e) {
		if ((e.getEntity() instanceof Player)) {
	    Player p = (Player)e.getEntity();
	    	if ((p.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) 
	    		  && (e.getCause() == EntityDamageEvent.DamageCause.FALL) 
	    		  	&& (teleport.contains(p))) {
	      	e.setCancelled(true);
	      } 
	    }
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void leaveevnt(PlayerQuitEvent e) {
	Player p = e.getPlayer();
	  	
		usb.updatescoreboardforeveryone();
	  	
		if (teleport.contains(p)) {
			teleport.remove(p);
			p.setAllowFlight(false);
		}
		e.setQuitMessage(null);
	}
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void kickevent(PlayerKickEvent e) {
	Player p = e.getPlayer();
	  	
	usb.updatescoreboardforeveryone();
	  	
		if (teleport.contains(p)) {
			teleport.remove(p);
			p.setAllowFlight(false);
		}
		e.setLeaveMessage(null);
	}
	  

		
	@SuppressWarnings("deprecation")
	public void givestarter(Player p) {
		ItemStack item1 = new ItemStack(Material.IRON_HELMET,1);
		ItemStack item2 = new ItemStack(Material.IRON_CHESTPLATE,1);
		ItemStack item3 = new ItemStack(Material.IRON_LEGGINGS,1);
		ItemStack item4 = new ItemStack(Material.IRON_BOOTS,1);
			
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD,1);
			
		ItemStack item5 = new ItemStack(Material.COOKED_BEEF, 10);
		ItemStack item6 = new ItemStack(Material.GOLDEN_APPLE, 2);
			
			
			
		p.getInventory().addItem(item1);
		p.getInventory().addItem(item2);
		p.getInventory().addItem(item3);
		p.getInventory().addItem(item4);
			
		p.getInventory().addItem(sword);
			
		p.getInventory().addItem(item5);
		p.getInventory().addItem(item6);
			
		p.updateInventory();
			
	}
		
	public void setdefaultconfigvalues(Player p) {
			
		getConfig().set("kills.players.player." + p.getName(), 0);
		getConfig().set("deaths.players.player." + p.getName(), 0);
		getConfig().set("credits.players.player." + p.getName(), 0);
		saveConfig();
			   
	}
		
	public void sendmotd( Player p ) {
			
		p.sendMessage(ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "--------------------------------------------");
		p.sendMessage(ChatColor.GREEN + "             Welcome back " + ChatColor.LIGHT_PURPLE + p.getName());
		p.sendMessage(ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "--------------------------------------------");
		p.sendMessage(ChatColor.RED + "Hello, Craftshark Factions is still under alot of work!");
		p.sendMessage(ChatColor.DARK_GREEN + "- " + ChatColor.GOLD + "Learn how to vote by typing /vote");
		p.sendMessage(ChatColor.DARK_GREEN + "- " + ChatColor.GOLD + "Need more information on some commands, /warp info");
		p.sendMessage(ChatColor.DARK_GREEN + "- " + ChatColor.GOLD + "Learn how to donate by typing /donate");
		p.sendMessage(ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.BOLD + "--------------------------------------------");
			
	}
		
	public void getitems(Player p, PlayerDeathEvent e) {
		
		ItemStack[] content = e.getEntity().getInventory().getContents();
		ItemStack helmet = p.getInventory().getHelmet();
		helmets.put(e.getEntity(), helmet);
		ItemStack chest = p.getInventory().getChestplate();

		chests.put(e.getEntity(), chest);
		ItemStack legging = p.getInventory().getLeggings();

		leggings.put(e.getEntity(), legging);
		ItemStack boot = p.getInventory().getBoots();
		boots.put(e.getEntity(), boot);
		items.put(e.getEntity(), content);
		pArmor.put(e.getEntity(), e.getEntity().getInventory().getArmorContents());

		e.getDrops().clear();
		p.getInventory().clear();
					
		p.getInventory().setArmorContents(null);	
			
	}
	  
	  
	  
	public void setitems(Player p, PlayerDeathEvent e) {
		 if (this.items.containsKey(e.getEntity())) {
		    e.getEntity().getInventory().clear();
		     for (ItemStack stack : (ItemStack[])this.items.get(e.getEntity())) {
		       if (stack != null) {
		        e.getEntity().getInventory().addItem(new ItemStack[] { stack });
		      }

		      this.items.remove(e.getEntity());
		     }
		  }
		  e.getEntity().getInventory().setArmorContents((ItemStack[])this.pArmor.get(e.getEntity()));
		  this.pArmor.remove(e.getEntity());
	}
	  
	@EventHandler
	public void pvpevent(EntityDamageByEntityEvent e) {
		if((e.getEntity() instanceof Player) && e.getDamager() instanceof Player) {
			Player p = (Player)e.getEntity();
			Player killer = (Player)e.getDamager();
			if(spawnprotection.contains(p) || spawnprotection.contains(killer)) {
				killer.sendMessage(ChatColor.GOLD + " This player is in a non-pvp state for " + ChatColor.GREEN + "20 seconds!");
				e.setCancelled(true);
			} else {
				e.setCancelled(false);
			}
		}
	}	
}
