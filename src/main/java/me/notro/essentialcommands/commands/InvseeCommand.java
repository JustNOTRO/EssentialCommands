package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.invsee")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 1) {
            player.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/invsee <player>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        player.openInventory(target.getInventory());
        player.sendMessage(MessageUtility.fixColor("&7(Silent) &bOpened &3" + target.getName() + "&b's inventory&7."));
        return true;
    }
}
