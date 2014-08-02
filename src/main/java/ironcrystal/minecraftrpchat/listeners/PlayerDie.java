package ironcrystal.minecraftrpchat.listeners;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDie implements Listener {
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void playerDie(PlayerDeathEvent event) {
		String message = event.getDeathMessage();
		event.setDeathMessage("");
		if (message.contains("using")) {
			message = message.substring(0) + ChatColor.AQUA + message.substring(message.indexOf("using") + 6);
		}
		World world = event.getEntity().getWorld();
		for (Player p: world.getPlayers()) {
			p.sendMessage(message);
		}
	}
}
