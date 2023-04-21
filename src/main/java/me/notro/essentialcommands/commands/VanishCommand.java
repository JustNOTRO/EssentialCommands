package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;


public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.vanish")) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (!EssentialCommands.Vanish.getVanishedPlayers().contains(player.getUniqueId())) {
            Bukkit.getOnlinePlayers().forEach(player1 -> {
                EssentialCommands.Vanish.getVanishedPlayers().add(player.getUniqueId());
                player.hidePlayer(player1);
                player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
                player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &bYou are now &3invisible &bto all players."));
            });
            return true;
        }
        Bukkit.getOnlinePlayers().forEach(player1 -> {
            EssentialCommands.Vanish.getVanishedPlayers().remove(player.getUniqueId());
            player.showPlayer(player1);
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
            player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &bYou are now &3visible &bto all players."));
        });
        return true;
    }
}
