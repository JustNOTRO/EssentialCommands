package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class UnmuteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.unmute")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        if (args.length > 1) {
            sender.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/unmute <player>"));
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

        if (Bukkit.getPlayer(args[0]) != null) {
            Player myTarget = Bukkit.getPlayer(args[0]);
            myTarget.sendMessage(MessageUtility.fixColor("&aYou have been unmuted by &3" + sender.getName() + "&7."));
        }

        ConfigurationSection punishmentSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("punishments.mute");

        punishmentSection.set("reason", null);
        punishmentSection.set("players", null);
        EssentialCommands.getInstance().saveConfig();
        sender.sendMessage(MessageUtility.fixColor("&7(Silent) &3" + sender.getName() + "&b Unmuted &3" + target.getName() + "&7."));
        return true;
    }
}
