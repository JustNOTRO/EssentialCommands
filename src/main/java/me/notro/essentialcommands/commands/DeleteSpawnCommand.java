package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class DeleteSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.delete.spawn")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length > 0) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/deletespawn"));
            return false;
        }

        ConfigurationSection spawnSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("spawn");

        if (spawnSection.getLocation("location") == null) {
            sender.sendMessage(MessageUtility.fixColor("&cSpawn location does not exist&7."));
            return false;
        }

        spawnSection.set("location", null);
        sender.sendMessage(MessageUtility.fixColor("&aSpawn location has been successfully deleted&7."));
        EssentialCommands.getInstance().saveConfig();
        return true;
    }
}
