package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class KickallCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.kickall")) {
            sender.sendMessage(MessageUtility.NO_PERMISSION.getDefaultMessage());
            return false;
        }

        if (args.length > 0) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/kickall"));
            return false;
        }

        Bukkit.getOnlinePlayers()
                .stream()
                .filter(players -> players == sender).skip(1)
                .forEach(players -> players.kickPlayer(null));

        sender.sendMessage(MessageUtility.fixColor("&7(Silent) &bSuccessfully kicked all players&7."));
        return true;
    }
}
