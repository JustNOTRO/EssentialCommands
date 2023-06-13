package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.systems.WarpManager;
import me.notro.essentialcommands.utils.MessageUtility;
import me.notro.essentialcommands.warp.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveWarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.remove.warp")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 1) {
            player.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/unwarp <name>"));
            return false;
        }

        WarpManager warpManager = new WarpManager(EssentialCommands.getInstance(), "warps.yml");
        Warp warp = new Warp(warpManager.getWarpsFile().getFileConfiguration().getName(), player.getLocation());

        player.sendMessage(MessageUtility.fixColor("&a" + warp.getName() + " has been successfully removed&7."));
        warpManager.removeWarp(warp);
        warpManager.getWarpsFile().save();
        return true;
    }
}
