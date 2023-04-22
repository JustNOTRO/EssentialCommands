package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.systems.GodMode;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.god")) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (!GodMode.godModePlayers.contains(player.getUniqueId())) {
            GodMode.godModePlayers.add(player.getUniqueId());
            player.setInvulnerable(true);
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
            player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &bGod mode is now &aOn&b."));
            return true;
        }
        GodMode.godModePlayers.remove(player.getUniqueId());
        player.setInvulnerable(false);
        player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
        player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &bGod mode is now &cOff&b."));
        return true;
    }
}
