package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
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


public class KitCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.kits")) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        if (args.length > 1) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &cUsage&3: &7/kit <casual/strong>"));
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "casual" -> {
                ItemStack[] casualKit = new ItemStack[4];
                casualKit[3] = new ItemStack(Material.IRON_HELMET);
                casualKit[2] = new ItemStack(Material.IRON_CHESTPLATE); // Kit Materials
                casualKit[1] = new ItemStack(Material.IRON_LEGGINGS);
                casualKit[0] = new ItemStack(Material.IRON_BOOTS);
                ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);

                for (ItemStack content : player.getInventory().getContents()) {

                    if (!player.getInventory().isEmpty()) {
                        player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                        player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &cAnother kit is already being used."));
                        return false;
                    }
                    player.getInventory().setArmorContents(casualKit);
                    player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
                    player.getInventory().addItem(diamondSword);
                    player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &bKit &3casual &bhas been granted."));
                    return true;
                }
            }
            case "strong" -> {
                ItemStack[] strongKit = new ItemStack[4];
                strongKit[3] = new ItemStack(Material.NETHERITE_HELMET);
                strongKit[2] = new ItemStack(Material.NETHERITE_CHESTPLATE); // Kit Materials
                strongKit[1] = new ItemStack(Material.NETHERITE_LEGGINGS);
                strongKit[0] = new ItemStack(Material.NETHERITE_BOOTS);
                ItemStack netheriteSword = new ItemStack(Material.NETHERITE_SWORD);

                for (ItemStack content : player.getInventory().getContents()) {

                    if (!player.getInventory().isEmpty()) {
                        player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                        player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &cAnother kit is already being used."));
                        return false;
                    }
                    player.getInventory().setArmorContents(strongKit);
                    player.getInventory().addItem(netheriteSword);
                    player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
                    player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &bKit &3strong &bhas been granted."));
                    return true;
                }
            }
            default -> {
                player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &cKit does not exist."));
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> kitNames = new ArrayList<>();
            kitNames.add("casual");
            kitNames.add("strong");
            return kitNames;
        }
        return null;
    }
}