package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class SpeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.speed")) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        int speed;

        try {
            speed = Integer.parseInt(args[0]);

        } catch (NumberFormatException e) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &cInvalid speed level."));
            return false;
        }

        if (speed > 10.0F || speed < 0.0F) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &cInvalid speed level."));
            return false;
        }

        if (player.isFlying()) {
            player.setFlySpeed((float) speed / 10);
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
            player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &bFly speed has been set to &3" + speed + "&b."));
            return true;
        }
        player.setWalkSpeed((float) speed / 10);
        player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
        player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &bWalk speed has been set to &3" + speed + "&b."));
        return true;
    }
}
