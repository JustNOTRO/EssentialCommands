package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
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

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.give")) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        if (args.length > 3 ) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor("&7(Silent) &8>> &cusage&7: &b/give <player> <item> <count>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        Material material = Material.getMaterial(args[1].toUpperCase());
        int itemAmount = Integer.parseInt(args[2]);

        if (material == null) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor("&7(Silent) &8>> &cmaterial does not exist&7."));
            return false;
        }

        ItemStack itemStack = new ItemStack(material);
        itemStack.setAmount(itemAmount);

        if (itemStack.getAmount() == 0) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor("&7(Silent) &8>> &camount needs to be at least 1&7."));
            return false;
        }
        target.getInventory().addItem(itemStack);
        player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
        player.sendMessage(Message.fixColor("&7(Silent) &8>> &bgave &3" + target.getName() + " &b" + itemStack.getType().toString().toLowerCase() + "&7."));
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
