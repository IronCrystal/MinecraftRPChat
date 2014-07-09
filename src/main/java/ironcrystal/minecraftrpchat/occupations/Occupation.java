package ironcrystal.minecraftrpchat.occupations;

import org.bukkit.ChatColor;

import ironcrystal.minecraftrp.occupations.Occupations;
import ironcrystal.minecraftrpchat.file.Files;

public class Occupation {
	
	public static ChatColor getChatColor(Occupations occup) {
		switch (occup) {
		case CITIZEN: return Files.getChatColorFromConfig(Occupations.CITIZEN.toString());
		case MAYOR: return Files.getChatColorFromConfig(Occupations.MAYOR.toString());
		case SUPPLIER: return Files.getChatColorFromConfig(Occupations.SUPPLIER.toString());
		default:
			break;
		}
		return null;
	}

}
