package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");
        ConfigurationSection vanishSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("vanish");

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.vanish")) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        List<String> vanishedPlayers = vanishSection.getStringList("players-vanished");
        if (vanishedPlayers.isEmpty()) vanishSection.set("players-vanished", new ArrayList<>());

        Bukkit.getOnlinePlayers().forEach(player1 -> {
            if (!vanishedPlayers.contains(player.getName())) {
                vanishedPlayers.add(player.getName());
                if (player1.hasPermission("essentials.vanish.see") && vanishSection.getBoolean("staff-see")) player1.showPlayer(EssentialCommands.getInstance(), player);
                player1.hidePlayer(EssentialCommands.getInstance(), player);
                vanishSection.set("pickup-items", false);
                player.sendMessage(Message.fixColor("&7(Silent) &bYou are now &3invisible &bto all players&7."));
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Message.fixColor("&cYOU ARE NOW INVISIBLE TO ALL PLAYERS")));
                return;
            }
            vanishedPlayers.remove(player.getName());
            player1.showPlayer(EssentialCommands.getInstance(), player);
            vanishSection.set("pickup-items", true);
            player.sendMessage(Message.fixColor("&7(Silent) &bYou are now &3visible &bto all players&7."));
        });
        vanishSection.set("players-vanished", vanishedPlayers);
        EssentialCommands.getInstance().saveConfig();
        return true;
    }
}