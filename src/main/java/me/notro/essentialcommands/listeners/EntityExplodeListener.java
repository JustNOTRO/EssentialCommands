package me.notro.essentialcommands.listeners;

import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplodeListener implements Listener {

    @EventHandler
    public void onBlockExplode(EntityExplodeEvent event) {
        TNTPrimed tntPrimed = (TNTPrimed) event.getEntity();
        if (!tntPrimed.hasMetadata("tntprimed")) return;
        event.setCancelled(true);
    }
}