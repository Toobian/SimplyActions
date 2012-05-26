package fr.toobian.bukkit.simplyactions.actions.teleportation;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;
import fr.toobian.bukkit.simplyactions.actions.Effects;
import fr.toobian.bukkit.simplyactions.io.Store;

public class YouToPreviousLocation extends Action {

	public YouToPreviousLocation(SimplyActions plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player!");
			return false;
		}
		Player player = (Player) sender;
		
		if(!player.hasPermission("simplyactions.teleportation.back"))
			return true;
		
		if(args.length == 0) {
			
			Store store = new Store(plugin, player);
			Location loc = store.getLocation("teleportation.lastlocation");

			if(loc != null) {
				store.set("teleportation.lastlocation", player.getLocation());
				Effects.TeleportationEffect(plugin, player.getLocation());
				player.teleport(loc);
			}
			return true;
		}
		return false;
	}

}
