package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;

public class BanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.ban")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/ban <player> <reason>"));
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        StringBuilder reason = new StringBuilder(" ");

        for (int i = 1; i < args.length; i++) reason.append(args[i]);

        String message = MessageUtility.fixColor("&cYou are banned from this server reason:" + reason + "&7.");

        if (Bukkit.getPlayerExact(args[0]) != null) {
            Player playerTarget = Bukkit.getPlayerExact(args[0]);
            playerTarget.kickPlayer(message);
        }

        ConfigurationSection punishmentSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("punishments.ban");
        List<String> list = punishmentSection.getStringList("players");
        list.add(target.getUniqueId().toString());
        BanList banList = sender.getServer().getBanList(BanList.Type.NAME);
        banList.addBan(String.valueOf(target.getUniqueId()), reason.toString(), null, null);

        if (banList.isBanned(target.getName())) {
            sender.sendMessage(MessageUtility.fixColor("&3" + target.getName() + " &cis already banned&7."));
            return false;
        }

        punishmentSection.set("players", list);
        punishmentSection.set("message", message);
        punishmentSection.set("reason", reason.toString());

        EssentialCommands.getInstance().saveConfig();
        sender.sendMessage(MessageUtility.fixColor("&7(Silent) &3" + sender.getName() + " &3banned &3" + target.getName() + "&7."));
        return true;
    }
}
