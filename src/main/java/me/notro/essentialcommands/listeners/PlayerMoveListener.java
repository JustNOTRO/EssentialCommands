package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection freezeSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("freeze");
        if (freezeSection.getStringList("players-freezed").contains(player.getName())) event.setCancelled(true);
    }
}
