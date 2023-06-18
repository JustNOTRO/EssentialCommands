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

public class UnbanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.unban")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        ConfigurationSection punishmentSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("punishments.ban");

        BanList banList = sender.getServer().getBanList(BanList.Type.NAME);

        if (!banList.isBanned(target.getUniqueId().toString())) {
            sender.sendMessage(MessageUtility.fixColor("&3" + target.getName() + " &cis not banned&7."));
            return false;
        }

        punishmentSection.set("players", null);
        punishmentSection.set("message", null);
        punishmentSection.set("reason", null);
        banList.pardon(target.getUniqueId().toString());
        EssentialCommands.getInstance().saveConfig();
        sender.sendMessage(MessageUtility.fixColor("&7(Silent) &3" + sender.getName() + " &bunbanned &3" + target.getName() + "&7."));
        return true;
    }
}
