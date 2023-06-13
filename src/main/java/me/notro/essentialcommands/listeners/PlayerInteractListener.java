package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ConfigurationSection padSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("launch-pad.pad");

        Player player = event.getPlayer();
        Vector vector = player.getLocation().getDirection().multiply(1).setY(padSection.getInt("velocity-strength"));

        if (!padSection.getBoolean("enabled")) return;
        if (!event.getAction().equals(Action.PHYSICAL)) return;
        if (!event.getClickedBlock().getType().equals(Material.STONE_PRESSURE_PLATE)) return;

        player.setVelocity(vector);
    }

    @EventHandler
    public void onPlayerBuild(PlayerInteractEvent event) {
        ConfigurationSection buildSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("build");
        Player player = event.getPlayer();

        if (!buildSection.getStringList("players-building").contains(player.getName())) event.setCancelled(true);
    }
}