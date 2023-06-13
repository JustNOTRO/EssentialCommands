package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.models.ItemBuilder;
import me.notro.essentialcommands.systems.ScoreboardManager;
import me.notro.essentialcommands.systems.VanishManager;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection vanishSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("vanish-mode");
        VanishManager vanishManager = new VanishManager(player, vanishSection, vanishSection.getStringList("players-vanished"));

        if (vanishManager.isVanished()) {
            event.setJoinMessage(null);
            player.sendMessage(MessageUtility.fixColor("&7Silently joined the server (vanish)"));
            return;
        }

        if (EssentialCommands.getInstance().getConfig().getLocation("spawn") == null) return;
        player.teleport(EssentialCommands.getInstance().getConfig().getLocation("spawn"));

        event.setJoinMessage(MessageUtility.fixColor("&7[&a+&7] ") + player.getName());
        player.setAllowFlight(true);
    }

    @EventHandler
    public void onPlayerJoinWhileVanished(PlayerJoinEvent event) {
        ConfigurationSection vanishSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("vanish-mode");
        Player player = event.getPlayer();
        VanishManager vanishManager = new VanishManager(player, vanishSection, vanishSection.getStringList("players-vanished"));

        Bukkit.getOnlinePlayers()
                .stream()
                .filter(players -> vanishManager.isVanished())
                .forEach(players -> players.hidePlayer(EssentialCommands.getInstance(), player));
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