package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onPlayerGodMode(EntityDamageEvent event) {
        ConfigurationSection godModeSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("god-mode");

        if (!(event.getEntity() instanceof Player player)) return;
        if (godModeSection.getStringList("players").contains(player.getUniqueId().toString())) event.setCancelled(true);
    }
}
