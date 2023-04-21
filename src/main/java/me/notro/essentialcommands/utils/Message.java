package me.notro.essentialcommands.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@AllArgsConstructor
public enum Message {
    
    NO_PERMISSION("&7[&b&lEssential Commands&7] &8>> &cYou don't have permission to execute that command."),
    NO_SENDER_EXECUTOR("&7[&b&lEssential Commands&7] &8>> &cYou need to be a player to execute that command."),
    NO_ARGUMENTS_PROVIDED("&7[&b&lEssential Commands&7] &8>> &cNo arguments provided."),
    NO_PLAYER_EXISTENCE("&7[&b&lEssential Commands&7] &8>> &cPlayer is not Exist/Online.");

    @Getter
    private final String defaultMessage;

    public static String fixColor(String message) {

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void playSound(CommandSender sender, Sound sound, float volume, float pitch) {
        if (!(sender instanceof Player player)) return;
        player.playSound(player.getLocation(), sound, volume, pitch);
    }

}
