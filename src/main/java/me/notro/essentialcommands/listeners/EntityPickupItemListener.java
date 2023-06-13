package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class EntityPickupItemListener implements Listener {

    @EventHandler
    public void onPlayerPickupItems(EntityPickupItemEvent event) {
        ConfigurationSection vanishSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("vanish");
        if (!(event.getEntity() instanceof Player)) return;
        if (!vanishSection.getBoolean("pickup-items")) event.setCancelled(true);
    }
}