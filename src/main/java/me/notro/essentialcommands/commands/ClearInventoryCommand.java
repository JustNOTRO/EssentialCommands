package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearInventoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.clear")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (player.getInventory().isEmpty()) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cAll items has already been cleared&7."));
            return false;
        }

        player.getInventory().forEach(itemStack -> {
            if (player.getInventory().containsAtLeast(itemStack, 1)) {
                player.getInventory().clear();
                player.sendMessage(MessageUtility.fixColor("&7(Silent) &bSuccessfully cleared all the items from the inventory&7."));
            }
        });
        return true;
    }
}
