package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class AsyncPlayerPreLoginListener implements Listener {

    @EventHandler
    public void onPlayerPreLoginBan(AsyncPlayerPreLoginEvent event) {
        ConfigurationSection punishmentSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("punishments.ban");
        String player = event.getName();
        String message = punishmentSection.getString("message");

        if (Bukkit.getServer().getBanList(BanList.Type.NAME).isBanned(player)) event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, message);
    }

    @EventHandler
    public void onPlayerPreLoginMaintenance(AsyncPlayerPreLoginEvent event) {
        ConfigurationSection maintenanceMode = EssentialCommands.getInstance().getConfig().getConfigurationSection("maintenance");
        boolean maintenanceState = maintenanceMode.getBoolean("state");

        if (maintenanceState) event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST, MessageUtility.fixColor("&cServer is currently on maintenance&7."));
    }
}