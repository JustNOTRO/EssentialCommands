package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class KickCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.kick")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 2) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/kick <player> <reason>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        String reason = " ";

        for (int i = 1; i < args.length; i++) {
            reason = reason + args[i];
        }

        String message = MessageUtility.fixColor("&cYou have been kicked from the server for the reason: &c" + reason + "&7.");

        if (target.isOp()) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cYou can't kick this player&7."));
            return false;
        }

        target.kickPlayer(message);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> players = new ArrayList<>();
            Player[] allPlayers = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(allPlayers);

            for (int i = 0; i < allPlayers.length; i++) {
                players.add(allPlayers[i].getName());
            }
            return players;
        }
        return null;
    }
}
