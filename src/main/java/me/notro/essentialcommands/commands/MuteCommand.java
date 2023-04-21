package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.models.ConfigFields;
import me.notro.essentialcommands.utils.Config;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class MuteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");
        Config config = EssentialCommands.getInstance().getPunishmentsConfig();

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.mute")) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        if (args.length < 2) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &cUsage&3: &7/mute <player> <reason>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        String rawReason = "";

        for (int i = 1; i < args.length; i++) {
            rawReason = rawReason + args[i] + " ";
        }
        ConfigurationSection punishmentSection = config.createSection(target.getUniqueId().toString());
        punishmentSection.set(ConfigFields.EXECUTOR, player.getUniqueId().toString());
        punishmentSection.set(ConfigFields.REASON, rawReason);
        String reason = Message.fixColor("&7[&b&lEssential Commands&7] &8>> &bMuted " + target.getName() + " for the reason: " + ConfigFields.REASON);
        String targetReason = Message.fixColor("&7[&b&lEssential Commands&7] &8>> &cYou have been muted for the reason: " + ConfigFields.REASON);

        player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
        player.sendMessage(reason);
        target.sendMessage(targetReason);
        return true;
    }
}
