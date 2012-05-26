package fr.toobian.bukkit.simplyactions.actions.teleportation;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;
import fr.toobian.bukkit.simplyactions.io.Store;

public class SaveHereAsSpawn extends Action {

	public SaveHereAsSpawn(SimplyActions plugin) {
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

		Store store = new Store(plugin);
		store.set("teleportation.spawn", player.getLocation());
		player.sendMessage("[SimplyActions] New spawn set here!");

		return true;
	}

}
