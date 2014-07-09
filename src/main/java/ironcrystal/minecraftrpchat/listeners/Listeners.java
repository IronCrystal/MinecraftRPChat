package ironcrystal.minecraftrpchat.listeners;

import ironcrystal.minecraftrpchat.MinecraftRPChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Listeners {
	private static PlayerChat playerChat;
	
	private static void initializeListeners(MinecraftRPChat main) {
		playerChat = new PlayerChat();

	}
	
	public static void registerEvents(MinecraftRPChat main) {
		Listeners.initializeListeners(main);
		Bukkit.getServer().getPluginManager().registerEvents(playerChat, main);
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[MinecraftRPChat] Listeners Registered");
	}

}
