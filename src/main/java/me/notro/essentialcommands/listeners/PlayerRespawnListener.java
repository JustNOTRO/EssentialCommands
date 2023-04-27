package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerRespawnEvent event) {
        if (EssentialCommands.getInstance().getConfig().getLocation("spawn") == null) return;
        event.setRespawnLocation(EssentialCommands.getInstance().getConfig().getLocation("spawn"));
        EssentialCommands.getInstance().saveConfig();
    }
}
