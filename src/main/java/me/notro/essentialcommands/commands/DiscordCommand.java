package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage());
            return false;
        }

        if (args.length > 0) {
            player.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/discord"));
            return false;
        }

        player.sendMessage(MessageUtility.fixColor("&bDiscord&7: https://discord.gg/HFawq8ZNGp"));
        return true;
    }
}
