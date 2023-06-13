package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TphereCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.tphere")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 1) {
            player.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/tphere <player>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        target.teleport(player);
        player.sendMessage(MessageUtility.fixColor("&7(Silent) &bTeleported &3" + target.getName() + " &bto &3" + player.getName()));
        return true;
    }
}
