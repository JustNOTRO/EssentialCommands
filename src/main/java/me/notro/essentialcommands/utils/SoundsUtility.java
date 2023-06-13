package me.notro.essentialcommands.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SoundsUtility {

    public static void playSound(CommandSender sender, org.bukkit.Sound sound, float volume, float pitch) {
        if (!(sender instanceof Player player)) return;
        player.playSound(player.getLocation(), sound, volume, pitch);
    }
}
