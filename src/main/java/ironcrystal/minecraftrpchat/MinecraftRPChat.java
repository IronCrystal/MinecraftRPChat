package ironcrystal.minecraftrpchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class MinecraftRPChat extends JavaPlugin {
	
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[MinecraftRPChat] Loaded!");
	}
	
	@Override
	public void onDisable() {
		
	}

}
