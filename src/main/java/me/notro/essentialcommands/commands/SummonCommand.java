package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SummonCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.summon")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 3) {
            sender.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/summon <entity> <player> <amount>"));
            return false;
        }

        EntityType entityType = EntityType.fromName(args[0]);
        Player target = Bukkit.getPlayer(args[1]);
        int amount;

        if (entityType == null) {
            sender.sendMessage(MessageUtility.fixColor("&7(Silent) &centity does not exist&7."));
            return false;
        }

        if (target == null) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        try {
            amount = Integer.parseInt(args[2]);
        } catch (NumberFormatException exception) {
            sender.sendMessage(MessageUtility.fixColor("&7(Silent) &cAmount needs to be numeric&7."));
            return false;
        }

        for (int i = 0; i < amount; i++)
            target.getWorld().spawnEntity(target.getLocation(), entityType);
        sender.sendMessage(MessageUtility.fixColor("&7(Silent) &3" + sender.getName() + " &bsummoned &3" + entityType.getName() + " " + amount + "x" + " &3to &3" + target.getName() + "&b's location&7."));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> entities = new ArrayList<>();

            for (EntityType value : EntityType.values()) {
                entities.add(value.toString());
            }
            return entities;
        }
        return null;
    }
}
