package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WeatherCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.time.weather")) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        if (args.length > 1) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &cUsage&3: &7/weather <clear/rain>"));
            return false;
        }

        switch (args[0].toLowerCase()) {

            case "clear" -> {
                player.getWorld().setStorm(false);
                player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
                player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &bSet weather to &3Clear&b."));
            }
            case "rain" -> {
                player.getWorld().setStorm(true);
                player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
                player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &bSet weather to &3Rain&b."));
            }
            case "thunder" -> {
                player.getWorld().setStorm(true);
                player.getWorld().setThundering(true);
                player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
                player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &bSet weather to &3Rain &band &3Thunder&b."));
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> weatherStatus = new ArrayList<>();
            weatherStatus.add("clear");
            weatherStatus.add("rain");
            weatherStatus.add("thunder");
            return weatherStatus;
        }
        return null;
    }
}
