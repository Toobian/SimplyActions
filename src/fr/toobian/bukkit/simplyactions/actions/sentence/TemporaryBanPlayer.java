package fr.toobian.bukkit.simplyactions.actions.sentence;

import java.util.Calendar;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;
import fr.toobian.bukkit.simplyactions.actions.Effects;
import fr.toobian.bukkit.simplyactions.io.Store;

public class TemporaryBanPlayer extends Action {

	public TemporaryBanPlayer(SimplyActions plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			Player executioner = (Player) sender;
			if(!executioner.hasPermission("simplyactions.sentence.ban"))
				return true;
		}
		
		if(args.length >= 2) {
			Player player = plugin.getServer().getPlayer(args[0]);
			if(player == null || !player.isOnline()) {
				sender.sendMessage(ChatColor.RED + "Player not found");
				return false;
			}
			
			//Duration
			String duration = args[1];
			if(!duration.matches("(\\d+d)?(\\d+h)?(\\d+m)?(\\d+s)?"))
				return false;
			int days = 0, hours = 0, minutes = 0, seconds = 0;
			int idays = duration.indexOf("d"), ihours = duration.indexOf("h"), iminutes = duration.indexOf("m"), iseconds = duration.indexOf("s");
			if(idays > -1) {
				days = Integer.parseInt(duration.substring(0, idays));
				idays++;
			} else
				idays = 0;
			if(ihours > -1) {
				hours = Integer.parseInt(duration.substring(idays, ihours));
				ihours++;
			} else
				ihours = 0;
			if(iminutes > -1) {
				minutes = Integer.parseInt(duration.substring(ihours, iminutes));
				iminutes++;
			} else
				iminutes = 0;
			if(iseconds > -1)
				seconds = Integer.parseInt(duration.substring(iminutes, iseconds));
			
			Calendar end = Calendar.getInstance();
			end.add(Calendar.DAY_OF_YEAR, days);
			end.add(Calendar.HOUR, hours);
			end.add(Calendar.MINUTE, minutes);
			end.add(Calendar.SECOND, seconds);
			
			long theEnd = end.getTimeInMillis();

			String reason = "";
			if(args.length > 2) {
				reason = args[2];
				for(int i=3; i<args.length; i++) {
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
			store.set("sentence.banEnd", theEnd);
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
