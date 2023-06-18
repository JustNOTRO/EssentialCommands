package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FastMineCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.haste")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/haste <player> <duration>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        int duration;

        try {
            duration = Integer.parseInt(args[1]);

            if (duration <= 0) {
                sender.sendMessage(MessageUtility.fixColor("&cDuration must be at least 1&7."));
                return false;
            }

            target.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, duration * 20, 1));
        } catch (NumberFormatException exception) {
            sender.sendMessage(MessageUtility.fixColor("&cDuration is not numeric&7."));
            return false;
        }

        sender.sendMessage(MessageUtility.fixColor("&bSet haste for &3" + duration + " &bsecond(s)&7."));
        return true;
    }
}
