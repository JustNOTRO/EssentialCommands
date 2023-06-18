package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HealAllCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.healall")) {
            sender.sendMessage(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage());
            return false;
        }

        if (args.length > 0) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/healall"));
            return false;
        }

        Bukkit.getOnlinePlayers().forEach(players -> players.setHealth(20.0D));
        sender.sendMessage(MessageUtility.fixColor("&7(Silent) &bSet full health to all &3online players&7."));
        return true;
    }
}
