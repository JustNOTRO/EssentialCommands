package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.utils.ItemStackCreationUtils;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class ProjectileHitListener implements Listener {

    @EventHandler
    public void onPlayerProjectile(ProjectileHitEvent event) {
        ItemStack teleportBow = ItemStackCreationUtils.createTeleportBow();

        if (!(event.getEntity().getShooter() instanceof Player player)) return;
        if (!player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(teleportBow.getItemMeta().getDisplayName())) return;
        if (!event.getEntity().getType().getName().equalsIgnoreCase(Material.ARROW.toString())) return;

        Location teleportLocation = event.getEntity().getLocation();
        player.teleport(teleportLocation);
        event.getEntity().remove();
        Message.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
    }
}
