package fr.toobian.bukkit.simplyactions.events.sentence;

import java.util.Date;

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
		Store store = new Store(plugin, player);
		long end = store.getLong("sentence.banEnd");
		if(end > 0) {
			Date theEnd = new Date(store.getLong("sentence.banEnd"));
			if(theEnd.before(new Date()))
				player.setBanned(false);
		}
		
		if(player.isBanned()) {
			String reason = "";
			
			reason = store.getString("sentence.banReason");
			
			event.setKickMessage(reason);
		}
	}
}
