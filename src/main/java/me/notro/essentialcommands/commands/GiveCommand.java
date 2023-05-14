package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GiveCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");

        if (!sender.hasPermission("essentials.give")) {
            sender.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage(Message.fixColor("&7(Silent) &cUsage&7: &b/give <player> <item> <amount>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(Message.fixColor(Message.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        Material material = Material.getMaterial(args[1].toUpperCase());

        if (material == null) {
            sender.sendMessage(Message.fixColor("&7(Silent) &cMaterial does not exist&7."));
            return false;
        }

        ItemStack itemStack = new ItemStack(material);

        if (args.length == 2) {
            itemStack.setAmount(1);
            target.getInventory().addItem(itemStack);
            return true;
        }

        int itemAmount;

        try {
            itemAmount = Integer.parseInt(args[2]);
            itemStack.setAmount(itemAmount);
        } catch (NumberFormatException exception) {
            sender.sendMessage(Message.fixColor("&7(Silent) &cAmount needs to be numeric&7."));
            return false;
        }

        if (itemAmount <= 0) {
            sender.sendMessage(Message.fixColor("&7(Silent) &8>> &cAmount needs to be at least 1&7."));
            return false;
        }
        target.getInventory().addItem(itemStack);
        sender.sendMessage(Message.fixColor("&7(Silent) &8>> &bGave &3" + target.getName() + " &b" + itemStack.getType().toString().toLowerCase() + "&7."));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> players = new ArrayList<>();
            Player[] allPlayers = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(allPlayers);

            for (int i = 0; i < allPlayers.length; i++) {
                players.add(allPlayers[i].getName());
            }
            return players;
        }

        if (args.length == 2) {
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
