package fr.toobian.bukkit.simplyactions.actions.teleportation;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;
import fr.toobian.bukkit.simplyactions.actions.Effects;
import fr.toobian.bukkit.simplyactions.io.Store;

public class PlayerToCoordinates extends Action {

	public PlayerToCoordinates(SimplyActions plugin) {
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
		double x,y,z;
		try {
			x = Double.parseDouble(args[1]);
			y = Double.parseDouble(args[2]);
			z = Double.parseDouble(args[3]);
			Location destination = new Location(traveler.getWorld(), x, y, z);
			Store store = new Store(plugin, traveler);
			store.set("teleportation.lastlocation", traveler.getLocation());
			Effects.TeleportationEffect(plugin, traveler.getLocation());
			traveler.teleport(destination);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}

}
