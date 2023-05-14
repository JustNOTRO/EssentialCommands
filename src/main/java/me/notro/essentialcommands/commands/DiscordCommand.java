package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Message.NO_SENDER_EXECUTOR.getDefaultMessage());
            return false;
        }

        if (args.length > 0) {
            player.sendMessage(Message.fixColor("&cUsage&7: &b/discord"));
            return false;
        }
        player.sendMessage(Message.fixColor("&bDiscord: https://discord.gg/HFawq8ZNGp"));
        return true;
    }
}
