package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingListener implements Listener {

    @EventHandler
    public void onServerMOTD(ServerListPingEvent event) {
        ConfigurationSection motdSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("server-motd");
        String motdMessage = motdSection.getString("message");

        if (motdMessage == null) {
            event.setMotd("A Minecraft Server");
            return;
        }

        event.setMotd(motdMessage);
    }
}
