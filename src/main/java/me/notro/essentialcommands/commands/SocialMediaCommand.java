package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SocialMediaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.socialmedia")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length > 0) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/socialmedia"));
            return false;
        }

        sender.sendMessage(MessageUtility.fixColor("&b&lGitHub: github.com/JustNOTRO/EssentialCommands"));
        sender.sendMessage(MessageUtility.fixColor("&b&lDiscord: N0TR0#6999"));
        sender.sendMessage(MessageUtility.fixColor("&3&lPlugin Author: Notro&7."));
        return true;
    }
}
