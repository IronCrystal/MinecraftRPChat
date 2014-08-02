package ironcrystal.minecraftrpchat.listeners;

import ironcrystal.minecraftrpchat.MinecraftRPChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Listeners {
	private static PlayerChat playerChat;
	private static PlayerJoin playerJoin;
	private static PlayerDie playerDie;
	
	private static void initializeListeners(MinecraftRPChat main) {
		playerChat = new PlayerChat();
		playerJoin = new PlayerJoin();
		playerDie = new PlayerDie();

	}
	
	public static void registerEvents(MinecraftRPChat main) {
		Listeners.initializeListeners(main);
		Bukkit.getServer().getPluginManager().registerEvents(playerChat, main);
		Bukkit.getServer().getPluginManager().registerEvents(playerJoin, main);
		Bukkit.getServer().getPluginManager().registerEvents(playerDie, main);
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[MinecraftRPChat] Listeners Registered");
	}

}
