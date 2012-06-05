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

public class YouToPlayerOrWorld extends Action {

	public YouToPlayerOrWorld(SimplyActions plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		// TODO Auto-generated method stub
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player!");
			return false;
		}
		Player player = (Player) sender;

		if(!player.hasPermission("simplyactions.teleportation.tp"))
			return true;
		
		String dest = args[0];
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
				Store destinationStore = new Store(plugin, destinationPlayer);
				if(destinationStore.getBoolean("teleportation.tptoggle")){
					sender.sendMessage(ChatColor.RED + "Player location is disabled");
					return true;
				}
				destination = destinationPlayer.getLocation();
				
			}
		}
		
		if(destination == null) {
			sender.sendMessage(ChatColor.RED + error);
			return false;
		} else {
			Store store = new Store(plugin, player);
			store.set("teleportation.lastlocation", player.getLocation());
			Effects.TeleportationEffect(plugin, player.getLocation());
			player.teleport(destination);
			return true;
		}
	}

}
