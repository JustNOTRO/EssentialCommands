package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;

public class GodCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.god")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        ConfigurationSection godModeSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("god-mode");
        List<String> godModePlayers = godModeSection.getStringList("players");

        if (!godModePlayers.contains(player.getUniqueId().toString())) {
            godModePlayers.add(player.getUniqueId().toString());
            player.setInvulnerable(true);
            godModeSection.set("players", godModePlayers);
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &bGod mode is now &3On&7."));
            EssentialCommands.getInstance().saveConfig();
            return true;
        }

        godModePlayers.remove(player.getUniqueId().toString());
        godModeSection.set("players", godModePlayers);
        player.setInvulnerable(false);
        player.sendMessage(MessageUtility.fixColor("&7(Silent) &bGod mode is now &3Off&7."));
        EssentialCommands.getInstance().saveConfig();
        return true;
    }
}
