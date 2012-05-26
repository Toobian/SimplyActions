package fr.toobian.bukkit.simplyactions.actions.sentence;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;

public class KickPlayer extends Action {

	public KickPlayer(SimplyActions plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			Player executioner = (Player) sender;
			if(!executioner.hasPermission("simplyactions.sentence.kick"))
				return true;
		}

		if(args.length >= 1) {

			Player player = plugin.getServer().getPlayer(args[0]);
			if(player == null || !player.isOnline()) {
				sender.sendMessage(ChatColor.RED + "Player not found");
				return false;
			}

			String reason = "";
			if(args.length > 1) {
				reason = args[1];
				for(int i=2; i<args.length; i++) {
					reason+= " " + args[i];
				}
			}
			
			String message = plugin.config.getString("sentence.kick.msg", null);
			
			if(message == null) {
				plugin.getLogger().log(Level.CONFIG, "Propertie sentence.kick.msg is not define.");
				message = reason;
			} else {
				message = message.replaceAll("%p", player.getName()).replace("%r", reason);
			}
			
			String broadcast = plugin.config.getString("sentence.kick.broadcast", null);
			if(broadcast != null) 
				broadcast = message.replaceAll("%p", player.getName()).replace("%r", reason);
			
			player.kickPlayer(message);
			plugin.getServer().broadcastMessage(broadcast);
			return true;
		}
		return false;
	}

}
