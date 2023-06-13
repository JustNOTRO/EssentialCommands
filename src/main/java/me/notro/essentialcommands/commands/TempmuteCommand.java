package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

import java.time.Instant;
import java.util.List;

public class TempmuteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection punishmentSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("punishments.temp-mute");

        if (!sender.hasPermission("essentials.tempmute")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 3) {
            sender.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/tempmute <player> <time> <reason>"));
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
        List<String> tempMutedPlayers = punishmentSection.getStringList("players");
        long amount = Long.parseLong(args[1]);
        Instant start = Instant.now();
        Instant expiry = Instant.now().plusSeconds(amount);
        String reason = " ";

        for (int i = 2; i < args.length; i++) reason += args[i];

        String message = MessageUtility.fixColor("You are muted for: " + expiry + "seconds");

        tempMutedPlayers.add(target.getName());
        punishmentSection.set("players", tempMutedPlayers);
        punishmentSection.set("reason", reason);
        punishmentSection.set("message", message);
        punishmentSection.set("muted-since", start.toString());
        punishmentSection.set("muted-until", expiry.toString());
        EssentialCommands.getInstance().saveConfig();
        return true;
    }
}
