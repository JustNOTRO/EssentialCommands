package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;

public class PlayerPreLoginListener implements Listener {

    @EventHandler
    public void onPlayerPreLoginBan(AsyncPlayerPreLoginEvent event) {
        ConfigurationSection punishmentSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("punishments.ban");

        String player = event.getName();
        String message = punishmentSection.getString("message");

        if (Bukkit.getServer().getBanList(BanList.Type.NAME).isBanned(player)) event.disallow(PlayerPreLoginEvent.Result.KICK_BANNED, message);
    }
}
