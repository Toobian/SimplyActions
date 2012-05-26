package fr.toobian.bukkit.simplyactions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;
import fr.toobian.bukkit.simplyactions.actions.communication.BroadcastMessage;

public class Communication implements CommandExecutor {
	
	private Action broadcastAction;
	
	public Communication(SimplyActions plugin) {
		plugin.getCommand("broadcast").setExecutor(this);
		broadcastAction = new BroadcastMessage(plugin);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel,
			String[] args) {
		// Hub
		if(cmd.getName().equalsIgnoreCase("broadcast")) {
			return this.broadcastAction.execute(sender, args);
		}
		return false;
	}
}
