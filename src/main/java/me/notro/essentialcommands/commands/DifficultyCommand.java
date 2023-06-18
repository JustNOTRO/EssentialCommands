package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class DifficultyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.difficulty")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/difficulty <Peaceful/Easy/Normal/Hard>"));
            return false;
        }

        List<World> worlds = Bukkit.getWorlds();

        try {
            Difficulty difficulty = Difficulty.valueOf(args[0].toUpperCase());
            worlds.forEach(world -> world.setDifficulty(difficulty));
            sender.sendMessage(MessageUtility.fixColor("&bDifficulty has been set to &3" + difficulty.name() + "&7."));
        } catch (IllegalArgumentException exception) {
            sender.sendMessage(MessageUtility.fixColor("&cDifficulty does not exist&7."));
        }
        return true;
    }
}
