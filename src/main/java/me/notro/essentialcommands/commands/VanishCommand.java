package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.systems.VanishManager;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.vanish")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        ConfigurationSection vanishSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("vanish-mode");
        List<String> vanishList = vanishSection.getStringList("players-vanished");
        VanishManager vanishManager = new VanishManager(player, vanishSection, vanishList);

        if (!vanishManager.isVanished()) {
            vanishManager.hidePlayer();
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &bYou are now vanished&7."));
            vanishSection.set("pickup-items", false);
            EssentialCommands.getInstance().saveConfig();
            return true;
        }

        vanishManager.showPlayer();
        player.sendMessage(MessageUtility.fixColor("&7(Silent) &bYou are now un-vanished&7."));
        vanishSection.set("pickup-items", true);
        EssentialCommands.getInstance().saveConfig();
        return true;
    }
}