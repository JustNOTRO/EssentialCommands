package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ClearChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.clearchat")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length > 0) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/clearchat"));
            return false;
        }

        for (int i = 0; i < 100; i++) Bukkit.getOnlinePlayers().forEach(players -> players.sendMessage(" "));

        Bukkit.broadcastMessage(MessageUtility.fixColor("&c&lChat History has been cleared&7."));
        return true;
    }
}
