package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;

public class FreezeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.freeze")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        ConfigurationSection freezeSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("freeze-mode");
        List<String> freezedPlayers = freezeSection.getStringList("players");

        if (!freezeSection.getStringList("players").contains(target.getUniqueId().toString())) {
            freezedPlayers.add(target.getUniqueId().toString());
            freezeSection.set("players", freezedPlayers);
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &3" + target.getName() + " &bhas been frozen by &3" + player.getName() + "&7."));
            EssentialCommands.getInstance().saveConfig();
            return true;
        }

        freezedPlayers.remove(target.getUniqueId().toString());
        freezeSection.set("players", freezedPlayers);
        player.sendMessage(MessageUtility.fixColor("&7(Silent) &3" + target.getName() + " &bhas been unfrozen by &3" + player.getName() + "&7."));
        EssentialCommands.getInstance().saveConfig();
        return true;
    }
}
