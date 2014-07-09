package ironcrystal.minecraftrpchat.listeners;

import ironcrystal.minecraftrp.occupations.Occupations;
import ironcrystal.minecraftrp.player.OccupationalPlayer;
import ironcrystal.minecraftrpchat.occupations.Occupation;
import ironcrystal.minecraftrpchat.player.ChattablePlayer;
import ironcrystal.minecraftrpchat.staff.Staff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		event.setCancelled(true);
		String originalMessage = event.getMessage();

		ChattablePlayer player = new ChattablePlayer(event.getPlayer().getUniqueId());
		Staff staff = player.getStaff();
		OccupationalPlayer p = new OccupationalPlayer(event.getPlayer().getUniqueId());
		Occupations occup = p.getOccupation();

		String staffPrefix = staff.getPrefix();
		ChatColor staffColor = staff.getChatColor();

		ChatColor rankChatColor = player.getRank().getChatColor();		

		ChatColor occupColor = Occupation.getChatColor(occup);

		String message = "";

		/**
		 * Add Staff Prefix, if applicable
		 */
		if (!staffPrefix.equalsIgnoreCase("")) {
			message = message + staffColor + "[" + staffPrefix + "] " + ChatColor.WHITE;
		}

		/**
		 * Add Occupation
		 */
		message = message + occupColor + "[" + occup.toString() + "] " + ChatColor.WHITE;

		/**
		 * Add Donator Color to name
		 */
		message = message + rankChatColor;

		/**
		 * Add Name
		 */
		message = message + event.getPlayer().getName();

		/**
		 * Add colon after name
		 */
		message = message + ": " + ChatColor.WHITE;

		/**
		 * Search through message for color changes
		 */

		if (event.getPlayer().hasPermission("chat.colors")) {
			boolean isDone = false;
			while (!isDone) {
				if (originalMessage.contains("&1")) {
					int indextoSplit = originalMessage.indexOf("&1");
					String beginning = originalMessage.substring(0, indextoSplit);
					String ending = originalMessage.substring(indextoSplit +2);
					originalMessage = beginning + ChatColor.getByChar("1") + ending;
				}
				else if (originalMessage.contains("&2")) {
					int indextoSplit = originalMessage.indexOf("&2");
					String beginning = originalMessage.substring(0, indextoSplit);
					String ending = originalMessage.substring(indextoSplit +2);
					originalMessage = beginning + ChatColor.getByChar("2") + ending;
				}
				else if (originalMessage.contains("&3")) {
					int indextoSplit = originalMessage.indexOf("&3");
					String beginning = originalMessage.substring(0, indextoSplit);
					String ending = originalMessage.substring(indextoSplit +2);
					originalMessage = beginning + ChatColor.getByChar("3") + ending;
				}
				else if (originalMessage.contains("&4")) {
					int indextoSplit = originalMessage.indexOf("&4");
					String beginning = originalMessage.substring(0, indextoSplit);
					String ending = originalMessage.substring(indextoSplit +2);
					originalMessage = beginning + ChatColor.getByChar("4") + ending;
				}
				else if (originalMessage.contains("&5")) {
					int indextoSplit = originalMessage.indexOf("&5");
					String beginning = originalMessage.substring(0, indextoSplit);
					String ending = originalMessage.substring(indextoSplit +2);
					originalMessage = beginning + ChatColor.getByChar("5") + ending;
				}
				else if (originalMessage.contains("&6")) {
					int indextoSplit = originalMessage.indexOf("&6");
					String beginning = originalMessage.substring(0, indextoSplit);
					String ending = originalMessage.substring(indextoSplit +2);
					originalMessage = beginning + ChatColor.getByChar("6") + ending;
				}
				else if (originalMessage.contains("&7")) {
					int indextoSplit = originalMessage.indexOf("&7");
					String beginning = originalMessage.substring(0, indextoSplit);
					String ending = originalMessage.substring(indextoSplit +2);
					originalMessage = beginning + ChatColor.getByChar("7") + ending;
				}
				else if (originalMessage.contains("&7")) {
					int indextoSplit = originalMessage.indexOf("&7");
					String beginning = originalMessage.substring(0, indextoSplit);
					String ending = originalMessage.substring(indextoSplit +2);
					originalMessage = beginning + ChatColor.getByChar("7") + ending;
				}
				else if (originalMessage.contains("&8")) {
					int indextoSplit = originalMessage.indexOf("&8");
					String beginning = originalMessage.substring(0, indextoSplit);
					String ending = originalMessage.substring(indextoSplit +2);
					originalMessage = beginning + ChatColor.getByChar("8") + ending;
				}
				else if (originalMessage.contains("&9")) {
					int indextoSplit = originalMessage.indexOf("&9");
					String beginning = originalMessage.substring(0, indextoSplit);
					String ending = originalMessage.substring(indextoSplit +2);
					originalMessage = beginning + ChatColor.getByChar("9") + ending;
				}
				else if (originalMessage.contains("&a")) {
					int indextoSplit = originalMessage.indexOf("&a");
					String beginning = originalMessage.substring(0, indextoSplit);
					String ending = originalMessage.substring(indextoSplit +2);
					originalMessage = beginning + ChatColor.getByChar("a") + ending;
				}
				else if (originalMessage.contains("&b")) {
					int indextoSplit = originalMessage.indexOf("&b");
					String beginning = originalMessage.substring(0, indextoSplit);
					String ending = originalMessage.substring(indextoSplit +2);
					originalMessage = beginning + ChatColor.getByChar("b") + ending;
				}
				else if (originalMessage.contains("&c")) {
					int indextoSplit = originalMessage.indexOf("&c");
					String beginning = originalMessage.substring(0, indextoSplit);
					String ending = originalMessage.substring(indextoSplit +2);
					originalMessage = beginning + ChatColor.getByChar("c") + ending;
				}
				else if (originalMessage.contains("&d")) {
					int indextoSplit = originalMessage.indexOf("&d");
					String beginning = originalMessage.substring(0, indextoSplit);
					String ending = originalMessage.substring(indextoSplit +2);
					originalMessage = beginning + ChatColor.getByChar("d") + ending;
				}
				else if (originalMessage.contains("&e")) {
					int indextoSplit = originalMessage.indexOf("&e");
					String beginning = originalMessage.substring(0, indextoSplit);
					String ending = originalMessage.substring(indextoSplit +2);
					originalMessage = beginning + ChatColor.getByChar("e") + ending;
				}
				else if (originalMessage.contains("&f")) {
					int indextoSplit = originalMessage.indexOf("&f");
					String beginning = originalMessage.substring(0, indextoSplit);
					String ending = originalMessage.substring(indextoSplit +2);
					originalMessage = beginning + ChatColor.getByChar("f") + ending;
				}else{
					isDone = true;
				}
			}
		}

		/**
		 * Add Message
		 */
		message = message + originalMessage;

		for (Player players : Bukkit.getOnlinePlayers()) {
			players.sendMessage(message);
		}
	}
}
