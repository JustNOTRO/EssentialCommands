package me.notro.essentialcommands.managers;

import me.notro.essentialcommands.utils.MessageUtility;
import me.notro.essentialcommands.utils.WordUtility;
import org.bukkit.entity.Player;

public class GamemodeManager {

    private final org.bukkit.GameMode gameMode;

    public GamemodeManager(Player player, org.bukkit.GameMode gameMode) {
        this.gameMode = gameMode;
        if (player.getGameMode().equals(this.gameMode)) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cAlready on game mode " + getType() + "&7."));
            return;
        }

        player.setGameMode(gameMode);
        player.sendMessage(MessageUtility.fixColor("&7(Silent) &bSet game mode to &3" + getType() + "&7."));
    }

    private String getType() {
        return WordUtility.capitalizeFully(gameMode.name().toLowerCase(), null);
    }
}