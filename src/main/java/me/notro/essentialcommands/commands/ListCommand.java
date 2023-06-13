package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.list")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length > 0) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/list"));
            return false;
        }

        Bukkit.getOnlinePlayers().forEach(players -> sender.sendMessage(MessageUtility.fixColor("&bOnline Player(s)&7: &3" + players.getName() + "&7.")));
        return true;
    }
}
