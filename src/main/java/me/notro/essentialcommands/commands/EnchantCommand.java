package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EnchantCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.enchant")) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        if (args.length < 2) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor("&7(Silent) &cusage&7: &b/enchant <enchantment> <level>"));
            return false;
        }

        Enchantment enchantment = Enchantment.getByName(args[0].toUpperCase());
        int enchantmentLevel = Integer.parseInt(args[1]);
        ItemMeta enchantmentMeta = player.getInventory().getItemInMainHand().getItemMeta();
        
        if (enchantmentMeta == null) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor("&7(Silent) &b" + player.getName() + " &cis not holding anything&7."));
            return false;
        }

        if (enchantment == null) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor("&7(Silent) &cenchantment does not exist&7."));
            return false;
        }
        enchantmentMeta.addEnchant(enchantment, enchantmentLevel, true);
        player.getInventory().getItemInMainHand().setItemMeta(enchantmentMeta);
        player.sendMessage(Message.fixColor("&7(Silent) &bset &3" + enchantment.getName().toLowerCase() + "&b " + enchantmentLevel + "&b."));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> enchantments = new ArrayList<>();
            Enchantment[] enchantment = Enchantment.values();

            for (Enchantment value : enchantment) {
                enchantments.add(value.getName().toLowerCase());
            }
            return enchantments;
        }
        return null;
    }
}
