package ironcrystal.minecraftrpchat.player;

import ironcrystal.minecraftrpchat.ranks.Rank;
import ironcrystal.minecraftrpchat.staff.Staff;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ChattablePlayer {
	private UUID uuid;
		
	public ChattablePlayer(UUID uuid) {
		this.uuid = uuid;
	}

	public UUID getUuid() {
		return uuid;
	}
	
	public Rank getRank() {
		Player player = Bukkit.getPlayer(uuid);
		if (player.hasPermission("rank.vip")) {
			return Rank.VIP;
		}
		else if (player.hasPermission("rank.sponsor")) {
			return Rank.SPONSOR;
		}
		else if (player.hasPermission("rank.supporter")) {
			return Rank.SUPPORTER;
		}
		else if (player.hasPermission("rank.donator")) {
			return Rank.DONATOR;
		}
		return Rank.NONE;
	}
	
	public Staff getStaff() {
		Player player = Bukkit.getPlayer(uuid);
		if (player.hasPermission("staff.owner")) {
			return Staff.OWNER;
		}
		else if (player.hasPermission("staff.admin")) {
			return Staff.ADMIN;
		}
		else if (player.hasPermission("staff.moderator")) {
			return Staff.MOD;
		}
		else if (player.hasPermission("staff.trialmod")) {
			return Staff.TRIALMOD;
		}
		return Staff.NONE;
	}
}
