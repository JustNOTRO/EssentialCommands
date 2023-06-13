package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.staffchat")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        StringBuilder message = new StringBuilder(MessageUtility.fixColor("&7(Silent) &7[&3Staff Chat&7] &3" + player.getName() + "&b: "));

        for (String i : args) message.append(i).append(" ");

        String finalMessage = message.toString();
        Bukkit.getOnlinePlayers().forEach(players -> {
            if (players.hasPermission("essentials.staffchat")) player.sendMessage(finalMessage);
        });
        return true;
    }
}
