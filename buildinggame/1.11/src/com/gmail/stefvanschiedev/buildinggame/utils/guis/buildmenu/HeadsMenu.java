package com.gmail.stefvanschiedev.buildinggame.utils.guis.buildmenu;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.gmail.stefvanschiedev.buildinggame.managers.files.SettingsManager;
import com.gmail.stefvanschiedev.buildinggame.managers.messages.MessageManager;
import com.gmail.stefvanschiedev.buildinggame.utils.guis.Gui;
import com.gmail.stefvanschiedev.buildinggame.utils.guis.buildmenu.headsmenu.AlphabetHeadsMenu;
import com.gmail.stefvanschiedev.buildinggame.utils.guis.buildmenu.headsmenu.BlocksHeadsMenu;
import com.gmail.stefvanschiedev.buildinggame.utils.guis.buildmenu.headsmenu.CharactersHeadsMenu;
import com.gmail.stefvanschiedev.buildinggame.utils.guis.buildmenu.headsmenu.ColorsHeadsMenu;
import com.gmail.stefvanschiedev.buildinggame.utils.guis.buildmenu.headsmenu.DevicesHeadsMenu;
import com.gmail.stefvanschiedev.buildinggame.utils.guis.buildmenu.headsmenu.FoodHeadsMenu;
import com.gmail.stefvanschiedev.buildinggame.utils.guis.buildmenu.headsmenu.GamesHeadsMenu;
import com.gmail.stefvanschiedev.buildinggame.utils.guis.buildmenu.headsmenu.InteriorHeadsMenu;
import com.gmail.stefvanschiedev.buildinggame.utils.guis.buildmenu.headsmenu.MiscHeadsMenu;
import com.gmail.stefvanschiedev.buildinggame.utils.guis.buildmenu.headsmenu.MobsHeadsMenu;
import com.gmail.stefvanschiedev.buildinggame.utils.guis.buildmenu.headsmenu.PokemonHeadsMenu;
import com.gmail.stefvanschiedev.buildinggame.utils.nbt.item.skull.SkullItem;

public class HeadsMenu extends Gui {

	private static YamlConfiguration messages = SettingsManager.getInstance().getMessages();
	
	private FoodHeadsMenu foodMenu;
	private DevicesHeadsMenu devicesMenu;
	private MiscHeadsMenu miscMenu;
	private AlphabetHeadsMenu alphabetMenu;
	private InteriorHeadsMenu interiorMenu;
	private ColorsHeadsMenu colorsMenu;
	private BlocksHeadsMenu blocksMenu;
	private MobsHeadsMenu mobsMenu;
	private GamesHeadsMenu gamesMenu;
	private CharactersHeadsMenu charactersMenu;
	private PokemonHeadsMenu pokemonMenu;
	
	public HeadsMenu() {
		super(null, 45, MessageManager.translate(messages.getString("gui.heads.title")), 1);
		
		foodMenu = new FoodHeadsMenu();
		devicesMenu = new DevicesHeadsMenu();
		miscMenu = new MiscHeadsMenu();
		alphabetMenu = new AlphabetHeadsMenu();
		interiorMenu = new InteriorHeadsMenu();
		colorsMenu = new ColorsHeadsMenu();
		blocksMenu = new BlocksHeadsMenu();
		mobsMenu = new MobsHeadsMenu();
		gamesMenu = new GamesHeadsMenu();
		charactersMenu = new CharactersHeadsMenu();
		pokemonMenu = new PokemonHeadsMenu();
		
		ItemStack food = SkullItem.getSkull("http://textures.minecraft.net/texture/edf410fdf212964a5606ca0a1b47730922775ca7f9763e5aea9a3ab449b6ec");
		ItemMeta foodMeta = food.getItemMeta();
		foodMeta.setDisplayName(MessageManager.translate(messages.getString("gui.heads.food.name")));
		foodMeta.setLore(MessageManager.translate(messages.getStringList("gui.heads.food.lores")));
		food.setItemMeta(foodMeta);
		
		setItem(food, new GuiAction() {
			@Override
			public boolean actionPerformed(GuiActionType type, InventoryEvent e) {
				if (type != GuiActionType.CLICK)
					return false;
				
				InventoryClickEvent event = (InventoryClickEvent) e;
				foodMenu.open((Player) event.getWhoClicked());
				return true;
			}
		}, 10);

		ItemStack devices = SkullItem.getSkull("http://textures.minecraft.net/texture/15c292a24f54a7a43785266552dba7a184f9c50e0d94b337d8d3e76e9e9cce7");
		ItemMeta devicesMeta = devices.getItemMeta();
		devicesMeta.setDisplayName(MessageManager.translate(messages.getString("gui.heads.devices.name")));
		devicesMeta.setLore(MessageManager.translate(messages.getStringList("gui.heads.devices.lores")));
		devices.setItemMeta(devicesMeta);
		
		setItem(devices, new GuiAction() {
			@Override
			public boolean actionPerformed(GuiActionType type, InventoryEvent e) {
				if (type != GuiActionType.CLICK)
					return false;
				
				InventoryClickEvent event = (InventoryClickEvent) e;
				devicesMenu.open((Player) event.getWhoClicked());
				return true;
			}
		}, 12);
		
		ItemStack misc = SkullItem.getSkull("http://textures.minecraft.net/texture/86793bac4a1f974142ef8916642710949d7e38f87aebd380742ccc374f1de1");
		ItemMeta miscMeta = misc.getItemMeta();
		miscMeta.setDisplayName(MessageManager.translate(messages.getString("gui.heads.misc.name")));
		miscMeta.setLore(MessageManager.translate(messages.getStringList("gui.heads.misc.lores")));
		misc.setItemMeta(miscMeta);
		
		setItem(misc, new GuiAction() {
			@Override
			public boolean actionPerformed(GuiActionType type, InventoryEvent e) {
				if (type != GuiActionType.CLICK)
					return false;
				
				InventoryClickEvent event = (InventoryClickEvent) e;
				miscMenu.open((Player) event.getWhoClicked());
				return true;
			}
		}, 14);
		
		ItemStack alphabet = SkullItem.getSkull("http://textures.minecraft.net/texture/acb419d984d8796373c9646233c7a02664bd2ce3a1d3476dd9b1c5463b14ebe");
		ItemMeta alphabetMeta = alphabet.getItemMeta();
		alphabetMeta.setDisplayName(MessageManager.translate(messages.getString("gui.heads.alphabet.name")));
		alphabetMeta.setLore(MessageManager.translate(messages.getStringList("gui.heads.alphabet.lores")));
		alphabet.setItemMeta(alphabetMeta);
		
		setItem(alphabet, new GuiAction() {
			@Override
			public boolean actionPerformed(GuiActionType type, InventoryEvent e) {
				if (type != GuiActionType.CLICK)
					return false;
				
				InventoryClickEvent event = (InventoryClickEvent) e;
				alphabetMenu.open((Player) event.getWhoClicked());
				return true;
			}
		}, 16);
		
		ItemStack interior = SkullItem.getSkull("http://textures.minecraft.net/texture/7acc61666adf1e4cd7cf57af3e1e17ba17310b2fcd8e3ed27cf88b7d0d88518");
		ItemMeta interiorMeta = interior.getItemMeta();
		interiorMeta.setDisplayName(MessageManager.translate(messages.getString("gui.heads.interior.name")));
		interiorMeta.setLore(MessageManager.translate(messages.getStringList("gui.heads.interior.lores")));
		interior.setItemMeta(interiorMeta);
		
		setItem(interior, new GuiAction() {
			@Override
			public boolean actionPerformed(GuiActionType type, InventoryEvent e) {
				if (type != GuiActionType.CLICK)
					return false;
				
				InventoryClickEvent event = (InventoryClickEvent) e;
				interiorMenu.open((Player) event.getWhoClicked());
				return true;
			}
		}, 20);
		
		ItemStack colors = SkullItem.getSkull("http://textures.minecraft.net/texture/d45b44fd19d72fb3d6e189c4978b1ca687dbd6580b18ddd8aa710edffa5");
		ItemMeta colorsMeta = colors.getItemMeta();
		colorsMeta.setDisplayName(MessageManager.translate(messages.getString("gui.heads.colors.name")));
		colorsMeta.setLore(MessageManager.translate(messages.getStringList("gui.heads.colors.lores")));
		colors.setItemMeta(colorsMeta);
		
		setItem(colors, new GuiAction() {
			@Override
			public boolean actionPerformed(GuiActionType type, InventoryEvent e) {
				if (type != GuiActionType.CLICK)
					return false;
				
				InventoryClickEvent event = (InventoryClickEvent) e;
				colorsMenu.open((Player) event.getWhoClicked());
				return true;
			}
		}, 22);
		
		ItemStack blocks = SkullItem.getSkull("http://textures.minecraft.net/texture/c60b2f9145215a3a5065dca2d89bb8b4ca44b9222dd22060b51c38d9bf587");
		ItemMeta blocksMeta = blocks.getItemMeta();
		blocksMeta.setDisplayName(MessageManager.translate(messages.getString("gui.heads.blocks.name")));
		blocksMeta.setLore(MessageManager.translate(messages.getStringList("gui.heads.blocks.lores")));
		blocks.setItemMeta(blocksMeta);
		
		setItem(blocks, new GuiAction() {
			@Override
			public boolean actionPerformed(GuiActionType type, InventoryEvent e) {
				if (type != GuiActionType.CLICK)
					return false;
				
				InventoryClickEvent event = (InventoryClickEvent) e;
				blocksMenu.open((Player) event.getWhoClicked());
				return true;
			}
		}, 24);
		
		ItemStack mobs = SkullItem.getSkull("http://textures.minecraft.net/texture/621668ef7cb79dd9c22ce3d1f3f4cb6e2559893b6df4a469514e667c16aa4");
		ItemMeta mobsMeta = mobs.getItemMeta();
		mobsMeta.setDisplayName(MessageManager.translate(messages.getString("gui.heads.mobs.name")));
		mobsMeta.setLore(MessageManager.translate(messages.getStringList("gui.heads.mobs.lores")));
		mobs.setItemMeta(mobsMeta);
		
		setItem(mobs, new GuiAction() {
			@Override
			public boolean actionPerformed(GuiActionType type, InventoryEvent e) {
				if (type != GuiActionType.CLICK)
					return false;
				
				InventoryClickEvent event = (InventoryClickEvent) e;
				mobsMenu.open((Player) event.getWhoClicked());
				return true;
			}
		}, 28);
		
		ItemStack games = SkullItem.getSkull("http://textures.minecraft.net/texture/6c82e21a9320953d78daee85477de3bb82d5dfa6b19494d37733265d2d030a8");
		ItemMeta gamesMeta = games.getItemMeta();
		gamesMeta.setDisplayName(MessageManager.translate(messages.getString("gui.heads.games.name")));
		gamesMeta.setLore(MessageManager.translate(messages.getStringList("gui.heads.games.lores")));
		games.setItemMeta(gamesMeta);
		
		setItem(games, new GuiAction() {
			@Override
			public boolean actionPerformed(GuiActionType type, InventoryEvent e) {
				if (type != GuiActionType.CLICK)
					return false;
				
				InventoryClickEvent event = (InventoryClickEvent) e;
				gamesMenu.open((Player) event.getWhoClicked());
				return true;
			}
		}, 30);
		
		ItemStack characters = SkullItem.getSkull("http://textures.minecraft.net/texture/71be29750ddec80994bda79653e21ed70d5b2eb793da51d5a87b89bf67dcb96");
		ItemMeta charactersMeta = characters.getItemMeta();
		charactersMeta.setDisplayName(MessageManager.translate(messages.getString("gui.heads.characters.name")));
		charactersMeta.setLore(MessageManager.translate(messages.getStringList("gui.heads.characters.lores")));
		characters.setItemMeta(charactersMeta);
		
		setItem(characters, new GuiAction() {
			@Override
			public boolean actionPerformed(GuiActionType type, InventoryEvent e) {
				if (type != GuiActionType.CLICK)
					return false;
				
				InventoryClickEvent event = (InventoryClickEvent) e;
				charactersMenu.open((Player) event.getWhoClicked());
				return true;
			}
		}, 32);
		
		ItemStack pokemon = SkullItem.getSkull("http://textures.minecraft.net/texture/222bc95af0557a5940462025f253e9494cfcc56c5ff405e18805d133a87efd2");
		ItemMeta pokemonMeta = pokemon.getItemMeta();
		pokemonMeta.setDisplayName(MessageManager.translate(messages.getString("gui.heads.pokemon.name")));
		pokemonMeta.setLore(MessageManager.translate(messages.getStringList("gui.heads.pokemon.lores")));
		pokemon.setItemMeta(pokemonMeta);
		
		setItem(pokemon, new GuiAction() {
			@Override
			public boolean actionPerformed(GuiActionType type, InventoryEvent e) {
				if (type != GuiActionType.CLICK)
					return false;
				
				InventoryClickEvent event = (InventoryClickEvent) e;
				pokemonMenu.open((Player) event.getWhoClicked());
				return true;
			}
		}, 34);
	}
}