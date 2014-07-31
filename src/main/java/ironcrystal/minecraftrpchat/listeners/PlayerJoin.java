package ironcrystal.minecraftrpchat.listeners;


import ironcrystal.minecraftrpchat.file.FileManager;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		UUID uuid = p.getUniqueId();
		File file = FileManager.getPlayerFile(uuid);
		if (!file.exists()) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[MinecraftRP] New Player Joined! Creating player file...");
			FileManager.createPlayerFile(uuid);
			Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[MinecraftRP] " + p.getName() + " Player File Created!");
		}
	}

}
