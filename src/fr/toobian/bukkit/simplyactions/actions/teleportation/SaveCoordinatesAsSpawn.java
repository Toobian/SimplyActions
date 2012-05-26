package fr.toobian.bukkit.simplyactions.actions.teleportation;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;
import fr.toobian.bukkit.simplyactions.io.Store;

public class SaveCoordinatesAsSpawn extends Action {

	public SaveCoordinatesAsSpawn(SimplyActions plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player!");
			return false;
		}
		Player player = (Player) sender;
		
		if(!player.hasPermission("simplyactions.teleportation.setspawn"))
			return true;
		
		double x,y,z;
		try {
			x = Double.parseDouble(args[0]);
			y = Double.parseDouble(args[1]);
			z = Double.parseDouble(args[2]);

			Store store = new Store(plugin);
			store.set("teleportation.spawn", new Location(player.getWorld(), x, y, z));
			player.sendMessage("[SimplyActions] New spawn set on "+player.getWorld().getName()+" at X="+x+" Y="+y+" Z="+z+"!");
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}

}
