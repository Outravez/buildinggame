package com.gmail.stefvanschiedev.buildinggame.timers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.gmail.stefvanschiedev.buildinggame.managers.files.SettingsManager;
import com.gmail.stefvanschiedev.buildinggame.managers.messages.MessageManager;
import com.gmail.stefvanschiedev.buildinggame.timers.utils.Timer;
import com.gmail.stefvanschiedev.buildinggame.utils.arena.Arena;
import com.gmail.stefvanschiedev.buildinggame.utils.gameplayer.GamePlayer;
import com.gmail.stefvanschiedev.buildinggame.utils.plot.Plot;

public class WaitTimer extends Timer {

	private int seconds;
	private Arena arena;
	private boolean running = false;
	
	private YamlConfiguration config = SettingsManager.getInstance().getConfig();
	private YamlConfiguration messages = SettingsManager.getInstance().getMessages();
	
	public WaitTimer(int seconds, Arena arena) {
		this.seconds = seconds;
		this.arena = arena;
	}
	
	@Override
	public void run() {
		running = true;
		if (seconds <= 0) {
			arena.start();
			running = false;
			this.cancel();
			return;
		} else if (seconds % 15 == 0 || (seconds <= 10 && seconds >= 1)) {
			for (Plot plot : arena.getUsedPlots()) {
				for (GamePlayer gamePlayer : plot.getGamePlayers()) {
					Player player = gamePlayer.getPlayer();
					for (String message : messages.getStringList("lobbyCountdown.message")) {
						MessageManager.getInstance().send(player, message
								.replace("%seconds%", seconds + "")
								.replace("%minutes%", getMinutes() + "")
								.replace("%time%", getMinutes() + ":" + getSecondsFromMinute())
								.replace("%seconds_from_minute%", getSecondsFromMinute() + ""));
					}
				}
			}
		}
		for (Plot plot : arena.getUsedPlots()) {
			for (GamePlayer gamePlayer : plot.getGamePlayers()) {
				Player player = gamePlayer.getPlayer();
				
				player.setLevel(seconds);
			}
		}
		
		//timings
		try {
			for (String key : config.getConfigurationSection("timings.lobby-timer.at").getKeys(false)) {
				try {
					if (seconds == Integer.parseInt(key)) {
						for (String command : config.getStringList("timings.lobby-timer.at." + Integer.parseInt(key)))
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%arena%", arena.getName()));
					}
				} catch (NumberFormatException e) {}
			}
			for (String key : config.getConfigurationSection("timings.lobby-timer.every").getKeys(false)) {
				try {
					if (seconds % Integer.parseInt(key) == 0) {
						for (String command : config.getStringList("timings.lobby-timer.every." + Integer.parseInt(key)))
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%arena%", arena.getName()));
					}
				} catch (NumberFormatException e) {}
			}
		} catch (NullPointerException e) {}
		seconds--;
	}
	
	@Override
	public int getSeconds() {
		return seconds;
	}
	
	@Override
	public boolean isActive() {
		return running;
	}
	
	@Override
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
}