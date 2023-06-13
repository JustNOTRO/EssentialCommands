package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class WhitelistCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.whitelist")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/whitelist <action> <player>"));
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "add" -> {
                if (args.length < 2) {
                    sender.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/whitelist <action> <player>"));
                    return false;
                }

                OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                if (player.isWhitelisted()) {
                    sender.sendMessage(MessageUtility.fixColor("&7(Silent) &3" + player.getName() + " &bis already on the whitelist&7."));
                    return false;
                }
                sender.sendMessage(MessageUtility.fixColor("&7(Silent) &3" + sender.getName() + " &bAdded &3" + player.getName() + " &bto the whitelist&7."));
                player.setWhitelisted(true);
            }

            case "remove" -> {
                if (args.length < 2) {
                    sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/whitelist <action> <player>"));
                    return false;
                }

                OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                if (!player.isWhitelisted()) {
                    sender.sendMessage(MessageUtility.fixColor("&7(Silent) &3" + player.getName() + " &bis already not on the whitelist&7."));
                    return false;
                }
                sender.sendMessage(MessageUtility.fixColor("&7(Silent) &3" + sender.getName() + " &bRemoved &3" + player.getName() + " &bto the whitelist&7."));
                player.setWhitelisted(false);
            }

            case "enable" -> {
                if (Bukkit.getServer().hasWhitelist()) {
                    sender.sendMessage(MessageUtility.fixColor("&cWhitelist is already enabled&7."));
                    return false;
                }
                sender.sendMessage(MessageUtility.fixColor("&7(Silent) &bWhitelist has been &3Enabled &bby &3" + sender.getName() + "&7."));
                Bukkit.getServer().setWhitelist(true);
            }

            case "disable" -> {
                if (!Bukkit.getServer().hasWhitelist()) {
                    sender.sendMessage(MessageUtility.fixColor("&cWhitelist is already disabled&7."));
                    return false;
                }
                sender.sendMessage(MessageUtility.fixColor("&7(Silent) &bWhitelist has been &3Disabled &bby &3" + sender.getName() + "&7."));
                Bukkit.getServer().setWhitelist(false);
            }

            case "list" -> {
                List<String> whitelistedPlayers = new ArrayList<>();
                Bukkit.getWhitelistedPlayers().forEach(offlinePlayer -> whitelistedPlayers.add(offlinePlayer.getName()));
                sender.sendMessage(MessageUtility.fixColor("&7(Silent) &bWhitelisted Players&7: &3" + whitelistedPlayers + "&7."));
            }
            default -> sender.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/whitelist <action> <player>"));
        }
        return true;
    }
}
