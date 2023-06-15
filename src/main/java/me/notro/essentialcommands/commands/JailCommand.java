package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;

public class JailCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.jail")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/jail <add/remove> <player>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[1]);

        if (target == null) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        ConfigurationSection jailSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("jail");
        List<String> jailedPlayers = jailSection.getStringList("players");

        if (jailSection.getLocation("location") == null) {
            sender.sendMessage(MessageUtility.fixColor("&cJail location is undefined&7."));
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "add" -> {
                if (target.hasPermission("essentials.jail.bypass")) {
                    sender.sendMessage(MessageUtility.fixColor("&3" + target.getName() + " &ccannot be jailed silly&7."));
                    return false;
                }

                if (jailSection.contains(target.getUniqueId().toString())) {
                    sender.sendMessage(MessageUtility.fixColor("&3" + target.getName() + " &cis already in jail&7."));
                    return false;
                }

                jailedPlayers.add(target.getUniqueId().toString());
                jailSection.set("players", jailedPlayers);

                sender.sendMessage(MessageUtility.fixColor("&7(Silent) &bSent &3" + target.getName() + " &bto the jail&7."));
                target.sendMessage(MessageUtility.fixColor("&cYou have been sent to jail by &3" + sender.getName() + "&7."));
                target.teleport(jailSection.getLocation("location"));
                EssentialCommands.getInstance().saveConfig();
            }

            case "remove" -> {
                if (jailedPlayers.isEmpty()) {
                    sender.sendMessage(MessageUtility.fixColor("&3" + target.getName() + " &cis not jailed&7."));
                    return false;
                }

                ConfigurationSection spawnSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("spawn");
                jailSection.set("players", null);

                sender.sendMessage(MessageUtility.fixColor("&7(Silent) &bSent &3" + target.getName() + " &baway from jail to spawn&7."));
                target.sendMessage(MessageUtility.fixColor("&aYou have been sent from jail to spawn by &3" + sender.getName() + "&7."));
                target.teleport(spawnSection.getLocation("location"));
                EssentialCommands.getInstance().saveConfig();
            }

            default -> sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/jail <add/remove> <player>"));
        }
        return true;
    }
}