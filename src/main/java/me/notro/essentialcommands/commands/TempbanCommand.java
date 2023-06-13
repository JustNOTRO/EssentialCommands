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

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class TempbanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection punishmentSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("punishments.temp-ban");

        if (!sender.hasPermission("essentials.tempban")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 3) {
            sender.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/tempban <player> <duration> <reason>"));
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        List<String> tempBanPlayers = punishmentSection.getStringList("players");
        long amount = Long.parseLong(args[1]);
        Date start = Date.from(Instant.now());
        Duration expiry = Duration.ofSeconds(amount);
        String reason = " ";

        for (int i = 2; i < args.length; i++) reason += args[i];

        String message = MessageUtility.fixColor("&cYou are banned from this server until: &b" + expiry.toString() + "&cfor the reason: " + reason + "&7.");

        if (Bukkit.getPlayer(args[0]) != null) {
            Player player = Bukkit.getPlayer(args[0]);
            player.kickPlayer(message);
        }



        return true;
    }
}
