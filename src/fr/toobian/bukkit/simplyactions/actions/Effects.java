package fr.toobian.bukkit.simplyactions.actions;

import org.bukkit.Effect;
import org.bukkit.Location;

import fr.toobian.bukkit.simplyactions.SimplyActions;

public class Effects {

	public static void TeleportationEffect(final SimplyActions plugin, final Location loc) {
		if(plugin.config.getBoolean("teleportation.effects", false)) {
			loc.add(0, 0.5, 0);
			loc.getWorld().playEffect(loc, Effect.ENDER_SIGNAL, 0);
			loc.getWorld().playEffect(loc, Effect.ENDER_SIGNAL, 0);
			loc.getWorld().playEffect(loc, Effect.SMOKE, 4);
			loc.getWorld().playEffect(loc, Effect.SMOKE, 4);
			loc.getWorld().playEffect(loc, Effect.SMOKE, 4);
			loc.getWorld().playEffect(loc, Effect.GHAST_SHOOT, 0);
		}
	}
	
	public static void BanEffect(final SimplyActions plugin, final Location loc) {
		if(plugin.config.getBoolean("teleportation.effects", false)) {
			loc.getWorld().createExplosion(loc, 0F);
			loc.getWorld().createExplosion(loc, 0F);
			loc.getWorld().createExplosion(loc, 0F);
			loc.getWorld().createExplosion(loc, 0F);
			loc.getWorld().createExplosion(loc, 0F);
		}
	}

}
