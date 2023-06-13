package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;

public class ReportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage());
            return false;
        }

        if (!player.hasPermission("essentials.report.usage")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/report <player> <reason>"));
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        ConfigurationSection reportSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("reports");
        List<String> reportsList = reportSection.getStringList("players");
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < args.length; i++) stringBuilder.append(args[i]).append(" ");

        reportsList.add(MessageUtility.fixColor("&3" + target.getName() + " &ahas been reported for the reason: &3"));
        reportSection.set("players", reportsList);
        sender.sendMessage(MessageUtility.fixColor("&aYou reported &3" + target.getName() + " &afor the reason: &3" + stringBuilder + "&7."));
        if (player.hasPermission("essentials.staff.notify")) player.sendMessage(MessageUtility.fixColor("&7(Silent) &3" + target.getName() + " &bhas been reported for the reason: &3" + stringBuilder + "&7."));
        EssentialCommands.getInstance().saveConfig();
        return true;
    }
}
