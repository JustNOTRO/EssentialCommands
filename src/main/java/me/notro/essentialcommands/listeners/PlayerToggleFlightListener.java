package me.notro.essentialcommands.listeners;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class PlayerToggleFlightListener implements Listener {

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR)) return;

        event.setCancelled(true);
        player.setAllowFlight(false);
        player.setAllowFlight(true);
        player.setFlying(false);

        player.setVelocity(player.getLocation().getDirection().multiply(2.5).setY(1));
        player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1, 1);
    }
}