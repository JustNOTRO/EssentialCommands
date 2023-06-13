package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.systems.WarpManager;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.setwarp")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 1) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/setwarp <name>"));
            return false;
        }

        WarpManager warpManager = new WarpManager(EssentialCommands.getInstance(), "warps.yml");

        warpManager.createWarp(args[0], player.getLocation());
        player.sendMessage(MessageUtility.fixColor("&aNew warp named: " + args[0] + " successfully been created&7."));
        warpManager.getWarpsFile().save();
        return true;
    }
}
