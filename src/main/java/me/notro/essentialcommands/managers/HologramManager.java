package me.notro.essentialcommands.managers;

import lombok.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

@Getter
@Setter
@AllArgsConstructor
public class HologramManager {

    private Player player;

    public void createArmorStand(final @NonNull Location location, final @NonNull String name) {
        ArmorStand hologram = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

        hologram.setInvisible(true);
        hologram.setInvulnerable(true);
        hologram.setGravity(false);
        hologram.setCustomNameVisible(true);
        hologram.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
    }
}