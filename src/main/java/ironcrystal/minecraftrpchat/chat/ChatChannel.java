package ironcrystal.minecraftrpchat.chat;

import ironcrystal.minecraftrpchat.file.FileManager;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ChatChannel {
	private List<String> participants;
	private File file;
	
	public ChatChannel(File file) {
		this.file = file;
		FileConfiguration fileConfig = new YamlConfiguration();
		FileManager.loadFile(file, fileConfig);
		this.participants = fileConfig.getStringList("Participants");
	}
	
	public List<String> getParticipants() {
		return participants;
	}
	
	public ChatChannel addParticipant(UUID uuid) {
		this.participants.add(uuid.toString());
		FileConfiguration fileConfig = new YamlConfiguration();
		FileManager.loadFile(file, fileConfig);
		fileConfig.set("Participants", this.participants);
		FileManager.saveFile(file, fileConfig);
		return this;
	}
}
