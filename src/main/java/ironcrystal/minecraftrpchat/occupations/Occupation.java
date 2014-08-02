package ironcrystal.minecraftrpchat.occupations;

import org.bukkit.ChatColor;
import ironcrystal.minecraftrpchat.file.FileManager;
import ironcrystal.minecraftrp.occupations.Occupations;

public class Occupation {
	
	public static ChatColor getChatColor(Occupations occup) {
		switch (occup) {
		case CITIZEN: return FileManager.getChatColorFromConfig(Occupations.CITIZEN.toString());
		case MAYOR: return FileManager.getChatColorFromConfig(Occupations.MAYOR.toString());
		case SUPPLIER: return FileManager.getChatColorFromConfig(Occupations.SUPPLIER.toString());
		case SHOPKEEPER: return FileManager.getChatColorFromConfig(Occupations.SHOPKEEPER.toString());
		case CONSTRUCTION_WORKER: return FileManager.getChatColorFromConfig(Occupations.CONSTRUCTION_WORKER.toString());
		default:
			break;
		}
		return null;
	}

}
