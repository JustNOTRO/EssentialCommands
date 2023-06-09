package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.spawn")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        ConfigurationSection spawnSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("spawn");

        if (spawnSection.getLocation("location") == null) {
            player.sendMessage(MessageUtility.fixColor("&cSpawn does not exist&7."));
            return false;
        }

        player.teleport(spawnSection.getLocation("location"));
        player.sendMessage(MessageUtility.fixColor("&7(Silent) &bTeleported to the &3Spawn&7."));
        return true;
    }
}
