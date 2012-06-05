/**
 * 
 */
package fr.toobian.bukkit.simplyactions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;
import fr.toobian.bukkit.simplyactions.actions.sentence.BanPlayer;
import fr.toobian.bukkit.simplyactions.actions.sentence.KickPlayer;
import fr.toobian.bukkit.simplyactions.actions.sentence.KillPlayer;
import fr.toobian.bukkit.simplyactions.actions.sentence.TemporaryBanPlayer;
import fr.toobian.bukkit.simplyactions.actions.sentence.UnbanPlayer;
import fr.toobian.bukkit.simplyactions.events.sentence.BanEvent;
import fr.toobian.bukkit.simplyactions.events.sentence.KickEvent;

/**
 * @author Toobian
 *
 */
public class Sentence implements CommandExecutor {
	
	private Action kickAction, banAction, tempban, unbanAction, killAction;

	public Sentence(SimplyActions plugin) {
		
		PluginManager manager = plugin.getServer().getPluginManager();
		manager.registerEvents(new BanEvent(plugin), plugin);
		manager.registerEvents(new KickEvent(), plugin);
		
		this.kickAction = new KickPlayer(plugin);
		this.banAction = new BanPlayer(plugin);
		this.tempban = new TemporaryBanPlayer(plugin);
		this.unbanAction = new UnbanPlayer(plugin);
		this.killAction = new KillPlayer(plugin);
		
		plugin.getCommand("kick").setExecutor(this);
		plugin.getCommand("ban").setExecutor(this);
		plugin.getCommand("tempban").setExecutor(this);
		plugin.getCommand("unban").setExecutor(this);
		plugin.getCommand("kill").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel,
			String[] args) {
		// Hub
		if(cmd.getName().equalsIgnoreCase("kick")) {
			return this.kickAction.execute(sender, args);
		} else if(cmd.getName().equalsIgnoreCase("ban")) {
			return this.banAction.execute(sender, args);
		} else if(cmd.getName().equalsIgnoreCase("tempban")) {
			return this.tempban.execute(sender, args);
		} else if(cmd.getName().equalsIgnoreCase("unban")) {
			return this.unbanAction.execute(sender, args);
		} else if(cmd.getName().equalsIgnoreCase("kill")) {
			return this.killAction.execute(sender, args);
		} 
		return false;
	}

}
