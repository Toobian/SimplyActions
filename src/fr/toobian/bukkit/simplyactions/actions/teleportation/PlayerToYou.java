package fr.toobian.bukkit.simplyactions.actions.teleportation;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;
import fr.toobian.bukkit.simplyactions.actions.Effects;
import fr.toobian.bukkit.simplyactions.io.Store;

public class PlayerToYou extends Action {

	public PlayerToYou(SimplyActions plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player!");
			return false;
		}
		
		Player player = (Player) sender;
		
		if(!player.hasPermission("simplyactions.teleportation.tphere"))
			return true;
		
		Player traveler = plugin.getServer().getPlayer(args[0]);
		if(traveler == null || !traveler.isOnline()) {
			sender.sendMessage(ChatColor.RED + "Player not found");
			return false;
		}
		
		Store store = new Store(plugin, traveler);
		if(store.getBoolean("teleportation.tptoggle")){
			sender.sendMessage(ChatColor.RED + "Player location is disabled");
			return true;
		}
		
		store.set("teleportation.lastlocation", traveler.getLocation());
		Effects.TeleportationEffect(plugin, traveler.getLocation());
		traveler.teleport(player);
		return true;
	}

}
