package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class ClearLagCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.clear.lag")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/clearlag <world>"));
            return false;
        }

        World world = Bukkit.getWorld(args[0]);

        if (world == null) {
            sender.sendMessage(MessageUtility.fixColor("&cWorld does not exist&7."));
            return false;
        }

        world.getEntities()
                .stream()
                .filter(entity -> !(entity instanceof Player))
                .forEach(Entity::remove);

        sender.sendMessage(MessageUtility.fixColor("&bCleared all entities from " + world.getName() + "&7."));
        return true;
    }
}
