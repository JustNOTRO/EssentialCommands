package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GamemodeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Message.fixColor(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage())));
            return false;
        }

        if (!player.hasPermission("essentials.gamemode")) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        switch (args[0].toLowerCase()) {

            case "0" -> {
                if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                    player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                    player.sendMessage(Message.fixColor("&7(Silent) &calready on game mode survival&7."));
                    return false;
                }
                player.setGameMode(GameMode.SURVIVAL);
                player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
                player.sendMessage(Message.fixColor("&7(Silent) &bset game mode to &3survival&7."));
            }
            case "1" -> {
                if (player.getGameMode().equals(GameMode.CREATIVE)) {
                    player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                    player.sendMessage(Message.fixColor("&7(Silent) &calready on game mode creative&7."));
                    return false;
                }
                player.setGameMode(GameMode.CREATIVE);
                player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
                player.sendMessage(Message.fixColor("&7(Silent) &bset game mode to &3creative&7."));
            }
            case "2" -> {
                if (player.getGameMode().equals(GameMode.ADVENTURE)) {
                    player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                    player.sendMessage(Message.fixColor("&7(Silent) &calready on game mode adventure&7."));
                    return false;
                }
                player.setGameMode(GameMode.ADVENTURE);
                player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
                player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &bset game mode to &3adventure&7."));
            }
            case "3" -> {
                if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                    player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                    player.sendMessage(Message.fixColor("&7(Silent) &calready on game mode spectator&7."));
                    return false;
                }
                player.setGameMode(GameMode.SPECTATOR);
                player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
                player.sendMessage(Message.fixColor("&7(Silent) &bset game mode to &3spectator&7."));
            }
            default -> {
                player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                player.sendMessage(Message.fixColor("&7(Silent) &cunknown game mode please try again&7."));
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> playerGamemode = new ArrayList<>();
            playerGamemode.add("0");
            playerGamemode.add("1");
            playerGamemode.add("2");
            playerGamemode.add("3");
            return playerGamemode;
        }
        return null;
    }
}