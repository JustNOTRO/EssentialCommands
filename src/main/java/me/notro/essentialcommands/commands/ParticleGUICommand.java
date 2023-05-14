package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.systems.GUIManager;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ParticleGUICommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (args.length > 0) {
            player.sendMessage(Message.fixColor("&7(Silent) &cUsage&7: &b/particle"));
            return false;
        }

        GUIManager guiManager = new GUIManager(player, player.getInventory());
        guiManager.createGUIMenu(9, Message.fixColor("&cParticle Wizard"));

        ItemStack blaze = new ItemStack(Material.BLAZE_ROD);
        List<String> blazeLore = new ArrayList<>();
        blazeLore.add(Message.fixColor("&4&lMUAHHAAH NOTRO GAMING!"));

        guiManager.createGUIItems(0, blaze, Message.fixColor("&cSet yourself on FLAMES"), blazeLore);
        player.openInventory(guiManager.getInventory());
        return true;
    }
}
