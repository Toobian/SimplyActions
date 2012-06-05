package fr.toobian.bukkit.simplyactions.actions.teleportation;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;
import fr.toobian.bukkit.simplyactions.io.Store;

public class ChangeTpAuthorization extends Action {

	public ChangeTpAuthorization(SimplyActions plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player!");
			return false;
		}
		Player player = (Player) sender;
		
		if(!player.hasPermission("simplyactions.teleportation.tptoggle"))
			return true;
		
		Store store = new Store(plugin, player);
		store.set("teleportation.tptoggle", !store.getBoolean("teleportation.tptoggle"));
		
		sender.sendMessage("You're tp location is disable? "+store.getBoolean("teleportation.tptoggle"));
		return true;
	}

}
