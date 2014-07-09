package ironcrystal.minecraftrpchat.staff;

import ironcrystal.minecraftrpchat.file.Files;

import org.bukkit.ChatColor;

public enum Staff {
	NONE(ChatColor.WHITE, ""),
	TRIALMOD(Files.getChatColorFromConfig("TrialMod"), "Trial Mod"),
	MOD(Files.getChatColorFromConfig("Mod"), "Moderator"),
	ADMIN(Files.getChatColorFromConfig("Admin"), "Admin"),
	OWNER(Files.getChatColorFromConfig("Owner"), "Owner");
	
	private ChatColor color;
	private String prefix;
	
	Staff(ChatColor color, String prefix) {
		this.color = color;
		this.prefix = prefix;
	}
	
	public ChatColor getChatColor() {
		return color;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	@Override
	public String toString() {
		switch(this) {
		case TRIALMOD: return "Trial Mod";
		case MOD: return "Mod";
		case ADMIN: return "Admin";
		case OWNER: return "Owner";
		case NONE: return "None";
		}
		return null;
	}
	
	public static Staff getStaffByString(String str) {
		switch(str) {
		case "Trial Mod":
		case "trial mod": return TRIALMOD;
		case "Mod":
		case "mod": return MOD;
		case "Admin":
		case "admin": return ADMIN;
		case "Owner":
		case "owner": return OWNER;
		case "None":
		case "none": return NONE;
		}
		return null;
	}

}
