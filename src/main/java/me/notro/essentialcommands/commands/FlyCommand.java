package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.fly")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        String isEnabled = player.getAllowFlight() ? MessageUtility.fixColor("&3Disabled") : MessageUtility.fixColor("&3Enabled");
        player.setAllowFlight(!player.getAllowFlight());
        player.sendMessage(MessageUtility.fixColor("&7(Silent) &bFlight has been " + isEnabled + "&7."));
        return true;
    }
}
