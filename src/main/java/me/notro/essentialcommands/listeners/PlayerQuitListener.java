package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(MessageUtility.fixColor("&7[&c-&7] ") + player.getName());
    }
}