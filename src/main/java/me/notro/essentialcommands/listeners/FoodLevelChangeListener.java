package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.systems.GodMode;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener implements Listener {

    @EventHandler
    public void onPlayerHungerDeplete(FoodLevelChangeEvent event) {
        Entity entity = event.getEntity();

        if (GodMode.godModePlayers.contains(entity.getUniqueId())) {
            event.getEntity().setFoodLevel(20);
            event.setCancelled(true);
        }
    }
}
