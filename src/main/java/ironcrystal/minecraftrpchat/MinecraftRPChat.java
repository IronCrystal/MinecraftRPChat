package ironcrystal.minecraftrpchat;

import java.io.File;

import ironcrystal.minecraftrpchat.command.Commands;
import ironcrystal.minecraftrpchat.file.FileManager;
import ironcrystal.minecraftrpchat.listeners.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MinecraftRPChat extends JavaPlugin {
	
	@Override
	public void onEnable() {
		Listeners.registerEvents(this);
		FileManager.initializeFiles();
		
		/**
		 * Commands
		 */
		Commands commands = new Commands();
		getCommand("chat").setExecutor(commands);
		
		/**
		 * If Players are already online, make files if they don't exist
		 */
		for (Player p : Bukkit.getOnlinePlayers()) {
			File file = new File("plugins/MinecraftRPChat/player/" + p.getUniqueId().toString() + ".yml");
			if (!file.exists()) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[MinecraftRP] New Player Found! Creating player file...");
				FileManager.createPlayerFile(p.getUniqueId());
				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[MinecraftRP] " + p.getName() + " Player File Created!");
			}
		}
	}
	
	@Override
	public void onDisable() {
		
	}

}
