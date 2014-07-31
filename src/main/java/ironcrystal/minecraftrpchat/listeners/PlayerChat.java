package ironcrystal.minecraftrpchat.listeners;

import ironcrystal.minecraftrp.Files;
import ironcrystal.minecraftrp.occupations.Occupations;
import ironcrystal.minecraftrp.player.Mayor;
import ironcrystal.minecraftrp.player.OccupationalPlayer;
import ironcrystal.minecraftrp.town.Town;
import ironcrystal.minecraftrp.town.TownManager;
import ironcrystal.minecraftrpchat.MinecraftRPChat;
import ironcrystal.minecraftrpchat.chat.ChatChannel;
import ironcrystal.minecraftrpchat.chat.ChatChannelManager;
import ironcrystal.minecraftrpchat.file.FileManager;
import ironcrystal.minecraftrpchat.occupations.Occupation;
import ironcrystal.minecraftrpchat.player.ChattablePlayer;
import ironcrystal.minecraftrpchat.staff.Staff;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {
	private MinecraftRPChat plugin;

	public PlayerChat(MinecraftRPChat plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		if (event.isAsynchronous()) {
			String originalMessage = event.getMessage();
			ChattablePlayer player = new ChattablePlayer(event.getPlayer().getUniqueId());
			UUID uuid = player.getUUID();
			Staff staff = player.getStaff();
			OccupationalPlayer p = new OccupationalPlayer(event.getPlayer().getUniqueId());
			Occupations occup = p.getOccupation();
			FileConfiguration config = new YamlConfiguration();
			FileManager.loadFile(FileManager.Config, config);

			String staffPrefix = staff.getPrefix();
			ChatColor staffColor = staff.getChatColor();

			String channelPrefix = ChatChannelManager.getTalkingChannel(uuid);
			ChatColor channelColor = ChatColor.getByChar(config.getString("Channel Prefix Color"));

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
			 * Add Channel Prefix
			 */
			message = message + channelColor + "[" + channelPrefix.substring(0,1).toUpperCase() + channelPrefix.substring(1) + "] " + ChatColor.WHITE;

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
				String chars = "abcdef123456789";
				while (!isDone) {
					if (originalMessage.contains("&") && chars.contains(originalMessage.charAt(originalMessage.indexOf('&') + 1) + "")) {
						char character = originalMessage.charAt(originalMessage.indexOf('&') + 1);
						int indexToSplit = originalMessage.indexOf('&');
						String beginning = originalMessage.substring(0, indexToSplit);
						String ending = originalMessage.substring(indexToSplit + 2);
						originalMessage = beginning + ChatColor.getByChar(character) + ending;
					}else{
						isDone = true;
					}
				}
			}

			/**
			 * Add Message
			 */
			message = message + originalMessage;

			/**
			 * Get Recipients
			 */
			List<UUID> recipients = new ArrayList<UUID>();
			ChatChannel channel = ChatChannelManager.getTalkingChatChannel(uuid);
			if (channel == null) {
				if (ChatChannelManager.isTalkingInLocalChat(uuid)) {
					/**
					 * Get Local Players
					 */
					//recipients.add(uuid);
					World world = event.getPlayer().getLocation().getWorld();
					Location senderLoc = event.getPlayer().getLocation();
					FileConfiguration fileConfig = new YamlConfiguration();
					FileManager.loadFile(FileManager.Config, fileConfig);
					for (Player name : world.getPlayers()) {
						Location nearbyPlayerLoc = name.getLocation();
						if (senderLoc.distance(nearbyPlayerLoc) < fileConfig.getDouble("Local Distance")) {
							recipients.add(name.getUniqueId());
						}
					}
				}
				else if (ChatChannelManager.isTalkingInTownChat(uuid)) {
					/**
					 * Get Town Players
					 */
					File pFile = Files.getPlayerFile(uuid);
					FileConfiguration fileConfig = new YamlConfiguration();
					FileManager.loadFile(pFile, fileConfig);
					String townName = fileConfig.getString("Resident of");
					if (TownManager.getTown(new Mayor(uuid)) != null) {
						Town town = TownManager.getTown(new Mayor(uuid));
						for (String str : town.getResidents()) {
							UUID residentUUID = UUID.fromString(str);
							recipients.add(residentUUID);
						}
						if (!recipients.contains(uuid)) {
							recipients.add(uuid);
						}
					}else{
						if (townName != null && !townName.equalsIgnoreCase("") && !townName.equalsIgnoreCase("null")) {
							Town town = TownManager.getTown(townName);
							for (String str : town.getResidents()) {
								UUID residentUUID = UUID.fromString(str);
								recipients.add(residentUUID);
							}
							if (!recipients.contains(town.getMayor().getUUID())) {
								recipients.add(town.getMayor().getUUID());
							}					
						}else{
							event.getPlayer().sendMessage(ChatColor.RED + "[MinecraftRPChat] You aren't in a town!");
						}
					}
				}
				else if (ChatChannelManager.isTalkingInGlobalChat(uuid)) {
					for (Player players : Bukkit.getOnlinePlayers()) {
						if (!recipients.contains(players.getUniqueId())) {
							recipients.add(players.getUniqueId());
						}
					}
				}
			}else{
				Bukkit.getConsoleSender().sendMessage("The size of participants is " + channel.getParticipants().size());
				for (String str : channel.getParticipants()) {
					UUID pUUID = UUID.fromString(str);
					recipients.add(pUUID);
				}
			}

			/**
			 * Add All admins to chat
			 */
			for (Player players : Bukkit.getOnlinePlayers()) {
				if (players.hasPermission("staff.admin") || players.hasPermission("staff.owner")) {
					if (!recipients.contains(players.getUniqueId())) {
						recipients.add(players.getUniqueId());
					}
				}
			}
			event.setFormat(message);
			event.getRecipients().clear();
			for (UUID players : recipients) {
				if (Bukkit.getPlayer(players) != null) {
					Player pl = Bukkit.getPlayer(players);
					event.getRecipients().add(pl);
				}
			}			
			//			AsyncPlayerChatEvent newEvent = new AsyncPlayerChatEvent(false, event.getPlayer(), message, playerSet);
			//			plugin.getServer().getPluginManager().callEvent(newEvent);
			/**for (UUID players : recipients) {
				Player onlinePlayer = null;
				try {
					onlinePlayer = Bukkit.getPlayer(players);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				if (onlinePlayer != null) {
					onlinePlayer.sendMessage(message);
				}
			}
			 **/
		}
	}
}
