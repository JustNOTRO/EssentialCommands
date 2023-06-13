package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.nick")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 1) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/nick <name>"));
            return false;
        }

        if (args[0].equalsIgnoreCase("reset")) {
            player.setDisplayName(null);
            player.setPlayerListName(null);
            player.sendMessage(MessageUtility.fixColor("&aNickname has been reset&7."));
            return false;
        }

        player.setDisplayName(ChatColor.translateAlternateColorCodes('&', args[0] + ChatColor.RESET));
        player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', args[0] + ChatColor.RESET));
        player.sendMessage(MessageUtility.fixColor("&aNickname has been set to: &r" + ChatColor.translateAlternateColorCodes('&', args[0]) + "&7."));
        return true;
    }
}
