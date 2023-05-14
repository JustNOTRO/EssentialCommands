package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.utils.ItemStackCreationUtils;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerFishListener implements Listener {

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = ItemStackCreationUtils.createGrapplingHook();

        if (!itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(Message.fixColor("&aGrappling Hook"))) return;
        if (!event.getState().equals(PlayerFishEvent.State.REEL_IN)) return;

        Location locationThrow = player.getLocation();
        Location hookLocation = event.getHook().getLocation();
        Location locationToChange = hookLocation.subtract(locationThrow);

        player.setVelocity(locationToChange.toVector().multiply(0.3D).setY(2));
    }
}
