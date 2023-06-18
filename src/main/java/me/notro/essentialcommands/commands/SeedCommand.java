package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.seed")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length > 0) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/seed"));
            return false;
        }

        World world = Bukkit.getWorld("world");

        sender.sendMessage(MessageUtility.fixColor("&7[Server Seed] &b" + world.getSeed()));
        return true;
    }
}
