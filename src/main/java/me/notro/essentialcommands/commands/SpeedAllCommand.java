package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpeedAllCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.speedall")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/speedall <level>"));
            return false;
        }

        int speed;

        try {
            speed = Integer.parseInt(args[0]);
        } catch (NumberFormatException exception) {
            sender.sendMessage(MessageUtility.fixColor("&7(Silent) &cInvalid speed level&7."));
            return false;
        }

        if (speed > 10.0F || speed < 0.0F) {
            sender.sendMessage(MessageUtility.fixColor("&7(Silent) &cInvalid speed level&7."));
            return false;
        }

        Bukkit.getOnlinePlayers()
                .stream()
                .filter(Player::isFlying)
                .forEach(player -> player.setFlySpeed((float) speed / 10));

        Bukkit.getOnlinePlayers().forEach(player -> player.setWalkSpeed((float) speed / 10));
        Bukkit.broadcastMessage(MessageUtility.fixColor("&3" + sender.getName() + " &bset everyone's speed level to &3" + speed + "&7."));
        return true;
    }
}
