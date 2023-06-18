package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onPlayerInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (!player.getOpenInventory().getTitle().equalsIgnoreCase(MessageUtility.fixColor("&cParticle Wizard"))) return;

        event.setCancelled(true);
        player.closeInventory();
    }

    @EventHandler
    public void onPlayerInventoryTrash(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (!player.getOpenInventory().getTitle().equalsIgnoreCase("Recycle Bin")) return;

        player.getOpenInventory().getTopInventory().clear();
    }
}