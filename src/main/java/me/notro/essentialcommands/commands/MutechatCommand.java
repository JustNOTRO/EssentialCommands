package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class MutechatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");
        ConfigurationSection muteChatSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("mutechat");

        if (!sender.hasPermission("essentials.mutechat")) {
            Message.playSound(sender, Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            sender.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (!muteChatSection.getBoolean("chat-muted")) {
            muteChatSection.set("chat-muted", true);
            Bukkit.broadcastMessage(Message.fixColor("&cChat has been silenced by &3" + sender.getName() + "&7."));
            EssentialCommands.getInstance().saveConfig();
            return true;
        }
        muteChatSection.set("chat-muted", false);
        Bukkit.broadcastMessage(Message.fixColor("&cChat has been unsilenced by &3" + sender.getName() + "&7."));
        EssentialCommands.getInstance().saveConfig();
        return true;
    }
}
