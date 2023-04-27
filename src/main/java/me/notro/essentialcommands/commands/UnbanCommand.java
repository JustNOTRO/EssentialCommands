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

public class UnbanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");
        ConfigurationSection punishmentSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("punishments.ban");

        if (!sender.hasPermission("essentials.unban")) {
            sender.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }
        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

        try {
            sender.getServer().getBanList(BanList.Type.NAME).pardon(String.valueOf(target.getUniqueId()));
        } catch (NullPointerException exception) {
            sender.sendMessage(Message.fixColor("&7(Silent) &cPlayer does not exist&7."));
            return false;
        }
        punishmentSection.set("players", null);
        EssentialCommands.getInstance().saveConfig();
        sender.sendMessage(Message.fixColor("&7(Silent) &b" + sender.getName() + " &3unbanned &b" + target.getName() + "&7."));
        return true;
    }
}
