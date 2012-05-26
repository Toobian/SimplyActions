package fr.toobian.bukkit.simplyactions.actions.teleportation;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;
import fr.toobian.bukkit.simplyactions.actions.Effects;
import fr.toobian.bukkit.simplyactions.io.Store;

public class PlayerToPlayerOrWorld extends Action {

	public PlayerToPlayerOrWorld(SimplyActions plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			Player executioner = (Player) sender;
			if(!executioner.hasPermission("simplyactions.teleportation.tp"))
				return true;
		}
		
		Player traveler = plugin.getServer().getPlayer(args[0]);
		if(traveler == null || !traveler.isOnline()) {
			sender.sendMessage(ChatColor.RED + "Player not found");
			return false;
		}
		
		String dest = args[1];
		Location destination = null;
		String error = "";
		
		//Check override
		boolean override = false;
		if(dest.matches("w:\\w+")) {
			override = true;
			dest = dest.substring(2);
		}
		
		//Test World
		World w = plugin.getServer().getWorld(dest);
		if(w == null) {
			error = "Unknown world";
		} else {
			destination = w.getSpawnLocation();
		}

		if(!override) {
			//Test Player
			Player destinationPlayer = plugin.getServer().getPlayer(dest);
			if(destinationPlayer == null || !destinationPlayer.isOnline()) {
				error = "Player not found";
			} else {
				destination = destinationPlayer.getLocation();
			}
		}
		
		if(destination == null) {
			sender.sendMessage(ChatColor.RED + error);
			return false;
		} else {
			Store store = new Store(plugin, traveler);
			store.set("teleportation.lastlocation", traveler.getLocation());
			Effects.TeleportationEffect(plugin, traveler.getLocation());
			traveler.teleport(destination);
			return true;
		}
	}

}
