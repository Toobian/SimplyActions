package fr.toobian.bukkit.simplyactions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;
import fr.toobian.bukkit.simplyactions.actions.weather.Storm;
import fr.toobian.bukkit.simplyactions.actions.weather.Sun;
import fr.toobian.bukkit.simplyactions.actions.weather.Thundering;

public class Weather implements CommandExecutor {
	
	private Action sunAction, stormAction, thunderAction;

	public Weather(SimplyActions plugin) {
		this.sunAction = new Sun(plugin);
		this.stormAction = new Storm(plugin);
		this.thunderAction = new Thundering(plugin);
		
		plugin.getCommand("sun").setExecutor(this);
		plugin.getCommand("storm").setExecutor(this);
		plugin.getCommand("thunder").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel,
			String[] args) {
		// Hub
		if(cmd.getName().equalsIgnoreCase("sun")) {
			return this.sunAction.execute(sender, args);
		} else if(cmd.getName().equalsIgnoreCase("storm")) {
			return this.stormAction.execute(sender, args);
		} else if(cmd.getName().equalsIgnoreCase("thunder")) {
			return this.thunderAction.execute(sender, args);
		}
		return false;
	}

}
