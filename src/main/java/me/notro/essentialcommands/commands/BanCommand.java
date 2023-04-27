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
            sender.sendMessage(Message.fixColor("&7(Silent) &cusage&7: &b/ban <player> <reason>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(args[0]);


        String reason = Message.fixColor("&cYou have been banned from this server for the reason: ");

        for (int i = 1; i < args.length; i++)
            reason = reason + args[i] + " ";

        try {
            sender.getServer().getBanList(BanList.Type.NAME).addBan(String.valueOf(offlineTarget.getUniqueId()), reason, null, null);
            EssentialCommands.getInstance().saveConfig();
        } catch (NullPointerException exception) {

        }

        target.kickPlayer(reason);
        sender.getServer().getBanList(BanList.Type.NAME).addBan(String.valueOf(target.getUniqueId()), reason, null, null);
        punishmentSection.set("reason", reason);
        punishmentSection.set("players", target.getUniqueId().toString());
        EssentialCommands.getInstance().saveConfig();
        return true;
    }
}
