package fr.toobian.bukkit.simplyactions.actions.weather;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.actions.Action;

public class Storm extends Action{

	public Storm(SimplyActions plugin) {
		super(plugin);
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		List<World> worlds = new ArrayList<World>();
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if(!player.hasPermission("simplyactions.weather"))
				return true;
			worlds.add(player.getWorld());
		}
		
		if(args.length > 0) {
			worlds.clear();
			for(String world : args) {
				World w = plugin.getServer().getWorld(world);
				if(w != null)
					worlds.add(w);
			}
		}
		
		if(worlds.isEmpty())
			return false;
		
		for(World w : worlds) {
			w.setStorm(true);
		}
		return true;
	}

}
