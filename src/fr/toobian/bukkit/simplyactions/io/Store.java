package fr.toobian.bukkit.simplyactions.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import fr.toobian.bukkit.simplyactions.SimplyActions;

public class Store{

	private YamlConfiguration file;
	private File filename;
	private SimplyActions plugin;

	public Store(SimplyActions plugin) {
		filename = new File(plugin.getDataFolder(), "data"+File.separator+"globals.yml");
		file = new YamlConfiguration();
		if(filename.exists()) {
			try {
				file.load(filename);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		}
		this.plugin = plugin;
	}
	
	public Store(SimplyActions plugin, Player player) {
		filename = new File(plugin.getDataFolder(), "data"+File.separator+"players"+File.separator+player.getName()+".yml");
		file = new YamlConfiguration();
		if(filename.exists()) {
			try {
				file.load(filename);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		}
		this.plugin = plugin;
	}

	public void set(String path, Object value) {
		file.set(path, value);
		if(file.getKeys(false).isEmpty()) {
			if(filename.exists()) {
				filename.delete();
			}
		} else {
			try {
				file.save(filename);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void set(String path, Location value) {
		file.set(path+".pitch", value.getPitch());
		file.set(path+".world", value.getWorld().getName());
		file.set(path+".x", value.getX());
		file.set(path+".y", value.getY());
		file.set(path+".yaw", value.getYaw());
		file.set(path+".z", value.getZ());

		if(file.getKeys(false).isEmpty()) {
			if(filename.exists()) {
				filename.delete();
			}
		} else {
			try {
				file.save(filename);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Object get(String path) {
		return file.get(path);
	}
	
	public boolean getBoolean(String path) {
		return file.getBoolean(path);
	}
	
	public double getDouble(String path) {
		return file.getDouble(path);
	}

	public int getInt(String path) {
		return file.getInt(path);
	}
	
	public Location getLocation(String path) {
		World world = plugin.getServer().getWorld(file.getString(path+".world"));
		double x = file.getDouble(path+".x");
		double y = file.getDouble(path+".y");
		double z = file.getDouble(path+".z");
		float yaw = Float.parseFloat(file.getString(path+".yaw"));
		float pitch = Float.parseFloat(file.getString(path+".pitch"));
		Location loc = new Location(world, x, y, z, yaw, pitch);
		return loc;
	}
	
	public long getLong(String path) {
		return file.getLong(path);
	}
	
	public String getString(String path) {
		return file.getString(path);
	}
	
	public Vector getVector(String path) {
		return file.getVector(path);
	}
}
