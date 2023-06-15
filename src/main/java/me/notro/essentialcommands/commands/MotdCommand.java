package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class MotdCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.motd")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/motd <set> <message>"));
            return false;
        }

        if (!args[0].equalsIgnoreCase("set")) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/motd <set> <message>"));
            return false;
        }

        ConfigurationSection motdSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("server-motd");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < args.length; i++) stringBuilder.append(ChatColor.translateAlternateColorCodes('&', args[i])).append(" ");

        String motdMessage = stringBuilder.toString();
        motdSection.set("message", motdMessage);
        sender.sendMessage(MessageUtility.fixColor("&aNew MOTD has been set to: '" + stringBuilder + "' &7."));
        EssentialCommands.getInstance().saveConfig();
        return true;
    }
}
