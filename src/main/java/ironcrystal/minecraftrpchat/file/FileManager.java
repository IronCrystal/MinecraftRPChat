package ironcrystal.minecraftrpchat.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileManager {

	public static File Config;
	public static File TradeChannel;
	public static File StaffChannel;
	
	public static void initializeFiles() {
		Config = new File("plugins/MinecraftRPChat/config.yml");
		if (!Config.exists()) {
			FileConfiguration fileConfig = new YamlConfiguration();
			saveFile(Config, fileConfig);
			FileManager.loadFile(Config, fileConfig);
			fileConfig.set("Citizen", 7);
			fileConfig.set("Mayor", 'e');
			fileConfig.set("Supplier", 'a');
			fileConfig.set("Shopkeeper", 'b');
			fileConfig.set("Donator", 'f');
			fileConfig.set("Supporter", 6);
			fileConfig.set("Sponsor", 1);
			fileConfig.set("VIP", 5);
			fileConfig.set("Beta Tester", 'd');
			fileConfig.set("TrialMod", 8);
			fileConfig.set("Mod", 8);
			fileConfig.set("Admin", 'c');
			fileConfig.set("Owner", 3);
			fileConfig.set("Channel Prefix Color", 2);
			fileConfig.set("Local Distance", 250);
			fileConfig.set("Go here to look up color codes", "http://minecraft.gamepedia.com/Formatting_codes");
			saveFile(Config, fileConfig);
		}
		TradeChannel = new File("plugins/MinecraftRPChat/channel/trade.yml");
		if (!TradeChannel.exists()) {
			FileConfiguration fileConfig = new YamlConfiguration();
			saveFile(TradeChannel, fileConfig);
			loadFile(TradeChannel, fileConfig);
			fileConfig.set("Participants", new ArrayList<String>());
			saveFile(TradeChannel, fileConfig);
		}
		StaffChannel = new File("plugins/MinecraftRPChat/channel/staff.yml");
		if (!StaffChannel.exists()) {
			FileConfiguration fileConfig = new YamlConfiguration();
			saveFile(StaffChannel, fileConfig);
			loadFile(StaffChannel, fileConfig);
			fileConfig.set("Participants", new ArrayList<String>());
			saveFile(StaffChannel, fileConfig);
		}
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[MinecraftRPChat] Files Initialized");
	}
	
	public static ChatColor getChatColorFromConfig(String string) {
		FileConfiguration fileConfig = new YamlConfiguration();
		loadFile(Config, fileConfig);
		String colorChar = fileConfig.getString(string);
		ChatColor color = ChatColor.getByChar(colorChar);
		return color;
	}
	
	public static void createPlayerFile(UUID uuid) {
		String path = "plugins/MinecraftRPChat/player/";
		File file = new File(path + uuid + ".yml");
		if (!file.exists()) {
			FileConfiguration fileConfig = new YamlConfiguration();
			saveFile(file, fileConfig);
			loadFile(file, fileConfig);
			List<String> channels = new ArrayList<String>();
			channels.add("Local");
			fileConfig.set("Listening Chat Channels", channels);
			fileConfig.set("Talking Chat Channel", "Local");
			saveFile(file, fileConfig);
		}
	}
	
	public static File getPlayerFile(UUID uuid) {
		String path = "plugins/MinecraftRPChat/player/";
		return new File(path + uuid + ".yml");
	}
	
	public static void saveFile(File file, FileConfiguration fileConfig) {
		try {
			fileConfig.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadFile(File file, FileConfiguration fileConfig) {
		try {
			fileConfig.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
}
