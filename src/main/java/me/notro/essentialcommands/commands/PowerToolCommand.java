package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class PowerToolCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");

         if (!(sender instanceof Player player)) {
             sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
             return false;
         }

         if (!player.hasPermission("essentials.powertool")) {
             player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
             player.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
             return false;
         }

        if (args.length == 0) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        if (args.length > 2) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &cUsage&3: &7/powertool <command> <target>"));
            return false;
        }
        Command powerToolCast = Bukkit.getPluginCommand(args[0]);
        Player target = Bukkit.getPlayer(args[1]);

        if (powerToolCast == null) {
            player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &cCommand does not exist."));
            return false;
        }

        if (target == null) {
            player.sendMessage(Message.fixColor(Message.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }
        return true;
    }
}
