/**
 * 
 */
package fr.toobian.bukkit.simplyactions;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import fr.toobian.bukkit.simplyactions.commands.Communication;
import fr.toobian.bukkit.simplyactions.commands.Sentence;
import fr.toobian.bukkit.simplyactions.commands.Teleportation;
import fr.toobian.bukkit.simplyactions.commands.Weather;

/**
 * @author Toobian
 *
 */
public class SimplyActions extends JavaPlugin implements Listener {

	public FileConfiguration config;

	@Override
	public void onEnable() {

		if(!(new File(getDataFolder(), "config.yml")).exists()) {
			(new File(getDataFolder(), "data"+File.separator+"players")).mkdirs();
			saveDefaultConfig();
		}
		config = getConfig();

		//Communication Module
		if(config.getBoolean("communication.enabled", true))
			new Communication(this);
		//Sentence Module
		if(config.getBoolean("sentence.enabled", true))
			new Sentence(this);
		//Teleportation Module
		if(config.getBoolean("teleportation.enabled", true))
			new Teleportation(this);
		//Weather Module
		if(config.getBoolean("weather.enabled", true))
			new Weather(this);
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}

}
