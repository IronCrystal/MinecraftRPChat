package ironcrystal.minecraftrpchat.chat;

import ironcrystal.minecraftrpchat.file.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ChatChannelManager {

	public static List<ChatChannel> getListeningChatChannels(UUID uuid) {
		File pFile = FileManager.getPlayerFile(uuid);
		FileConfiguration fileConfig = new YamlConfiguration();
		FileManager.loadFile(pFile, fileConfig);
		List<String> channels = fileConfig.getStringList("Listening Chat Channels");
		List<ChatChannel> list = new ArrayList<ChatChannel>();
		for (String str : channels) {
			String path = "plugins/MinecraftRPChat/chat/";
			File file = new File(path + str + ".yml");
			if (file.exists()) {
				ChatChannel channel = new ChatChannel(file);
				list.add(channel);
			}
		}
		return list;
	}

	public static ChatChannel getTalkingChatChannel(UUID uuid) {
		File pFile = FileManager.getPlayerFile(uuid);
		FileConfiguration fileConfig = new YamlConfiguration();
		FileManager.loadFile(pFile, fileConfig);
		String channel = fileConfig.getString("Talking Chat Channel");
		String path;
		switch(channel) {
			case "trade": path = "plugins/MinecraftRPChat/channel/trade.yml"; break;
			case "staff": path = "plugins/MinecraftRPChat/channel/staff.yml"; break;
		default: return null;
		}
		File file = new File(path);
		if (file.exists()) {
			return new ChatChannel(file);
		}
		return null;
	}

	public static String getTalkingChannel(UUID uuid) {
		File file = FileManager.getPlayerFile(uuid);
		FileConfiguration fileConfig = new YamlConfiguration();
		FileManager.loadFile(file, fileConfig);
		return fileConfig.getString("Talking Chat Channel");
	}

	public static boolean isListeningToChat(UUID uuid, String channel) {
		File file = FileManager.getPlayerFile(uuid);
		FileConfiguration fileConfig = new YamlConfiguration();
		FileManager.loadFile(file, fileConfig);
		if (fileConfig.getStringList("Listening Chat Channels").contains(channel)) {
			return true;
		}
		return false;
	}

	public static boolean isTalkingInLocalChat(UUID uuid) {
		File file = FileManager.getPlayerFile(uuid);
		FileConfiguration fileConfig = new YamlConfiguration();
		FileManager.loadFile(file, fileConfig);
		if (fileConfig.getString("Talking Chat Channel").equalsIgnoreCase("Local")) {
			return true;
		}
		return false;
	}
	
	public static boolean isTalkingInGlobalChat(UUID uuid) {
		File file = FileManager.getPlayerFile(uuid);
		FileConfiguration fileConfig = new YamlConfiguration();
		FileManager.loadFile(file, fileConfig);
		if (fileConfig.getString("Talking Chat Channel").equalsIgnoreCase("Global")) {
			return true;
		}
		return false;
	}

	public static boolean isTalkingInTownChat(UUID uuid) {
		File file = FileManager.getPlayerFile(uuid);
		FileConfiguration fileConfig = new YamlConfiguration();
		FileManager.loadFile(file, fileConfig);
		if (fileConfig.getString("Talking Chat Channel").equalsIgnoreCase("Town")) {
			return true;
		}
		return false;
	}

	public static void setTalkingChannel(UUID uuid, String channel) {
		File file = FileManager.getPlayerFile(uuid);
		FileConfiguration fileConfig = new YamlConfiguration();
		FileManager.loadFile(file, fileConfig);
		fileConfig.set("Talking Chat Channel", channel);
		FileManager.saveFile(file, fileConfig);
	}

	public static void addListeningChannel(UUID uuid, String channel) {
		File file = FileManager.getPlayerFile(uuid);
		FileConfiguration fileConfig = new YamlConfiguration();
		FileManager.loadFile(file, fileConfig);
		List<String> list = fileConfig.getStringList("Listening Chat Channels");
		list.add(channel);
		fileConfig.set("Listening Chat Channels", list);
		FileManager.saveFile(file, fileConfig);
		if (channel.equalsIgnoreCase("trade") || channel.equalsIgnoreCase("staff")) {
			FileConfiguration channelConfig = new YamlConfiguration();
			String path = "plugins/MinecraftRPChat/channel/" + channel + ".yml";
			File channelFile = new File(path);
			FileManager.loadFile(new File(path), channelConfig);
			List<String> participants = channelConfig.getStringList("Participants");
			participants.add(uuid.toString());
			channelConfig.set("Participants", participants);
			FileManager.saveFile(channelFile, channelConfig);
		}
	}
	
	public static void removeListeningChannel(UUID uuid, String channel) {
		File file = FileManager.getPlayerFile(uuid);
		FileConfiguration fileConfig = new YamlConfiguration();
		FileManager.loadFile(file, fileConfig);
		List<String> list = fileConfig.getStringList("Listening Chat Channels");
		list.remove(channel);
		fileConfig.set("Listening Chat Channels", list);
		FileManager.saveFile(file, fileConfig);		
		if (channel.equalsIgnoreCase("trade") || channel.equalsIgnoreCase("staff")) {
			FileConfiguration channelConfig = new YamlConfiguration();
			String path = "plugins/MinecraftRPChat/channel/" + channel + ".yml";
			File channelFile = new File(path);
			FileManager.loadFile(new File(path), channelConfig);
			List<String> participants = channelConfig.getStringList("Participants");
			participants.remove(uuid.toString());
			channelConfig.set("Participants", participants);
			FileManager.saveFile(channelFile, channelConfig);
		}
	}
}
