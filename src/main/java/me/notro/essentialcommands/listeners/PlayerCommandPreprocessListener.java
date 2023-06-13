package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocessListener implements Listener {

    @EventHandler
    public void onUnknownCommand(PlayerCommandPreprocessEvent event) {
        String[] args = event.getMessage().split(" ");
        Player player = event.getPlayer();

        if (Bukkit.getServer().getHelpMap().getHelpTopic(args[0]) == null) {
            event.setCancelled(true);
            player.sendMessage(MessageUtility.fixColor("&cUnknown command please try again&7."));
        }
    }
}
