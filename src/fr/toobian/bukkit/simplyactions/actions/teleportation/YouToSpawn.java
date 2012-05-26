package fr.toobian.bukkit.simplyactions.actions.teleportation;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;
import fr.toobian.bukkit.simplyactions.actions.Effects;
import fr.toobian.bukkit.simplyactions.io.Store;

public class YouToSpawn extends Action {

	public YouToSpawn(SimplyActions plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player!");
			return false;
		}
		Player player = (Player) sender;
		
		if(!player.hasPermission("simplyactions.teleportation.tp"))
			return true;

		if(args.length >= 1) {
			return false;
		}
		
		Store globalStore = new Store(plugin);
		Store store = new Store(plugin, player);
		store.set("teleportation.lastlocation", player.getLocation());
		Effects.TeleportationEffect(plugin, player.getLocation());
		player.teleport(globalStore.getLocation("teleportation.spawn"));

		return true;
	}

}
