package fr.toobian.bukkit.simplyactions.actions.teleportation;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;
import fr.toobian.bukkit.simplyactions.io.Store;

public class SaveCoordinatesWithWorldAsSpawn extends Action {

	public SaveCoordinatesWithWorldAsSpawn(SimplyActions plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			Player executioner = (Player) sender;
			if(!executioner.hasPermission("simplyactions.teleportation.setspawn"))
				return true;
		}
		
		World world = plugin.getServer().getWorld(args[0]);
		if(world == null) {
			sender.sendMessage(ChatColor.RED + "World not found");
			return false;
		}

		double x,y,z;
		try {
			x = Double.parseDouble(args[1]);
			y = Double.parseDouble(args[2]);
			z = Double.parseDouble(args[3]);

			Store store = new Store(plugin);
			store.set("teleportation.spawn", new Location(world, x, y, z));
			sender.sendMessage("[SimplyActions] New spawn set on "+world.getName()+" at X="+x+" Y="+y+" Z="+z+"!");
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}

}
