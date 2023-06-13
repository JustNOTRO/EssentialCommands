package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TrollCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.troll")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/troll <player> <troll-type>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        switch (args[1].toLowerCase()) {
            case "frog" -> target.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 50, 50, false, false, false));

            case "speed" -> target.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 50, 200, false, false ,false));
        }
        return true;
    }
}
