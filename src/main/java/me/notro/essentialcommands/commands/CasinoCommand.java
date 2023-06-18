package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class CasinoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.casino")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length > 0) {
            player.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/casino"));
            return false;
        }

        Random random = new Random();
        Material[] materials = Material.values();
        Material randomMaterial = materials[random.nextInt(materials.length)];

        if (!randomMaterial.isItem()) {
            player.sendMessage(MessageUtility.fixColor("&cWhoops you didn't won anything!"));
            return false;
        }

        ItemStack itemStack = new ItemStack(randomMaterial);

        player.getInventory().addItem(itemStack);
        player.sendMessage(MessageUtility.fixColor("&bYou got &3" + itemStack.getType() + "&7."));
        return true;
    }
}
