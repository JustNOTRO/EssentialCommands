package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class MutechatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.mutechat")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        ConfigurationSection muteChatSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("mute-chat");

        if (!muteChatSection.getBoolean("chat-muted")) {
            muteChatSection.set("chat-muted", true);
            Bukkit.broadcastMessage(MessageUtility.fixColor("&cChat has been silenced by &3" + sender.getName() + "&7."));
            EssentialCommands.getInstance().saveConfig();
            return true;
        }

        muteChatSection.set("chat-muted", false);
        Bukkit.broadcastMessage(MessageUtility.fixColor("&cChat has been unsilenced by &3" + sender.getName() + "&7."));
        EssentialCommands.getInstance().saveConfig();
        return true;
    }
}
