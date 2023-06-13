package me.notro.essentialcommands.systems;

import me.notro.essentialcommands.EssentialCommands;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;

public class VanishManager {

    private final Player player;
    private final ConfigurationSection configurationSection;

    private EssentialCommands plugin;
    private final List<String> vanishList;

    public VanishManager(Player player, ConfigurationSection configurationSection, List<String> vanishList) {
        this.player = player;
        this.configurationSection = configurationSection;
        this.vanishList = vanishList;
    }

    public void hidePlayer() {
        vanishList.add(player.getUniqueId().toString());

        Bukkit.getOnlinePlayers()
                .stream()
                .filter(players -> !vanishList.contains(player.getUniqueId().toString()))
                .forEach(players -> players.hidePlayer(plugin, player));
        configurationSection.set("players-vanished", vanishList);
    }

    public void showPlayer() {
        vanishList.remove(player.getUniqueId().toString());

        Bukkit.getOnlinePlayers()
                .stream()
                .filter(players -> vanishList.contains(player.getUniqueId().toString()))
                .forEach(players -> players.showPlayer(plugin, player));
        configurationSection.set("players-vanished", vanishList);
    }

    public boolean isVanished() {
        return vanishList.contains(player.getUniqueId().toString());
    }
}