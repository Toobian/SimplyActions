package fr.toobian.bukkit.simplyactions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;

import fr.toobian.bukkit.simplyactions.SimplyActions;

public class Time implements CommandExecutor {
	
	public Time(SimplyActions plugin) {
		PluginManager manager = plugin.getServer().getPluginManager();
		
		
		plugin.getCommand("time").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel,
			String[] args) {
		// Hub
		if(cmd.getName().equalsIgnoreCase("time")) {
			return this.kickAction.execute(sender, args);
		}
		return false;
	}

}
