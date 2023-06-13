package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.random.tp")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length > 0) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/rtp || /jtp"));
            return false;
        }

        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        players.remove(player);

        if (players.isEmpty()) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cNo other players are online."));
            return false;
        }

        Player randomPlayer = players.get(new Random().nextInt(players.size()));

        player.teleport(randomPlayer.getLocation());
        player.sendMessage(MessageUtility.fixColor("&7(Silent) &bRandomly teleported to &3" + randomPlayer.getName() + "&7."));
        return true;
    }
}
