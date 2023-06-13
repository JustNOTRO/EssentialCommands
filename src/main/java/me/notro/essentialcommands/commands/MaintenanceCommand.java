package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class MaintenanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.maintenance.use")) {
            sender.sendMessage(MessageUtility.NO_PERMISSION.getDefaultMessage());
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&&7: &b/maintenance <on/off>"));
            return false;
        }

        ConfigurationSection maintenanceMode = EssentialCommands.getInstance().getConfig().getConfigurationSection("maintenance");

        switch (args[0].toLowerCase()) {
            case "on" -> {
                sender.getServer().setWhitelist(true);
                maintenanceMode.set("state", true);
                sender.sendMessage(MessageUtility.fixColor("&cMaintenance mode is now on&7."));
                EssentialCommands.getInstance().saveConfig();
            }

            case "off" -> {
                sender.getServer().setWhitelist(false);
                maintenanceMode.set("state", false);
                sender.sendMessage(MessageUtility.fixColor("&cMaintenance mode is now off&7."));
                EssentialCommands.getInstance().saveConfig();
            }

            default -> sender.sendMessage(MessageUtility.fixColor("&cUsage&&7: &b/maintenance <on/off>"));
        }
        return true;
    }
}
