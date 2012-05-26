package fr.toobian.bukkit.simplyactions.events.sentence;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import fr.toobian.bukkit.simplyactions.SimplyActions;
import fr.toobian.bukkit.simplyactions.io.Store;

public class BanEvent implements Listener {
	
	private SimplyActions plugin;
	
	public BanEvent(SimplyActions plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		if(player.isBanned()) {
			String reason = "";
			
			Store store = new Store(plugin, player);
			reason = store.getString("sentence.banReason");
			
			event.setKickMessage(reason);
		}
	}
}
