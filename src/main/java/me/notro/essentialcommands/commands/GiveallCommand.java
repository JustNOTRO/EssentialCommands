package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GiveallCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("essentials.giveall")) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/giveall <material> <amount>"));
            return false;
        }

        Material material = Material.getMaterial(args[0].toUpperCase());

        if (material == null) {
            sender.sendMessage(MessageUtility.fixColor("&7(Silent) &cMaterial does not exist&7."));
            return false;
        }

        ItemStack itemStack = new ItemStack(material);

        if (itemStack.getType() == Material.AIR) {
            sender.sendMessage(MessageUtility.fixColor("&cYou cannot give AIR to all players."));
            return false;
        }

        int amount;

        if (args.length == 1) {
            itemStack.setAmount(1);
            Bukkit.getOnlinePlayers().forEach(players -> players.getInventory().addItem(itemStack));
            return true;
        }

        try {
            amount = Integer.parseInt(args[1]);
            itemStack.setAmount(amount);
        } catch (NumberFormatException exception) {
            sender.sendMessage(MessageUtility.fixColor("&7(Silent) &cAmount needs to be numeric&7."));
            return false;
        }

        if (amount <= 0) {
            sender.sendMessage(MessageUtility.fixColor("&7(Silent) &8>> &cAmount needs to be at least 1&7."));
            return false;
        }

        Bukkit.getOnlinePlayers().forEach(players -> players.getInventory().addItem(itemStack));
        sender.sendMessage(MessageUtility.fixColor("&7(Silent) &bGave all online players &3" + itemStack.getType()));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> materials = new ArrayList<>();
            Material[] material = Material.values();

            for (Material value : material) {
                materials.add(value.toString().toLowerCase());
            }
            return materials;
        }
        return null;
    }
}
