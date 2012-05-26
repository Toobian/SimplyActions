package fr.toobian.bukkit.simplyactions.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;
import fr.toobian.bukkit.simplyactions.actions.teleportation.PlayerToCoordinates;
import fr.toobian.bukkit.simplyactions.actions.teleportation.PlayerToPlayerOrWorld;
import fr.toobian.bukkit.simplyactions.actions.teleportation.PlayerToYou;
import fr.toobian.bukkit.simplyactions.actions.teleportation.SaveCoordinatesAsSpawn;
import fr.toobian.bukkit.simplyactions.actions.teleportation.SaveCoordinatesWithWorldAsSpawn;
import fr.toobian.bukkit.simplyactions.actions.teleportation.SaveHereAsSpawn;
import fr.toobian.bukkit.simplyactions.actions.teleportation.SavePlayerPositionAsSpawn;
import fr.toobian.bukkit.simplyactions.actions.teleportation.YouToCoordinates;
import fr.toobian.bukkit.simplyactions.actions.teleportation.YouToPlayerOrWorld;
import fr.toobian.bukkit.simplyactions.actions.teleportation.YouToPreviousLocation;
import fr.toobian.bukkit.simplyactions.actions.teleportation.YouToSpawn;
import fr.toobian.bukkit.simplyactions.events.teleportation.SpawnEvent;

public class Teleportation implements CommandExecutor {

	private Map<Integer, Action> tpActions, setspawnAction;
	private Action tphereActions, backAction, spawnAction;

	public Teleportation(SimplyActions plugin) {
		
		PluginManager manager = plugin.getServer().getPluginManager();
		manager.registerEvents(new SpawnEvent(plugin), plugin);
		
		this.tpActions = new HashMap<Integer, Action>();
		this.tpActions.put(1, new YouToPlayerOrWorld(plugin));
		this.tpActions.put(2, new PlayerToPlayerOrWorld(plugin));
		this.tpActions.put(3, new YouToCoordinates(plugin));
		this.tpActions.put(4, new PlayerToCoordinates(plugin));

		this.tphereActions = new PlayerToYou(plugin);
		this.backAction = new YouToPreviousLocation(plugin);
		this.spawnAction = new YouToSpawn(plugin);
		
		this.setspawnAction = new HashMap<Integer, Action>();
		this.setspawnAction.put(0, new SaveHereAsSpawn(plugin));
		this.setspawnAction.put(1, new SavePlayerPositionAsSpawn(plugin));
		this.setspawnAction.put(3, new SaveCoordinatesAsSpawn(plugin));
		this.setspawnAction.put(4, new SaveCoordinatesWithWorldAsSpawn(plugin));

		plugin.getCommand("tp").setExecutor(this);
		plugin.getCommand("tphere").setExecutor(this);
		plugin.getCommand("back").setExecutor(this);
		plugin.getCommand("spawn").setExecutor(this);
		plugin.getCommand("setspawn").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel,
			String[] args) {
		// Hub
		if(cmd.getName().equalsIgnoreCase("tp")) {
			try {
				return this.tpActions.get(args.length).execute(sender, args);
			} catch (NullPointerException e) {
				if(sender instanceof Player && !((Player) sender).hasPermission("simplyactions.teleportation.tp"))
					return true;
				return false;
			}
		} else if(cmd.getName().equalsIgnoreCase("tphere")) {
			return this.tphereActions.execute(sender, args);
		} else if(cmd.getName().equalsIgnoreCase("back")) {
			return this.backAction.execute(sender, args);
		} else if(cmd.getName().equalsIgnoreCase("spawn")) {
			return this.spawnAction.execute(sender, args);
		} else if(cmd.getName().equalsIgnoreCase("setspawn")) {
			try {
				return this.setspawnAction.get(args.length).execute(sender, args);
			} catch (NullPointerException e) {
				if(sender instanceof Player && !((Player) sender).hasPermission("simplyactions.teleportation.setspawn"))
					return true;
				return false;
			}
		}
		return false;
	}
}
