package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BuildCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.build")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 1) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/build <enable/disable>"));
            return false;
        }

        ConfigurationSection buildSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("build");
        List<String> playersBuilding = buildSection.getStringList("players-building");

        switch (args[0].toLowerCase()) {
            case "disable" -> {
                playersBuilding.add(player.getUniqueId().toString());
                buildSection.set("players-building", playersBuilding);
                player.sendMessage(MessageUtility.fixColor("&7(Silent) &bBuild has been &3disabled&7."));
                EssentialCommands.getInstance().saveConfig();
            }

            case "enable" -> {
                playersBuilding.remove(player.getUniqueId().toString());
                buildSection.set("players-building", playersBuilding);
                player.sendMessage(MessageUtility.fixColor("&7(Silent) &bBuild has been &3enabled&7."));
                EssentialCommands.getInstance().saveConfig();
            }

            default -> player.sendMessage(MessageUtility.fixColor("&7(Silent) &bUnknown build status try again&7."));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> buildMode = new ArrayList<>();
            buildMode.add("enable");
            buildMode.add("disable");
            return buildMode;
        }
        return null;
    }
}
