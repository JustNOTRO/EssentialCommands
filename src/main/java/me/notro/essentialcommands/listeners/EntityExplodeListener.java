package me.notro.essentialcommands.listeners;

import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplodeListener implements Listener {

    @EventHandler
    public void onBlockExplode(EntityExplodeEvent event) {

        if (!(event.getEntity() instanceof TNTPrimed tntPrimed)) return;
        if (tntPrimed.hasMetadata("primedtnt")) return;

        event.setCancelled(true);
    }
}