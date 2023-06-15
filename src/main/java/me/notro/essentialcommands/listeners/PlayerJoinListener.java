package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.ItemBuilder;
import me.notro.essentialcommands.managers.ScoreboardManager;
import me.notro.essentialcommands.managers.VanishManager;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.util.List;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection spawnSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("spawn");
        ConfigurationSection vanishSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("vanish-mode");
        ConfigurationSection jailSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("jail");

        List<String> jailedPlayers = jailSection.getStringList("players");
        VanishManager vanishManager = new VanishManager(player, vanishSection, vanishSection.getStringList("players-vanished"));

        if (vanishManager.isVanished()) {
            event.setJoinMessage(null);
            player.sendMessage(MessageUtility.fixColor("&7Silently joined the server (vanish)"));
            return;
        }

        event.setJoinMessage(MessageUtility.fixColor("&7[&a+&7] ") + player.getName());

        if (jailedPlayers.contains(player.getUniqueId().toString())) {
            Bukkit.getServer().broadcast(MessageUtility.fixColor("&3" + player.getName() + " &bJoined while in jail&7."), "essentials.staff.notify");
            player.teleport(jailSection.getLocation("location"));
            return;
        }

        if (spawnSection.getLocation("location") == null) return;

        player.teleport(spawnSection.getLocation("location"));
        player.setAllowFlight(true);
    }

    @EventHandler
    public void onPlayerScoreboard(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        ScoreboardManager scoreboardManager = new ScoreboardManager(player, player.getScoreboard());
        Scoreboard scoreboard = scoreboardManager.createScoreboardManager("Main", "dummy", MessageUtility.fixColor("&b&lEssentials Commands"), DisplaySlot.SIDEBAR);
        scoreboardManager.scoreboardScore("Main", MessageUtility.fixColor("&6Name: &3" + player.getName()), 9);
        scoreboardManager.scoreboardScore("Main", MessageUtility.fixColor("&6Money: &2$" + 1), 8);

        player.setScoreboard(scoreboard);
    }

    @EventHandler
    public void onPlayerGetGrapplingHook(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemBuilder grapplingHook = new ItemBuilder(Material.FISHING_ROD);
        grapplingHook.setDisplayName("&bGRAPPLING HOOK");

        if (player.getInventory().contains(grapplingHook.build())) return;
        player.getInventory().setItem(4, grapplingHook.build());
    }
}