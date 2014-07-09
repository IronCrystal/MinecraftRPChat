package ironcrystal.minecraftrpchat;

import ironcrystal.minecraftrpchat.file.Files;
import ironcrystal.minecraftrpchat.listeners.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class MinecraftRPChat extends JavaPlugin {
	
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[MinecraftRPChat] Loaded!");
		Listeners.registerEvents(this);
		Files.initializeFiles();
	}
	
	@Override
	public void onDisable() {
		
	}

}
