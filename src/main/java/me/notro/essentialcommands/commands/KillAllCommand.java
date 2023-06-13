package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillAllCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.kill.all")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length > 0) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/killall"));
            return false;
        }

        Bukkit.getOnlinePlayers().forEach(onlinePlayers -> {
            onlinePlayers.setHealth(0.0D);
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &bKilled all &3players&7."));
        });
        return true;
    }
}