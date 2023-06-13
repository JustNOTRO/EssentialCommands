package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.broadcast")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if  (args.length < 1) {
            sender.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/broadcast <message>"));
            return false;
        }

        StringBuilder broadcastMessage = new StringBuilder(MessageUtility.fixColor("&c&lBroadcast: &7"));

        for (String i : args) broadcastMessage.append(ChatColor.translateAlternateColorCodes('&', i)).append(" ");

        sender.getServer().broadcastMessage(broadcastMessage.toString());
        return true;
    }
}
