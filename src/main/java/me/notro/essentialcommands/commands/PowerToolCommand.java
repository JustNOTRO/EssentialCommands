package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PowerToolCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

         if (!(sender instanceof Player player)) {
             sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
             return false;
         }

         if (!player.hasPermission("essentials.powertool")) {
             player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
             return false;
         }

        if (args.length < 2) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/powertool <command>"));
            return false;
        }

        return true;
    }
}
