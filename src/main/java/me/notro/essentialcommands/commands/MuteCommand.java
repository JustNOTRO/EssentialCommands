package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MuteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");
        ConfigurationSection punishmentSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("punishments.mute");

        if (!sender.hasPermission("essentials.mute")) {
            sender.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage(Message.fixColor("&7(Silent) &cUsage&7: &b/mute <player> <reason>"));
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

        String reason = " ";

        for (int i = 1; i < args.length; i++) {
            reason = reason + args[i];
        }

        String message = Message.fixColor("&cYou have been muted by &3" + sender.getName() + " &cfor the reason:" + reason + "&7.");

        if (Bukkit.getPlayer(args[0]) != null) {
            Player myTarget = Bukkit.getPlayer(args[0]);
            myTarget.sendMessage(message);
        }

        List<String> list = punishmentSection.getStringList("players");
        if (list.isEmpty()) punishmentSection.set("players", new ArrayList<>());
        list.add(target.getUniqueId().toString());

        punishmentSection.set("players", list);
        punishmentSection.set("message", message);
        punishmentSection.set("reason", reason);

        EssentialCommands.getInstance().saveConfig();
        sender.sendMessage(Message.fixColor("&7(Silent) &3" + sender.getName() + " &bmuted &3" + target.getName() + "&7."));
        return true;
    }
}