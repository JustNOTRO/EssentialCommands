package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.ServerOperator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OpListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.op.list")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length > 0) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/oplist"));
            return false;
        }

        List<String> opPlayers = new ArrayList<>();

        Arrays.stream(Bukkit.getOfflinePlayers())
                .filter(ServerOperator::isOp)
                .forEach(player -> opPlayers.add(player.getName()));

        sender.sendMessage(MessageUtility.fixColor("&bOp Players&7: &3" + opPlayers + "&7."));
        return true;
    }
}
