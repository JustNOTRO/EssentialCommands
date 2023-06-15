package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class PvPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.pvp")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length > 0) {
            player.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/pvp"));
            return false;
        }

        ConfigurationSection pvpSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("pvp-mode");

        if (!pvpSection.getBoolean("state")) {
            pvpSection.set("state", true);
            Bukkit.broadcastMessage(MessageUtility.fixColor("&bPvP is now &3on&7."));
            EssentialCommands.getInstance().saveConfig();
            return true;
        }

        pvpSection.set("state", false);
        Bukkit.broadcastMessage(MessageUtility.fixColor("&bPvP is now &3off"));
        EssentialCommands.getInstance().saveConfig();
        return true;
    }
}
