package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class StaffListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.staff.list")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage())));
            return false;
        }

        if (args.length > 0) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/stafflist"));
            return false;
        }

        List<String> staffMembers = new ArrayList<>();

        Bukkit.getOnlinePlayers()
                .stream()
                .filter(players -> players.hasPermission("essentials.staff.member"))
                .forEach(players -> staffMembers.add(players.getName()));

        if (staffMembers.isEmpty()) {
            sender.sendMessage(MessageUtility.fixColor("&cNo staff are currently online&7."));
            return false;
        }

        sender.sendMessage(MessageUtility.fixColor("&3Online Staff&7: &7[&b" + staffMembers + "&7]."));
        return true;
    }
}
