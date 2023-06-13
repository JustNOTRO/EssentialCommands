package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.top")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length > 0) {
            player.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/top"));
            return false;
        }

        int blockX = player.getLocation().getBlockX();
        int blockY = player.getWorld().getLogicalHeight();
        int blockZ = player.getLocation().getBlockZ();

        Location topLocation = new Location(player.getWorld(), blockX, blockY, blockZ);

        player.teleport(topLocation);
        return true;
    }
}
