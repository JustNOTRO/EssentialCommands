package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TimeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.time")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "day" -> {
                long dayTime = 1000;
                player.getWorld().setTime(dayTime);
                player.sendMessage(MessageUtility.fixColor("&7(Silent) &bTime has been set to &3Day&7."));
            }

            case "night" -> {
                long nightTime = 14000;
                player.getWorld().setTime(nightTime);
                player.sendMessage(MessageUtility.fixColor("&7(Silent) &bTime has been set to &3Night&7."));
            }

            default -> player.sendMessage(MessageUtility.fixColor("&7(Silent) &cUnknown day time please try again&7."));
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
