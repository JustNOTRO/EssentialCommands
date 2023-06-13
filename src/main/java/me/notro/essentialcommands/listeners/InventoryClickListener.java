package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Particle;
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
        player.getWorld().spawnParticle(Particle.SNOWBALL, player.getLocation(), 0, 0, 10, 1);
        player.closeInventory();
    }
}