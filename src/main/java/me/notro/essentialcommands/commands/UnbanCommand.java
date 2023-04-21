package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class UnbanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");
        ConfigurationSection punishmentSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("punishments.ban");

        if (command.getName().equalsIgnoreCase("unban")) {
            if (args.length == 0) {
                sender.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
                return false;
            }

            if (!sender.hasPermission("essentials.unban")) {
                sender.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
                return false;
            }
            return true;
        }
        return false;
    }
}
