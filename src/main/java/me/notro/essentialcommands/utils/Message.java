package me.notro.essentialcommands.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public enum Message {
    NO_PERMISSION("&cYou don't have permission to execute that command&7."),
    NO_SENDER_EXECUTOR("&cYou need to be a player to execute that command&7."),
    NO_ARGUMENTS_PROVIDED("&cNo arguments provided&7."),
    NO_PLAYER_EXISTENCE("&cPlayer is not Exist/Online&7.");

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
