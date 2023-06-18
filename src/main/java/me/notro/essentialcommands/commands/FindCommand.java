package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FindCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.find")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/find <player>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }


        sender.sendMessage(MessageUtility.fixColor("&bPlayer&7: &3" + target.getName() + "\n &bWorld&7: &3" + target.getWorld().getName()));
        sender.sendMessage(MessageUtility.fixColor("&bLocation&7: \n &bX&7: &3" + target.getLocation().getX() + "&7.\n &bY&7: &3" + target.getLocation().getY() + "&7.\n &bZ&7: &3" + target.getLocation().getZ() + "&7."));
        return true;
    }
}
