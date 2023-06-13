package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.set.spawn")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        ConfigurationSection spawnSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("spawn");
        spawnSection.set("location", player.getLocation());
        EssentialCommands.getInstance().saveConfig();
        player.sendMessage(MessageUtility.fixColor("&aSpawn has been set&7."));
        return true;
    }
}
