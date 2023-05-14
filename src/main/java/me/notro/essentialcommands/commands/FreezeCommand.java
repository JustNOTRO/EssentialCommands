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

import java.util.ArrayList;
import java.util.List;

public class FreezeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection freezeSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("freeze");
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.freeze")) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        List<String> freezedPlayers = freezeSection.getStringList("players-freezed");
        if (freezedPlayers.isEmpty()) freezeSection.set("players-freezed", new ArrayList<>());

        if (!freezeSection.getStringList("players-freezed").contains(target.getName())) {
            freezedPlayers.add(target.getName());
            freezeSection.set("players-freezed", freezedPlayers);
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor("&7(Silent) &3" + target.getName() + " &bhas been frozen by &3" + player.getName() + "&7."));
            EssentialCommands.getInstance().saveConfig();
            return true;
        }
        freezedPlayers.remove(target.getName());
        freezeSection.set("players-freezed", freezedPlayers);
        player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
        player.sendMessage(Message.fixColor("&7(Silent) &3" + target.getName() + " &bhas been unfrozen by &3" + player.getName() + "&7."));
        EssentialCommands.getInstance().saveConfig();
        return true;
    }
}
