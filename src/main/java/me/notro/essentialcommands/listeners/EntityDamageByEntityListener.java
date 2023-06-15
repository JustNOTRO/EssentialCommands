package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onPlayerTogglePvP(EntityDamageByEntityEvent event) {
        ConfigurationSection pvpSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("pvp-mode");

        if (!(event.getEntity() instanceof Player)) return;
        if (!pvpSection.getBoolean("state")) event.setCancelled(true);
    }
}
