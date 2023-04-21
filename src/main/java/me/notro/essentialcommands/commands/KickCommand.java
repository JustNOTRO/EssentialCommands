package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class KickCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.kick")) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        if (args.length < 2) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &cUsage&3: &7/kick <player> <reason>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        String reason = Message.fixColor("&b&7[&b&lEssential Commands&7] &8>> &bYou have been kicked from the server for the reason: &c");

        for (int i = 1; i < args.length; i++) {
            reason = reason + args[i] + " ";
        }

        if (target.isOp()) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &cYou cannot kick this player."));
            return false;
        }
        target.kickPlayer(reason);
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
