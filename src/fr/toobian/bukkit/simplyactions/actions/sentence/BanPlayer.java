package fr.toobian.bukkit.simplyactions.actions.sentence;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;
import fr.toobian.bukkit.simplyactions.actions.Effects;
import fr.toobian.bukkit.simplyactions.io.Store;

public class BanPlayer extends Action {

	public BanPlayer(SimplyActions plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			Player executioner = (Player) sender;
			if(!executioner.hasPermission("simplyactions.sentence.ban"))
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
			
			
			String message = plugin.config.getString("sentence.ban.msg", null);
			
			if(message == null) {
				plugin.getLogger().log(Level.CONFIG, "Propertie sentence.ban.msg is not define.");
				message = reason;
			} else {
				message = message.replaceAll("%p", player.getName()).replace("%r", reason);
			}
			
			String broadcast = plugin.config.getString("sentence.ban.broadcast", null);
			if(broadcast != null) 
				broadcast = message.replaceAll("%p", player.getName()).replace("%r", reason);
			
			Store store = new Store(plugin, player);
			store.set("sentence.banReason", reason);
			
			player.setBanned(true);
			Effects.BanEffect(plugin, player.getLocation());
			player.kickPlayer(reason);
			plugin.getServer().broadcastMessage(broadcast);
			return true;
		}
		return false;
	}

}
