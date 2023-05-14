package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.systems.WhitelistManager;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class WhitelistCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");

        if (!sender.hasPermission("essentials.whitelist")) {
            Message.playSound(sender, Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            sender.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(Message.fixColor("&7(Silent) &cUsage&7: &b/whitelist <action> <player>"));
            return false;
        }

        switch (args[0].toLowerCase()) {

            case "add" -> {
                if (args.length < 2) {
                    sender.sendMessage(Message.fixColor("&7(Silent) &cUsage&7: &b/whitelist <action> <player>"));
                    return false;
                }
                OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                WhitelistManager whitelistManager = new WhitelistManager(player);
                whitelistManager.addPlayerToWhitelist();
            }

            case "remove" -> {
                if (args.length < 2) {
                    sender.sendMessage(Message.fixColor("&cUsage&7: &b/whitelist <action> <player>"));
                    return false;
                }
                OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
                WhitelistManager whitelistManager = new WhitelistManager(player);
                whitelistManager.removePlayerFromWhitelist();
            }

            case "enable" -> {
                WhitelistManager whitelistManager = new WhitelistManager(null);
                whitelistManager.enableWhitelist();
                sender.sendMessage(Message.fixColor("&7(Silent) &bWhitelist has been &3Enabled &bby &3" + sender.getName() + "&7."));
            }

            case "disable" -> {
                WhitelistManager whitelistManager = new WhitelistManager(null);
                whitelistManager.disableWhitelist();
                sender.sendMessage(Message.fixColor("&7(Silent) &bWhitelist has been &3Disabled &bby &3" + sender.getName() + "&7."));
            }

            case "list" -> {
                List<String> whitelistedPlayers = new ArrayList<>();
                Bukkit.getWhitelistedPlayers().forEach(offlinePlayer -> {
                    whitelistedPlayers.add(offlinePlayer.getName());
                });
                sender.sendMessage(Message.fixColor("&bWhitelisted Players&7: &3" + whitelistedPlayers + "&7."));
            }
            default -> sender.sendMessage(Message.fixColor("&7(Silent) &cUsage&7: &b/whitelist <action> <player>"));
        }
        return true;
    }
}
