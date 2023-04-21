package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onPlayerGodMode(EntityDamageEvent event) {

        if (!(event.getEntity() instanceof Player player)) return;

        if (EssentialCommands.GodMode.getGodModePlayers().contains(player.getUniqueId())) event.setCancelled(true);
    }
}
