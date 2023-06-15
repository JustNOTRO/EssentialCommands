package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GravityCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.gravity")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/gravity <player>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        if (target.hasGravity()) {
            target.setGravity(false);
            sender.sendMessage(MessageUtility.fixColor("&7(Silent) &bGravity has been set to false to &3" + target.getName() + "&7."));
            return true;
        }

        target.setGravity(true);
        sender.sendMessage(MessageUtility.fixColor("&7(Silent) &bGravity has been set to true to &3" + target.getName() + "&7."));
        return true;
    }
}
