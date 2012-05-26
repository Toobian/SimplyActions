package fr.toobian.bukkit.simplyactions.actions;

import org.bukkit.command.CommandSender;

import fr.toobian.bukkit.simplyactions.SimplyActions;

public abstract class Action {
	protected SimplyActions plugin;
	
	public Action(SimplyActions plugin) {
		this.plugin = plugin;
	}
	
	public abstract boolean execute(CommandSender sender, String[] args);
}
