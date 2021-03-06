package com.gmail.stefvanschiedev.buildinggame.managers.stats;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.gmail.stefvanschiedev.buildinggame.Main;
import com.gmail.stefvanschiedev.buildinggame.managers.files.SettingsManager;
import com.gmail.stefvanschiedev.buildinggame.utils.stats.Stat;
import com.gmail.stefvanschiedev.buildinggame.utils.stats.StatType;

public class StatManager {
	
	private static StatManager instance = new StatManager();
	private MySQLDatabase database;
	
	private List<Stat> stats = new ArrayList<Stat>();
	
	private StatManager() {}
	
	public void setup() {
		YamlConfiguration config = SettingsManager.getInstance().getConfig();
		YamlConfiguration stats = SettingsManager.getInstance().getStats();
		
		if (!config.getBoolean("stats.enable"))
			return;
		
		if (config.getBoolean("stats.database.enable")) {
			database = new MySQLDatabase(Main.getInstance());
			
			if (database.setup()) {
				Set<UUID> uuids = database.getAllPlayers();
			
				for (UUID uuid : uuids) {
					for (StatType statType : StatType.values())
						this.stats.add(new Stat(statType, Bukkit.getOfflinePlayer(uuid), database.getStat(uuid.toString(), statType.toString().toLowerCase())));
				}
			
				return;
			}
		
			Main.getInstance().getLogger().warning("Database usage failed; returning to flat-file storage");
		}
		
		for (String uuid : stats.getKeys(false)) {
			OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
			
			for (String stat : stats.getConfigurationSection(uuid).getKeys(false)) {
				if (stat.equalsIgnoreCase("plays"))
					this.stats.add(new Stat(StatType.PLAYS, player, stats.getInt(uuid + "." + stat)));
				else if (stat.equalsIgnoreCase("first"))
					this.stats.add(new Stat(StatType.FIRST, player, stats.getInt(uuid + "." + stat)));
				else if (stat.equalsIgnoreCase("second"))
					this.stats.add(new Stat(StatType.SECOND, player, stats.getInt(uuid + "." + stat)));
				else if (stat.equalsIgnoreCase("third"))
					this.stats.add(new Stat(StatType.THIRD, player, stats.getInt(uuid + "." + stat)));
				else if (stat.equalsIgnoreCase("broken"))
					this.stats.add(new Stat(StatType.BROKEN, player, stats.getInt(uuid + "." + stat)));
				else if (stat.equalsIgnoreCase("placed"))
					this.stats.add(new Stat(StatType.PLACED, player, stats.getInt(uuid + "." + stat)));
				else if (stat.equalsIgnoreCase("walked"))
					this.stats.add(new Stat(StatType.WALKED, player, stats.getInt(uuid + "." + stat)));
			}
		}
	}
	
	public boolean containsUUID(UUID uuid) {
		for (Stat stat : stats) {
			if (stat.getPlayer().getUniqueId().equals(uuid))
				return true;
		}
		
		return false;
	}
	
	public Stat getStat(Player player, StatType type) {
		for (Stat stat : stats) {
			if (stat.getPlayer().getPlayer() == player && stat.getType() == type)
				return stat;
		}
		return null;
	}
	
	public List<Stat> getStats() {
		return stats;
	}
	
	public List<Stat> getStats(StatType type) {
		List<Stat> stats = new ArrayList<>();
		
		for (Stat stat : this.stats) {
			if (stat.getType() == type)
				stats.add(stat);
		}
		
		return stats;
	}
	
	public void registerStat(Player player, StatType type, int value) {
		YamlConfiguration config = SettingsManager.getInstance().getConfig();
		
		if (!config.getBoolean("stats.enable"))
			return;
		
		if (getStat(player, type) != null)
			stats.remove(getStat(player, type));
		
		stats.add(new Stat(type, player, value));
	}
	
	/**
	 * Because of massive amount of stat saving,
	 * we need to do this every once in a while.
	 */
	public void saveToFile() {
		YamlConfiguration config = SettingsManager.getInstance().getConfig();
		YamlConfiguration stats = SettingsManager.getInstance().getStats();
		
		if (!config.getBoolean("stats.enable"))
			return;
		
		/* 
		 * Create a new instance of the stat list
		 * because of ConcurrentModificationException
		 */
		
		List<Stat> statistics = this.stats;
		
		for (int i = 0; i < statistics.size(); i++) {
			Stat stat = statistics.get(i);
			stats.set(stat.getPlayer().getUniqueId() + "." + stat.getType().toString().toLowerCase(), stat.getValue());
		}
		
		SettingsManager.getInstance().save();
	}
	
	public void saveToDatabase(){
		List<Stat> stats = this.stats;
		
		for (int i = 0; i < stats.size(); i++) {
			Stat stat = stats.get(i);
			getMySQLDatabase().setStat(stat.getPlayer().getUniqueId().toString(), stat.getType().toString().toLowerCase(), stat.getValue());
		}
	}
	
	public static StatManager getInstance() {
		return instance;
	}
	
	public MySQLDatabase getMySQLDatabase() {
		return database;
	}
}