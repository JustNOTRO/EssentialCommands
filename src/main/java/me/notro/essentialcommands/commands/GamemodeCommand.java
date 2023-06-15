package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.managers.GamemodeManager;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GamemodeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage())));
            return false;
        }

        if (!player.hasPermission("essentials.gamemode")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "0", "s" -> new GamemodeManager(player, GameMode.SURVIVAL);

            case "1", "c" -> new GamemodeManager(player, GameMode.CREATIVE);

            case "2", "a" -> new GamemodeManager(player, GameMode.ADVENTURE);

            case "3", "sp" -> new GamemodeManager(player, GameMode.SPECTATOR);

            default -> player.sendMessage(MessageUtility.fixColor("&7(Silent) &cUnknown game mode please try again&7."));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> playerGamemode = new ArrayList<>();
            playerGamemode.add("0");
            playerGamemode.add("s");
            playerGamemode.add("1");
            playerGamemode.add("c");
            playerGamemode.add("2");
            playerGamemode.add("a");
            playerGamemode.add("3");
            playerGamemode.add("sp");
            return playerGamemode;
        }
        return null;
    }
}