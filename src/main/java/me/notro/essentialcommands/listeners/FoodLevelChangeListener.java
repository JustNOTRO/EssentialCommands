package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener implements Listener {

    @EventHandler
    public void onPlayerHungerDeplete(FoodLevelChangeEvent event) {
        ConfigurationSection godModeSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("god-mode");
        Entity entity = event.getEntity();

        if (!godModeSection.getStringList("players").contains(entity.getName())) return;
        event.getEntity().setFoodLevel(20);
        event.setCancelled(true);
    }
}