package me.notro.essentialcommands.systems;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import javax.annotation.Nullable;

@AllArgsConstructor
public class WhitelistManager {

    private @Nullable OfflinePlayer player;

    public void addPlayerToWhitelist() {
        if (player.isWhitelisted()) return;
        player.setWhitelisted(true);
    }

    public void removePlayerFromWhitelist() {
        if (!player.isWhitelisted()) return;
        player.setWhitelisted(false);
    }

    public void enableWhitelist() {
        if (Bukkit.getServer().hasWhitelist()) return;
        Bukkit.getServer().setWhitelist(true);
    }

    public void disableWhitelist() {
        if (!Bukkit.getServer().hasWhitelist()) return;
        Bukkit.getServer().setWhitelist(false);
    }
}
