package ironcrystal.minecraftrpchat.ranks;

import org.bukkit.ChatColor;

import ironcrystal.minecraftrpchat.file.FileManager;

public enum Rank {
	NONE(ChatColor.WHITE),
	DONATOR(FileManager.getChatColorFromConfig("Donator")),
	SUPPORTER(FileManager.getChatColorFromConfig("Supporter")),
	SPONSOR(FileManager.getChatColorFromConfig("Sponsor")),
	VIP(FileManager.getChatColorFromConfig("VIP"));
	
	private ChatColor color;
	
	Rank(ChatColor color) {
		this.color = color;
	}
	
	public ChatColor getChatColor() {
		return color;
	}
	
	@Override
	public String toString() {
		switch(this) {
		case DONATOR: return "Donator";
		case SUPPORTER: return "Supporter";
		case SPONSOR: return "Sponsor";
		case VIP: return "VIP";
		case NONE: return "None";
		}
		return null;
	}
	
	public static Rank getRankByString(String str) {
		switch(str) {
		case "Donator":
		case "donator": return DONATOR;
		case "Supporter":
		case "supporter": return SUPPORTER;
		case "Sponsor":
		case "sponsor": return SPONSOR;
		case "Vip":
		case "VIP":
		case "vip": return VIP;
		case "None":
		case "none": return NONE;
		}
		return null;
	}

}
