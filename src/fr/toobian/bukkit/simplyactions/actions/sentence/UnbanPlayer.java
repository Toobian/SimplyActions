package fr.toobian.bukkit.simplyactions.actions.sentence;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;

public class UnbanPlayer extends Action {

	public UnbanPlayer(SimplyActions plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			Player executioner = (Player) sender;
			if(!executioner.hasPermission("simplyactions.sentence.unban"))
				return true;
		}

		if(args.length == 1) {
			OfflinePlayer player = plugin.getServer().getOfflinePlayer(args[0]);
			if(!player.isBanned())
				sender.sendMessage(ChatColor.RED + "Player is not banned");
			else
				player.setBanned(false);
			return true;
		}
		return false;
	}

}
