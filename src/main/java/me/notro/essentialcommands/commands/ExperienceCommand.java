package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import me.notro.essentialcommands.utils.SoundsUtility;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExperienceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.exp")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 2) {
            player.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/experience <player> <amount>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        try {
            int amount = Integer.parseInt(args[1]);
            player.giveExpLevels(amount);
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &bGave &3" + target.getName() + " &b" + amount + " exp levels&7."));
            SoundsUtility.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        } catch (NumberFormatException exception) {
            player.sendMessage(MessageUtility.fixColor("&cValue is not numeric/reached to max value"));
        }
        return true;
    }
}
