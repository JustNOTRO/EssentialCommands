package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection padSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("launch-pad.pad");
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

        if (buildSection.getStringList("players-building").contains(player.getUniqueId().toString())) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteractWhileInJail(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection jailSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("jail");
        List<String> jailedPlayers = jailSection.getStringList("players");

        if (jailedPlayers.contains(player.getUniqueId().toString())) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPowerToolInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();

        if (itemStack == null || !itemStack.hasItemMeta() || !itemStack.getItemMeta().hasDisplayName()) return;
        if (!event.getAction().name().contains("RIGHT_CLICK")) return;

        String powertoolCommand = itemStack.getItemMeta().getDisplayName();
        player.performCommand(powertoolCommand);
        event.setCancelled(true);
    }
}