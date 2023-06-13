package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class RulesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.rules")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/rules <add/remove/list>"));
            return false;
        }

        List<String> rules = new ArrayList<>();
        rules.add("1. Do not swear");
        rules.add("2. Do not send NSFW");
        rules.add("3. Subscribe to Technoblade");
        rules.add("4. Kill me now omg uhhh");

        sender.sendMessage(MessageUtility.fixColor("&aServer Rules&7: &3" + rules));
        return true;
    }
}
