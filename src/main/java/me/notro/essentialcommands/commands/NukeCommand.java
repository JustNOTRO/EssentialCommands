package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class NukeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.nuke")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.toString()));
            return false;
        }

        if (args.length < 2) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/nuke <player> <amount>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        int amount = Integer.parseInt(args[1]);

        if (target == null) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        for (int i = 0; i < amount; i++)
            player.getWorld().spawnEntity(player.getLocation().add(0, 5, 0), EntityType.PRIMED_TNT).setMetadata("tntprimed", EssentialCommands.MetadataValues.blocksBreakMetaData(true));
        return true;
    }
}
