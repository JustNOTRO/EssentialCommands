package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(Message.fixColor("&7[&a+&7] ") + player.getName());
        player.teleport(EssentialCommands.getInstance().getConfig().getLocation("spawn"));
        EssentialCommands.getInstance().saveConfig();
        player.setAllowFlight(true);
    }

    @EventHandler
    public void onPlayerPunished(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection punishmentSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("punishments.ban");

        if (EssentialCommands.getInstance().getConfig().getStringList("players").contains(player.getUniqueId().toString())) player.kickPlayer(punishmentSection.getString("reason"));
    }
}
