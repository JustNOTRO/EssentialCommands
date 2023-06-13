package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.msg")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 2) {
            player.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/msg <player> <message>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        StringBuilder senderStringBuilder = new StringBuilder(MessageUtility.fixColor("&7From &3" + player.getName() + "&6 >> &7"));
        StringBuilder targetStringBuilder = new StringBuilder(MessageUtility.fixColor("&7To &3" + target.getName() + "&6 >> &7"));

        for (int i = 1; i < args.length; i++) {
            senderStringBuilder.append(args[i]).append(" ");
            targetStringBuilder.append(args[i]).append(" ");
        }

        target.sendMessage(senderStringBuilder.toString());
        player.sendMessage(targetStringBuilder.toString());
        return true;
    }
}