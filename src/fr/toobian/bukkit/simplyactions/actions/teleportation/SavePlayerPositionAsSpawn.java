package fr.toobian.bukkit.simplyactions.actions.teleportation;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;
import fr.toobian.bukkit.simplyactions.io.Store;

public class SavePlayerPositionAsSpawn extends Action {

	public SavePlayerPositionAsSpawn(SimplyActions plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			Player executioner = (Player) sender;
			if(!executioner.hasPermission("simplyactions.teleportation.setspawn"))
				return true;
		}
		
		Player player = plugin.getServer().getPlayer(args[0]);
		if(player == null || !player.isOnline()) {
			sender.sendMessage(ChatColor.RED + "Player not found");
			return false;
		}

		Store store = new Store(plugin);
		store.set("teleportation.spawn", player.getLocation());
		player.sendMessage("[SimplyActions] New spawn set on "+player.getName()+"'s position!");
		return true;
	}

}
