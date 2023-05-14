package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.systems.ScoreboardManager;
import me.notro.essentialcommands.utils.ItemStackCreationUtils;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.*;


public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(Message.fixColor("&7[&a+&7] ") + player.getName());
        if (EssentialCommands.getInstance().getConfig().getLocation("spawn") == null) return;
        player.teleport(EssentialCommands.getInstance().getConfig().getLocation("spawn"));
        EssentialCommands.getInstance().saveConfig();
        player.setAllowFlight(true);
    }

    @EventHandler
    public void onPlayerJoinWhileVanished(PlayerJoinEvent event) {
        ConfigurationSection vanishSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("vanish");
        Player player = event.getPlayer();

        if (!vanishSection.getStringList("players-vanished").contains(player.getName())) return;
        Bukkit.getOnlinePlayers().forEach(player1 -> {
            player1.hidePlayer(EssentialCommands.getInstance(), player);
            return;
        });
    }

    @EventHandler
    public void onPlayerScoreboard(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        ScoreboardManager scoreboardManager = new ScoreboardManager(player, player.getScoreboard());
        Scoreboard scoreboard = scoreboardManager.createScoreboardManager("Main", "dummy", Message.fixColor("&b&lEssentials Commands"), DisplaySlot.SIDEBAR);

        scoreboardManager.scoreboardScore("Main", Message.fixColor("&6Name: &3" + player.getName()), 9);
        scoreboardManager.scoreboardScore("Main", Message.fixColor("&6Money: &2$" + 1), 8);

        player.setScoreboard(scoreboard);
    }

    @EventHandler
    public void onPlayerGetGrapplingHook(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = ItemStackCreationUtils.createGrapplingHook();

        if (player.getInventory().contains(itemStack)) return;

        player.getInventory().setItem(4, itemStack);
    }
}
