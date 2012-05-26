package fr.toobian.bukkit.simplyactions.actions.communication;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;

public class BroadcastMessage extends Action {

	public BroadcastMessage(SimplyActions plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		String message = "";
		for(String arg : args) {
			message = message + " " + arg;
		}
		message = ChatColor.translateAlternateColorCodes('&', message.substring(1));
		message = plugin.config.getString("communication.broadcast.suffix", "") + message;
		this.plugin.getServer().broadcastMessage(message);
		return true;
	}

}
