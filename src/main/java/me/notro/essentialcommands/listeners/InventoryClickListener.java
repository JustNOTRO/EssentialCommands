package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.utils.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onPlayerInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (player.getOpenInventory().getTitle().equalsIgnoreCase(Message.fixColor("&cParticle Wizard"))) event.setCancelled(true);

        if (!player.getOpenInventory().getTitle().equalsIgnoreCase(Message.fixColor("&cParticle Wizard"))) return;

    }
}