package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.managers.GUIManager;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ParticleGUICommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (args.length > 0) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/particle"));
            return false;
        }

        GUIManager guiManager = new GUIManager(player);
        guiManager.createGUIMenu(9, MessageUtility.fixColor("&cParticle Wizard"));

        guiManager.createGUIItem(0, Material.BLAZE_ROD, MessageUtility.fixColor("&cSet yourself on FLAMES"), MessageUtility.fixColor("&4&lMUHAHAHA NOTRO GAMING!"));
        player.openInventory(guiManager.getInventory());
        return true;
    }
}
