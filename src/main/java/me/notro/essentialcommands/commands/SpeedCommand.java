package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.speed")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        int speed;

        try {
            speed = Integer.parseInt(args[0]);

        } catch (NumberFormatException e) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cInvalid speed level&7."));
            return false;
        }

        if (speed > 10.0F || speed < 0.0F) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cInvalid speed level&7."));
            return false;
        }

        if (player.isFlying()) {
            player.setFlySpeed((float) speed / 10);
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &bFly speed has been set to &3" + speed + "&7."));
            return true;
        }

        player.setWalkSpeed((float) speed / 10);
        player.sendMessage(MessageUtility.fixColor("&7(Silent) &bWalk speed has been set to &3" + speed + "&7."));
        return true;
    }
}
