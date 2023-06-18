package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.feed")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length > 0) {
            player.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/feed"));
            return false;
        }

        if (player.getFoodLevel() == 20.0) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cYou are already full hunger level&7."));
            return false;
        }

        player.setFoodLevel(20);
        player.sendMessage(MessageUtility.fixColor("&7(Silent) &bYour food level has been raised&7."));
        return true;
    }
}
