package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;

public class ReportsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.report.list")) {
            player.sendMessage(MessageUtility.NO_PERMISSION.getDefaultMessage());
            return false;
        }

        if (args.length < 1) {
            player.sendMessage(MessageUtility.fixColor("&cUsage&&: &b/reports <clear/list>"));
            return false;
        }

        ConfigurationSection reportSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("reports");
        List<String> reportsList = reportSection.getStringList("players");


        switch (args[0].toLowerCase()) {
            case "list" -> {
                if (reportsList.isEmpty()) {
                    player.sendMessage(MessageUtility.fixColor("&cThere are no new reports&7."));
                    return false;
                }

                for (String report : reportsList) player.sendMessage(report);
            }

            case "clear" -> {
                if (reportsList.isEmpty()) {
                    player.sendMessage(MessageUtility.fixColor("&cThere are no new reports&7."));
                    return false;
                }
                reportSection.set("players", null);
                player.sendMessage(MessageUtility.fixColor("&aAll reports has been cleared&7."));
                EssentialCommands.getInstance().saveConfig();
            }

            default -> player.sendMessage(MessageUtility.fixColor("&cUsage&&: &b/reports <clear/list>"));
        }
        return true;
    }
}
