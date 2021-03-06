package com.gmail.stefvanschiedev.buildinggame.utils.guis.buildmenu.headsmenu.mobs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gmail.stefvanschiedev.buildinggame.managers.files.SettingsManager;
import com.gmail.stefvanschiedev.buildinggame.managers.messages.MessageManager;
import com.gmail.stefvanschiedev.buildinggame.utils.nbt.item.NBTItem;
import com.gmail.stefvanschiedev.buildinggame.utils.nbt.item.skull.SkullItem;

public class MobsHeadsMenuTwo {
	
	public void show(Player player) {
		YamlConfiguration messages = SettingsManager.getInstance().getMessages();
		
		Inventory inventory = Bukkit.createInventory(null, 54, MessageManager.translate(messages.getString("gui.heads.mobs.page-2.title")));
		
		ItemStack ferret = SkullItem.getSkull("http://textures.minecraft.net/texture/236edf7de9adca72308a94d1c38c358acc82918fe8fced25d474820f4cb784");
		ItemMeta ferretMeta = ferret.getItemMeta();
		ferretMeta.setDisplayName(MessageManager.translate(messages.getString("gui.heads.mobs.page-2.ferret.name")));
		{
			List<String> lores = new ArrayList<String>();
			for (String lore : messages.getStringList("gui.heads.mobs.page-2.ferret.lores")) {
				lores.add(MessageManager.translate(lore));
			}
			ferretMeta.setLore(lores);
		}
		ferret.setItemMeta(ferretMeta);
		
		ItemStack elephant = SkullItem.getSkull("http://textures.minecraft.net/texture/7071a76f669db5ed6d32b48bb2dba55d5317d7f45225cb3267ec435cfa514");
		ItemMeta elephantMeta = elephant.getItemMeta();
		elephantMeta.setDisplayName(MessageManager.translate(messages.getString("gui.heads.mobs.page-2.elephant.name")));
		{
			List<String> lores = new ArrayList<String>();
			for (String lore : messages.getStringList("gui.heads.mobs.page-2.elephant.lores")) {
				lores.add(MessageManager.translate(lore));
			}
			elephantMeta.setLore(lores);
		}
		elephant.setItemMeta(elephantMeta);
		
		ItemStack furby = SkullItem.getSkull("http://textures.minecraft.net/texture/7bff527562889e16a544f2f996fba3d9541d0aacf81462bffc9fb5cad8aedd5");
		ItemMeta furbyMeta = furby.getItemMeta();
		furbyMeta.setDisplayName(MessageManager.translate(messages.getString("gui.heads.mobs.page-2.furby.name")));
		{
			List<String> lores = new ArrayList<String>();
			for (String lore : messages.getStringList("gui.heads.mobs.page-2.furby.lores")) {
				lores.add(MessageManager.translate(lore));
			}
			furbyMeta.setLore(lores);
		}
		furby.setItemMeta(furbyMeta);
		
		//previous page
		ItemStack previous = new ItemStack (Material.SUGAR_CANE);
		ItemMeta previousMeta = previous.getItemMeta();
		previousMeta.setDisplayName(MessageManager.translate(messages.getString("gui.heads.mobs.page-2.previous-page.name")));
		{
			List<String> lores = new ArrayList<String>();
			for (String lore : messages.getStringList("gui.heads.mobs.page-2.previous-page.lores")) {
				lores.add(MessageManager.translate(lore));
			}
			previousMeta.setLore(lores);
		}
		previous.setItemMeta(previousMeta);
		NBTItem previousNbt = new NBTItem(previous);
		previousNbt.setInteger("page", 1);
		previous = previousNbt.getItem();
		
		//close
		ItemStack close = new ItemStack(Material.BOOK);
		ItemMeta closeMeta = close.getItemMeta();
		closeMeta.setDisplayName(MessageManager.translate(messages.getString("gui.heads.mobs.page-2.close.name")));
		{
			List<String> lores = new ArrayList<String>();
			for (String lore : messages.getStringList("gui.heads.mobs.page-2.close.lores")) {
				lores.add(MessageManager.translate(lore));
			}
			closeMeta.setLore(lores);
		}
		close.setItemMeta(closeMeta);
		
		inventory.setItem(0, ferret);
		inventory.setItem(1, elephant);
		inventory.setItem(2, furby);
		
		inventory.setItem(47, previous);
		inventory.setItem(49, close);
		
		player.openInventory(inventory);
	}
}