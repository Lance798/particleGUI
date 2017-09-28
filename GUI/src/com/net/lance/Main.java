package net.lance.com;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;




public class Main extends JavaPlugin implements Listener{
	public void onEnable() {
		this.getConfig().options().copyDefaults(true);
	    saveConfig();
	    reloadConfig();
		System.out.println("[MoTyCraft] 已開啟");
		System.out.println("[MoTyCraft] 目前版本:" + this.getConfig());
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	public void onDisable() {
		
	}
	ArrayList<String> joinlist = new ArrayList<String>();
	
	@Override
	public boolean onCommand(CommandSender sender,Command cmd,String lable,String args[]) {
		Player player = (Player)sender;
		if(cmd.getName().equalsIgnoreCase("mtc") && player.hasPermission("mtc.usecommand")) {
			String a = args[0];
			
			switch(a) {
			case "help":{
				player.sendMessage(ChatColor.YELLOW + "MoTy" + ChatColor.GOLD + "Craft" + ChatColor.YELLOW + "指令列表:");
				player.sendMessage(ChatColor.YELLOW + "/mtc help 顯示指令。");
				player.sendMessage(ChatColor.YELLOW + "/mtc setmotd 設定當玩家登入時的訊息。");
				player.sendMessage(ChatColor.YELLOW + "/mtc version 顯示目前版本。");
				return true;
			}
			case "setmotd":{
				if(args.length == 0) {
			        player.sendMessage(ChatColor.RED + "請設定motd文字!");
			    }
			        String motd = "";
			        for(int i=0;i<args.length;i++) {
			            motd += args[i] + " ";
			        }
			        getConfig().set("PlayerJoinMessenge" , motd);
			        saveConfig();
			        player.sendMessage(ChatColor.GREEN + "成功設置MOTD!");
			        return true;
			}		
			case "":{
				player.sendMessage(ChatColor.YELLOW + "MoTy" + ChatColor.GOLD + "Craft" + ChatColor.YELLOW + "指令列表:");
				player.sendMessage(ChatColor.YELLOW + "/mtc help 顯示指令。");
				player.sendMessage(ChatColor.YELLOW + "/mtc setmotd 設定當玩家登入時的訊息。");
				player.sendMessage(ChatColor.YELLOW + "/mtc version 顯示目前版本。");
				return true;
			}
			}
			} else {
			player.sendMessage(ChatColor.RED + "你沒有權限!");
		}
		return true;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		joinlist.add(player.getName());
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		
	}
}

