package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");
        ConfigurationSection punishmentSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("punishments.ban");

        if (!sender.hasPermission("essentials.ban")) {
            sender.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage(Message.fixColor("&7(Silent) &cUsage&7: &b/ban <player> <reason>"));
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        String reason = " ";

        for (int i = 1; i < args.length; i++)
            reason = reason + args[i];

        String message = Message.fixColor("&cYou are banned from this server reason:" + reason + "&7.");

        if (Bukkit.getPlayerExact(args[0]) != null) {
            Player playerTarget = Bukkit.getPlayerExact(args[0]);
            playerTarget.kickPlayer(message);
        }

        sender.getServer().getBanList(BanList.Type.NAME).addBan(String.valueOf(target.getUniqueId()), reason, null, null);

        List<String> list = punishmentSection.getStringList("players");
        if (list.isEmpty()) punishmentSection.set("players", new ArrayList<>());
        list.add(target.getUniqueId().toString());

        punishmentSection.set("players", list);
        punishmentSection.set("message", message);
        punishmentSection.set("reason", reason);

        EssentialCommands.getInstance().saveConfig();
        sender.sendMessage(Message.fixColor("&7(Silent) &b" + sender.getName() + " &3banned &b" + target.getName() + "&7."));
        return true;
    }
}
