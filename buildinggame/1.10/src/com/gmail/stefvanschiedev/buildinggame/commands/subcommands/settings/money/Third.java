package com.gmail.stefvanschiedev.buildinggame.commands.subcommands.settings.money;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import com.gmail.stefvanschiedev.buildinggame.commands.commandutils.CommandResult;
import com.gmail.stefvanschiedev.buildinggame.commands.commandutils.ConsoleCommand;
import com.gmail.stefvanschiedev.buildinggame.managers.files.SettingsManager;
import com.gmail.stefvanschiedev.buildinggame.managers.messages.MessageManager;

public class Third extends ConsoleCommand {

	@Override
	public CommandResult onCommand(CommandSender sender, String[] args) {
		YamlConfiguration config = SettingsManager.getInstance().getConfig();
		
		if (args.length == 0) {
			MessageManager.getInstance().send(sender, ChatColor.RED + "Please specify the money");
			return CommandResult.ARGUMENTEXCEPTION;
		}
		
		try {
			Double.parseDouble(args[0]);
		} catch (NumberFormatException nfe) {
			MessageManager.getInstance().send(sender, ChatColor.RED + "That's not a valid number");
			return CommandResult.ERROR;
		}
		
		double money = Double.parseDouble(args[0]);
		
		config.set("money.third", money);
		SettingsManager.getInstance().save();
		
		MessageManager.getInstance().send(sender, ChatColor.GREEN + "Money setting set to " + money);
		
		return CommandResult.SUCCES;
	}

	@Override
	public String getName() {
		return "third";
	}

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public String getInfo() {
		return "Set the money";
	}

	@Override
	public String getPermission() {
		return "bg.setting.money.third";
	}

}
