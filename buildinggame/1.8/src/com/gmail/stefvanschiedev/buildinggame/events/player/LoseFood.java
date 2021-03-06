package com.gmail.stefvanschiedev.buildinggame.events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import com.gmail.stefvanschiedev.buildinggame.managers.arenas.ArenaManager;

public class LoseFood implements Listener {

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		
		Player player = (Player) e.getEntity();
		
		if (ArenaManager.getInstance().getArena(player) == null) {
			return;
		}
		
		e.setCancelled(true);
	}
}