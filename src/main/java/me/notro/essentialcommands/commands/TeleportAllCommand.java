package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportAllCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.tpall")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length > 0) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&3: &7/tpall"));
            return false;
        }

        Bukkit.getOnlinePlayers().forEach(onlinePlayers -> {
            onlinePlayers.teleport(player.getLocation());
            player.sendMessage(MessageUtility.fixColor("&7[&b&lEssential Commands&7] &8>> &bTeleported everyone to &3" + player.getName() + "&b."));
            return;
        });
        return true;
    }
}
