package me.stefvanschie.buildinggame.utils;

import java.lang.reflect.Constructor;

import me.stefvanschie.buildinggame.managers.files.SettingsManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GamePlayer {

	private float exp;
	private float flySpeed;
	private GameMode gameMode;
	private int levels;
	private int blocksPlaced = 0;
	private Player player;
	private ItemStack[] inventory;
	private ItemStack[] armor;
	
	public GamePlayer(Player player) {
		exp = player.getExp();
		flySpeed = player.getFlySpeed();
		gameMode = player.getGameMode();
		levels = player.getLevel();
		this.player = player;
		inventory = player.getInventory().getContents();
		armor = player.getInventory().getArmorContents();
	}

	public ItemStack[] getArmor() {
		return armor;
	}
	
	public int getBlocksPlaced() {
		return blocksPlaced;
	}
	
	public float getExp() {
		return exp;
	}
	
	public float getFlySpeed() {
		return flySpeed;
	}
	
	public GameMode getGameMode() {
		return gameMode;
	}
	
	public ItemStack[] getInventory() {
		return inventory;
	}
	
	public int getLevels() {
		return levels;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void restore() {
		player.getInventory().setArmorContents(armor);
		setBlocksPlaced(0);
		player.setExp(exp);
		player.setFlySpeed(flySpeed);
		player.setGameMode(gameMode);
		player.getInventory().setContents(inventory);
		player.setLevel(levels);
	}
	
	public void sendSubtitle(String subtitle) {
		YamlConfiguration config = SettingsManager.getInstance().getConfig();
		
		try {
			Constructor<?> constructor = getNMSClass("PacketPlayOutTitle")
					.getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0],
							getNMSClass("IChatBaseComponent"),
							int.class,
							int.class,
							int.class);
			
			Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
			Object chatSerializer = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
					.getMethod("a", String.class).invoke(null, ChatColor.translateAlternateColorCodes('&', "{\"text\":\"" + subtitle + "\"}"));
			
			Object packet = constructor.newInstance(enumTitle, chatSerializer, config.getInt("title.fade_in"), config.getInt("title.stay"), config.getInt("title.fade_out"));
			sendPacket(packet);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	public void sendTitle(String title) {
		YamlConfiguration config = SettingsManager.getInstance().getConfig();
		
		try {
			Constructor<?> constructor = getNMSClass("PacketPlayOutTitle")
					.getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0],
							getNMSClass("IChatBaseComponent"),
							int.class,
							int.class,
							int.class);
			
			Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
			Object chatSerializer = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
					.getMethod("a", String.class).invoke(null, ChatColor.translateAlternateColorCodes('&', "{\"text\":\"" + title + "\"}"));
			
			Object packet = constructor.newInstance(enumTitle, chatSerializer, config.getInt("title.fade_in"), config.getInt("title.stay"), config.getInt("title.fade_out"));
			sendPacket(packet);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	public void setArmor(ItemStack[] armor) {
		this.armor = armor;
	}
	
	public void setBlocksPlaced(int blocksPlaced) {
		this.blocksPlaced = blocksPlaced;
	}
	
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	public void setFlySpeed(float flySpeed) {
		this.flySpeed = flySpeed;
	}
	
	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}
	
	public void setInventory(ItemStack[] inventory) {
		this.inventory = inventory;
	}
	
	public void setLevels(int levels) {
		this.levels = levels;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	private void sendPacket(Object packet) {
		try {
			Object handle = getPlayer().getClass().getMethod("getHandle").invoke(getPlayer());
			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
			playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Class<?> getNMSClass(String name) {
		try {
			return Class.forName("net.minecraft.server." + getVersion() + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String getVersion() {
		return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	}
}