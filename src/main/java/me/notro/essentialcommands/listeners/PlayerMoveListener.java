package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection freezeSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("freeze-mode");

        if (freezeSection.getStringList("players").contains(player.getName())) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerMoveInJail(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection jailSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("jail");
        List<String> jailedPlayers = jailSection.getStringList("players");

        if (player.hasPermission("essentials.jail.bypass")) return;
        if (jailedPlayers.contains(player.getUniqueId().toString())) event.setCancelled(true);
    }
}