package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WeatherCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.time.weather")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 1) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/weather <clear/rain>"));
            return false;
        }

        switch (args[0].toLowerCase()) {

            case "clear" -> {
                player.getWorld().setStorm(false);
                player.sendMessage(MessageUtility.fixColor("&7(Silent) &bSet weather to &3Clear&7."));
            }

            case "rain" -> {
                player.getWorld().setStorm(true);
                player.sendMessage(MessageUtility.fixColor("&7(Silent) &bSet weather to &3Rain&7."));
            }

            case "thunder" -> {
                player.getWorld().setStorm(true);
                player.getWorld().setThundering(true);
                player.sendMessage(MessageUtility.fixColor("&7(Silent) &bSet weather to &3Rain &band &3Thunder&7."));
            }

            default -> player.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/weather <clear/rain>"));
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
