package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.managers.VanishManager;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuitWhileVanished(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection vanishSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("vanish-mode");
        List<String> vanishedPlayers = vanishSection.getStringList("players-vanished");
        VanishManager vanishManager = new VanishManager(player, vanishSection, vanishedPlayers);

        if (vanishManager.isVanished()) {
            event.setQuitMessage(null);
            return;
        }
    }

    @EventHandler
    public void onPlayerQuitWhileInJail(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection jailSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("jail");
        List<String> jailedPlayers = jailSection.getStringList("players");

        if (jailedPlayers.contains(player.getUniqueId().toString())) {
            Bukkit.getServer().broadcast(MessageUtility.fixColor("&3" + player.getName() + " &bQuit while in jail&7."), "essentials.staff.notify");
            return;
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(MessageUtility.fixColor("&7[&c-&7] ") + player.getName());
    }
}