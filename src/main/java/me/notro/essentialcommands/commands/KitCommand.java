package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.systems.KitManager;
import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class KitCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.kit")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/kit <kit-type>"));
            return false;
        }

        ItemStack[] casualKit = new ItemStack[4];
        ItemStack[] strongKit = new ItemStack[4];
        Material[] materials = new Material[4];

        if (!player.getInventory().isEmpty()) {
            player.sendMessage(MessageUtility.fixColor("&cYou need to clear your inventory first!"));
            return false;
        }

        KitManager kitManager = new KitManager(player);

        switch (args[0].toLowerCase()) {
            case "casual" -> {
                materials[3] = Material.IRON_HELMET;
                materials[2] = Material.IRON_CHESTPLATE;
                materials[1] = Material.IRON_LEGGINGS;
                materials[0] = Material.IRON_BOOTS;

                kitManager.createKit(casualKit, materials, MessageUtility.fixColor("&eCasual Kit"), MessageUtility.fixColor("&eThis kit is for casuals haha!"), Material.DIAMOND_SWORD);
                player.sendMessage(MessageUtility.fixColor("&bKit &3Casual &bhas been granted&7."));
            }
            case "strong" -> {
                materials[3] = Material.NETHERITE_HELMET;
                materials[2] = Material.NETHERITE_CHESTPLATE;
                materials[1] = Material.NETHERITE_LEGGINGS;
                materials[0] = Material.NETHERITE_BOOTS;

                kitManager.createKit(strongKit, materials, MessageUtility.fixColor("&4Strong Kit"), MessageUtility.fixColor("&cThat kit is very strong!"), Material.NETHERITE_SWORD);
                player.sendMessage(MessageUtility.fixColor("bKit &3Strong &bhas been granted&7."));
            }

            default -> player.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/kit <kit-type>"));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> kits = new ArrayList<>();
            kits.add("casual");
            kits.add("strong");
            return kits;
        }
        return null;
    }
}
