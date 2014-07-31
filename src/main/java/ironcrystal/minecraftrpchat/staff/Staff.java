package ironcrystal.minecraftrpchat.staff;

import ironcrystal.minecraftrpchat.file.FileManager;

import org.bukkit.ChatColor;

public enum Staff {
	NONE(ChatColor.WHITE, ""),
	BETATESTER(FileManager.getChatColorFromConfig("Beta Tester"), "Beta Tester"),
	TRIALMOD(FileManager.getChatColorFromConfig("TrialMod"), "Trial Mod"),
	MOD(FileManager.getChatColorFromConfig("Mod"), "Moderator"),
	ADMIN(FileManager.getChatColorFromConfig("Admin"), "Admin"),
	OWNER(FileManager.getChatColorFromConfig("Owner"), "Owner");
	
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
		case BETATESTER: return "Beta Tester";
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
		case "Beta Tester":
		case "beta tester": return BETATESTER;
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
