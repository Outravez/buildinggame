package com.gmail.stefvanschiedev.buildinggame.managers.arenas;

import org.bukkit.configuration.file.YamlConfiguration;

import com.gmail.stefvanschiedev.buildinggame.Main;
import com.gmail.stefvanschiedev.buildinggame.managers.files.SettingsManager;
import com.gmail.stefvanschiedev.buildinggame.utils.arena.Arena;
import com.gmail.stefvanschiedev.buildinggame.utils.arena.ArenaMode;

public class ArenaModeManager {

	private ArenaModeManager() {}
	
	private static ArenaModeManager instance = new ArenaModeManager();
	
	public static ArenaModeManager getInstance() {
		return instance;
	}
	
	public void setup() {
		YamlConfiguration arenas = SettingsManager.getInstance().getArenas();
		
		for (Arena arena : ArenaManager.getInstance().getArenas()) {
			if (!arenas.contains(arena.getName() + ".mode")) {
				arenas.set(arena.getName() + ".mode", "SOLO");
			}
			
			arena.setMode(ArenaMode.valueOf(arenas.getString(arena.getName() + ".mode")));
			
			if (SettingsManager.getInstance().getConfig().getBoolean("debug")) {
				Main.getInstance().getLogger().info("Loaded gamemode for " + arena.getName());
			}
		}
	}
}
