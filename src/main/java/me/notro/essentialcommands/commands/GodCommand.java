package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GodCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");
        ConfigurationSection godModeSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("god");

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.god")) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        List<String> godModePlayers = godModeSection.getStringList("players");
        if (godModePlayers.isEmpty()) godModeSection.set("players", new ArrayList<>());

        if (!godModePlayers.contains(player.getName())) {
            godModePlayers.add(player.getName());
            player.setInvulnerable(true);
            godModeSection.set("players", godModePlayers);
            player.sendMessage(Message.fixColor("&7(Silent) &bGod mode is now &3On&7."));
            EssentialCommands.getInstance().saveConfig();
            return true;
        }
        godModePlayers.remove(player.getName());
        player.setInvulnerable(false);
        godModeSection.set("players", godModePlayers);
        player.sendMessage(Message.fixColor("&7(Silent) &bGod mode is now &3Off&7."));
        EssentialCommands.getInstance().saveConfig();
        return true;
    }
}
