package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffHelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.staff.help")) {
            sender.sendMessage(MessageUtility.NO_PERMISSION.getDefaultMessage());
            return false;
        }

        if (args.length < 1) {
            player.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/staffhelp <reason>"));
            return false;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < args.length; i++) stringBuilder.append(args[i]).append(" ");

        if (player.hasPermission("essentials.staff.notify")) player.sendMessage(MessageUtility.fixColor("&7(Silent) &3" + player.getName() + " &bneeds help with &3" + stringBuilder));

        player.sendMessage(MessageUtility.fixColor("&aYour request for help has been sent to an online staff&7."));
        return true;
    }
}
