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

public class TimeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.time")) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "day" -> {
                long dayTime = 1000;
                player.getWorld().setTime(dayTime);
                player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
                player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &bTime has been set to &3Day&b."));
            }
            case "night" -> {
                long nightTime = 14000;
                player.getWorld().setTime(nightTime);
                player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
                player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &bTime has been set to &3Night&b."));
            }
            default -> {
                player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &cUnknown day time. Please try again."));
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> dayTime = new ArrayList<>();
            dayTime.add("day");
            dayTime.add("night");
            return dayTime;
        }
        return null;
    }
}
