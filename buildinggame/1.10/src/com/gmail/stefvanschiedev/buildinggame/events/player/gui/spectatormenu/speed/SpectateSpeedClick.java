package com.gmail.stefvanschiedev.buildinggame.events.player.gui.spectatormenu.speed;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gmail.stefvanschiedev.buildinggame.managers.files.SettingsManager;
import com.gmail.stefvanschiedev.buildinggame.managers.messages.MessageManager;
import com.gmail.stefvanschiedev.buildinggame.utils.guis.buildmenu.SpeedMenu;

public class SpectateSpeedClick implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		YamlConfiguration messages = SettingsManager.getInstance().getMessages();
		
		Player player = (Player) e.getWhoClicked();
		Inventory inventory = e.getInventory();
		
		if (!inventory.getName().equals(MessageManager.translate(messages.getString("spectator-gui.title"))))
			return;
		
		if (e.getCurrentItem() == null)
			return;
		
		ItemStack currentItem = e.getCurrentItem();
		
		if (currentItem.getType() != Material.FEATHER)
			return;
		
		if (!currentItem.hasItemMeta())
			return;
			
		if (!currentItem.getItemMeta().getDisplayName().equals(MessageManager.translate(messages.getString("spectator-gui.fly-speed.name"))))
			return;
		
		new SpeedMenu().show(player);
		e.setCancelled(true);
	}
}