package me.notro.essentialcommands.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;

@RequiredArgsConstructor
public enum MessageUtility {
    NO_PERMISSION("&cYou don't have permission to execute that command&7."),
    NO_SENDER_EXECUTOR("&cYou need to be a player to execute that command&7."),
    NO_ARGUMENTS_PROVIDED("&cNo arguments provided&7."),
    NO_PLAYER_EXISTENCE("&cPlayer does not Exist/Online&7.");

    @Getter
    private final String defaultMessage;

    public static String fixColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}