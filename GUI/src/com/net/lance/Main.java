package com.net.lance;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;



public class Main extends JavaPlugin implements Listener {
	ArrayList<String> playerlist = new ArrayList<String>();
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		
		
	}
	@Override
	public void onDisable() {
		
	}
	 static ItemStack blaze = new ItemStack(Material.BLAZE_POWDER);
	 static ItemStack water = new ItemStack(Material.WATER_BUCKET);
	 static ItemStack clear = new ItemStack(Material.BUCKET);
	 
	public static Inventory particle = Bukkit.createInventory(null , 9 , ChatColor.BLACK + "粒子效果清單");

		         static {
		   
		        	 blaze.getItemMeta().setDisplayName(ChatColor.GOLD + "烈焰");
		        	 water.getItemMeta().setDisplayName(ChatColor.BLUE + "水滴");
		        	 clear.getItemMeta().setDisplayName(ChatColor.RED + "清除粒子效果");
		        	 particle.setItem(0, blaze);
		        	 particle.setItem(1, water);
		        	 particle.setItem(8, clear);
		        	 
		        	 
		         }
	@Override
	public boolean onCommand(CommandSender sender,Command cmd,String lable,String args[]) {
		Player player = (Player)sender;
		if(cmd.getName().equalsIgnoreCase("menu")) {
			player.openInventory(particle);
		}
		return true;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
	    Inventory inventory = event.getInventory();
	    if(inventory.getName().equals(particle.getName())){
	        
	            event.setCancelled(true);
	            if(event.getCurrentItem().equals(blaze)){
	            	if (!(playerlist.contains(player.getName()))) {
	            		player.closeInventory();
	            		playerlist.add(player.getName());
	            		
	            	
	            		new BukkitRunnable(){
	            		
						@Override
	                    public void run() {
							
							player.getWorld().spawnParticle(Particle.LAVA, player.getLocation(), 50);
							if (!(playerlist.contains(player.getName()))) {
								cancel();
							}
	                    }
	                }.runTaskTimer(this, 20, 10);
	            	} else {
	            		event.setCancelled(true);
	            		player.sendMessage(ChatColor.RED + "你已擁有另一個粒子特效了，若要更換請先清除粒子效果。");
	            	}
	                if(event.getCurrentItem().equals(clear)){
	                	player.closeInventory();
	                	player.sendMessage(ChatColor.RED + "已清除粒子效果!");
	                	playerlist.remove(player.getName());
	                }
	            	}
	            	if(event.getCurrentItem().equals(water)) {
	            		player.closeInventory();
						if (!(playerlist.contains(player.getName()))) {
							playerlist.add(player.getName());
							
	            		new BukkitRunnable(){
	            			
							@Override
		                    public void run() {
								
								player.getWorld().spawnParticle(Particle.DRIP_WATER, player.getLocation(), 100);
								if (!(playerlist.contains(player.getName()))) {
									cancel();
								}
								
		                    }
		                }.runTaskTimer(this, 20, 10);
	            		} else {
	            			event.setCancelled(true);
	            			player.sendMessage(ChatColor.RED + "你已擁有另一個粒子特效了，若要更換請先清除粒子效果。");
	            		}
	            		}
	    }
	}
	
}



