package ironcrystal.minecraftrpchat.file;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Files {

	public static File Config;
	
	public static void initializeFiles() {
		Config = new File("plugins/MinecraftRPChat/config.yml");
		if (!Config.exists()) {
			FileConfiguration fileConfig = new YamlConfiguration();
			saveConfig(fileConfig);
			Files.loadFile(Config, fileConfig);
			fileConfig.set("Citizen", 7);
			fileConfig.set("Mayor", 'e');
			fileConfig.set("Supplier", 'a');
			fileConfig.set("Donator", 'f');
			fileConfig.set("Supporter", 6);
			fileConfig.set("Sponsor", 1);
			fileConfig.set("VIP", 5);
			fileConfig.set("TrialMod", 8);
			fileConfig.set("Mod", 8);
			fileConfig.set("Admin", 'c');
			fileConfig.set("Owner", 3);
			fileConfig.set("Go here to look up color codes", "http://minecraft.gamepedia.com/Formatting_codes");
			saveConfig(fileConfig);
		}
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[MinecraftRPChat] Files Initialized");
	}
	
	public static ChatColor getChatColorFromConfig(String string) {
		FileConfiguration fileConfig = new YamlConfiguration();
		Files.loadFile(Files.Config, fileConfig);
		String colorChar = fileConfig.getString(string);
		ChatColor color = ChatColor.getByChar(colorChar);
		return color;
	}
	
	public static void saveConfig(FileConfiguration fileConfig) {
		try {
			fileConfig.save(Config);
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
