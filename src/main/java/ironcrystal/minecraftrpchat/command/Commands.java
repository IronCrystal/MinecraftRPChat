package ironcrystal.minecraftrpchat.command;

import ironcrystal.minecraftrpchat.chat.ChatChannelManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("chat")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length == 0) {
					sendHelp(sender);
				}else{
					if (args[0].equalsIgnoreCase("join")) {
						if (args.length == 2) {
							if (args[1].equalsIgnoreCase("local")) {
								ChatChannelManager.setTalkingChannel(player.getUniqueId(), "Local");
								if (!ChatChannelManager.isListeningToChat(player.getUniqueId(), "Local"))
									ChatChannelManager.addListeningChannel(player.getUniqueId(), "Local");
								player.sendMessage(ChatColor.AQUA + "[MinecraftRPChat] You succesfully joined the local chat");
							}
							else if (args[1].equalsIgnoreCase("town")) {
								ChatChannelManager.setTalkingChannel(player.getUniqueId(), "Town");
								if (!ChatChannelManager.isListeningToChat(player.getUniqueId(), "Town")) {
									ChatChannelManager.addListeningChannel(player.getUniqueId(), "Town");
								}
								player.sendMessage(ChatColor.AQUA + "[MinecraftRPChat] You succesfully joined the town chat");
							}
							else if (args[1].equalsIgnoreCase("trade")) {
								ChatChannelManager.setTalkingChannel(player.getUniqueId(), "trade");
								if (!ChatChannelManager.isListeningToChat(player.getUniqueId(), "trade"))
									ChatChannelManager.addListeningChannel(player.getUniqueId(), "trade");
								player.sendMessage(ChatColor.AQUA + "[MinecraftRPChat] You succesfully joined the trading chat");
							}
							else if (args[1].equalsIgnoreCase("staff") && sender.hasPermission("chat.staff")) {
								ChatChannelManager.setTalkingChannel(player.getUniqueId(), "staff");
								if (!ChatChannelManager.isListeningToChat(player.getUniqueId(), "staff"))
									ChatChannelManager.addListeningChannel(player.getUniqueId(), "staff");
								player.sendMessage(ChatColor.AQUA + "[MinecraftRPChat] You succesfully joined the staff chat");
							}
							else if (args[1].equalsIgnoreCase("global") && (sender.hasPermission("staff.admin") || sender.hasPermission("staff.owner"))) {
								ChatChannelManager.setTalkingChannel(player.getUniqueId(), "global");
								player.sendMessage(ChatColor.AQUA + "[MinecraftRPChat] You succesfully joined the global chat");
							}else{
								player.sendMessage(ChatColor.RED + "[MinecraftRPChat] Incorrect chat channel!");
								player.sendMessage(ChatColor.RED + "[MinecraftRPChat] The Choices are: local, town, trade");
							}
						}else{
							sendHelp(sender);
						}
					}else if (args[0].equalsIgnoreCase("leave")) {
						String channel = ChatChannelManager.getTalkingChannel(player.getUniqueId());
						ChatChannelManager.setTalkingChannel(player.getUniqueId(), "Local");
						ChatChannelManager.removeListeningChannel(player.getUniqueId(), channel);
						player.sendMessage(ChatColor.AQUA + "[MinecraftRPChat] You succesfully left the " + channel + " channel.");
					}
				}
			}
		}
		return false;
	}

	private void sendHelp(CommandSender sender) {
		sender.sendMessage(ChatColor.AQUA + "--------------------Chat--------------------");
		sender.sendMessage("");
		sender.sendMessage(ChatColor.AQUA + "/chat join <channel>  | Joins a chat channel");
		sender.sendMessage(ChatColor.AQUA + "/chat leave           | Leaves the current channel");
	}
}
