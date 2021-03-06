package com.gmail.stefvanschiedev.buildinggame.utils.gameplayer;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.stefvanschiedev.buildinggame.Main;
import com.gmail.stefvanschiedev.buildinggame.managers.files.SettingsManager;

public class TitleCountdown extends BukkitRunnable {

	private GamePlayer gamePlayer;
	
	public TitleCountdown(GamePlayer gamePlayer) {
		this.gamePlayer = gamePlayer;
	}
	
	@Override
	public void run() {	
		if (gamePlayer.getTitles().size() > 0) {
			YamlConfiguration config = SettingsManager.getInstance().getConfig();
			
			gamePlayer.sendTitle(gamePlayer.getTitles().get(0));	
			gamePlayer.setTitleCountdown(new TitleCountdown(gamePlayer));
			gamePlayer.getTitleCountdown().runTaskLater(Main.getInstance(), config.getInt("title.fade_in") + config.getInt("title.stay") + config.getInt("title.fade_out"));
		} else
			gamePlayer.setTitleCountdown(null);
		
		if (gamePlayer.getTitles().size() > 0)
			gamePlayer.getTitles().remove(0);
		
	}
}
