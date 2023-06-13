package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.models.ItemBuilder;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class PlayerFishListener implements Listener {

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        ItemBuilder grapplingHook = new ItemBuilder(Material.FISHING_ROD);
        grapplingHook.setDisplayName("&bGRAPPLING HOOK");

        if (!grapplingHook.getItemMeta().getDisplayName().equalsIgnoreCase(MessageUtility.fixColor("&bGRAPPLING HOOK"))) return;
        if (!event.getState().equals(PlayerFishEvent.State.REEL_IN)) return;

        Location locationThrow = player.getLocation();
        Location hookLocation = event.getHook().getLocation();
        Location locationToChange = hookLocation.subtract(locationThrow);

        player.setVelocity(locationToChange.toVector().multiply(0.3D).setY(2));
    }
}
