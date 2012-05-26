package fr.toobian.bukkit.simplyactions.events.teleportation;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.io.Store;

public class SpawnEvent implements Listener {

	private SimplyActions plugin;
	
	public SpawnEvent(SimplyActions plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Store store = new Store(plugin);
		Location spawn = store.getLocation("teleportation.spawn");
		event.setRespawnLocation(spawn);
	}
}
