package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;

public class SocialSpyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.socialspy")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 1) {
            player.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/socialspy <player>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        ConfigurationSection socialSpySection = EssentialCommands.getInstance().getConfig().getConfigurationSection("social-spies");
        List<String> socialSpies = socialSpySection.getStringList("spying-players");
        List<String> targetedPlayers = socialSpySection.getStringList("targeted-players");

        if (!socialSpies.contains(player.getUniqueId().toString())) {
            player.sendMessage(MessageUtility.fixColor("&7[Social Spy] &ais now on&7."));
            socialSpies.add(player.getUniqueId().toString());
            targetedPlayers.add(target.getUniqueId().toString());
            socialSpySection.set("targeted-players", targetedPlayers);
            socialSpySection.set("spying-players", socialSpies);
            EssentialCommands.getInstance().saveConfig();
            return true;
        }

        player.sendMessage(MessageUtility.fixColor("&7[Social Spy] &cis now off&7."));
        socialSpies.remove(player.getUniqueId().toString());
        targetedPlayers.remove(target.getUniqueId().toString());
        socialSpySection.set("targeted-players", null);
        socialSpySection.set("spying-players", null);
        EssentialCommands.getInstance().saveConfig();
        return true;
    }
}
