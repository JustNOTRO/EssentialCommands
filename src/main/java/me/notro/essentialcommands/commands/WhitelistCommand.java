package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

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
            Message.playSound(sender, Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            sender.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }
        switch (args[0].toLowerCase()) {
            case "add" -> {
                if (args.length < 2) {
                    Message.playSound(sender, Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                    sender.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &cUsage&3: &c/whitelist <action> <player>"));
                    return false;
                }

                OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(args[1]);

                if (offlineTarget.isWhitelisted()) {
                    Message.playSound(sender, Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                    sender.sendMessage(Message.fixColor("&7(Silent) &b" + offlineTarget.getName() + "&7 is already whitelisted."));
                    return false;
                }
                offlineTarget.setWhitelisted(true);
                sender.sendMessage(Message.fixColor("&7(Silent) &b" + offlineTarget.getName() + " &7has been added to the whitelist."));
                }
            case "remove" -> {
                OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(args[1]);

                if (!offlineTarget.isWhitelisted()) {
                    Message.playSound(sender, Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                    sender.sendMessage(Message.fixColor("&7(Silent) &b" + offlineTarget.getName() + "&7 is already not whitelisted."));
                    return false;
                }
                offlineTarget.setWhitelisted(false);
                sender.sendMessage(Message.fixColor("&7(Silent) &b" + offlineTarget.getName() + " &7has been removed from the whitelist."));
                }
            case "on" -> {
                if (Bukkit.getServer().hasWhitelist()) {
                    Message.playSound(sender, Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                    sender.sendMessage(Message.fixColor("&7(Silent) &cwhitelist is already on&7."));
                    return false;
                }
                    Bukkit.setWhitelist(true);
                    sender.sendMessage(Message.fixColor("&7(Silent) whitelist has been turned on."));
                }
                case "off" -> {
                    if (!Bukkit.getServer().hasWhitelist()) {
                        Message.playSound(sender, Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                        sender.sendMessage(Message.fixColor("&7(Silent) &cwhitelist is already off."));
                        return false;
                    }
                    Bukkit.setWhitelist(false);
                    sender.sendMessage(Message.fixColor("&7(Silent whitelist has been turned off."));
                }
                case "list" -> {
                sender.sendMessage(Message.fixColor("&7(Silent) whitelisted players: &b"));
                Bukkit.getWhitelistedPlayers().forEach(offlinePlayer -> {
                    sender.sendMessage(Message.fixColor("&b") + offlinePlayer.getName());
                    });
                }
            }
            return true;
        }
    }
