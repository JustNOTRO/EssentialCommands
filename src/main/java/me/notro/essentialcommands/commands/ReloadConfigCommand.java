package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfigCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.config.reload")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&&: &b/config reload"));
            return false;
        }

        if (!args[0].equalsIgnoreCase("reload")) return false;

        EssentialCommands.getInstance().reloadConfig();
        sender.sendMessage(MessageUtility.fixColor("&aConfig has been reloaded&7."));
        return true;
    }
}
