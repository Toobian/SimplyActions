package fr.toobian.bukkit.simplyactions.actions.sentence;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;

public class KillPlayer extends Action {

	public KillPlayer(SimplyActions plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			Player executioner = (Player) sender;
			if(!executioner.hasPermission("simplyactions.sentence.kill"))
				return true;
		}
		
		if(args.length == 1) {
			Player player = plugin.getServer().getPlayer(args[0]);
			if(player == null || !player.isOnline()) {
				sender.sendMessage(ChatColor.RED + "Player not found");
				return false;
			}
			player.setHealth(0);
			return true;
		}
		return false;
	}

}
