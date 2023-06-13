package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WearCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof  Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.wear")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length > 0) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/wear"));
            return false;
        }

        if (player.getInventory().getItemInMainHand().getItemMeta() == null) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cPlayer is not holding anything&7."));
            return false;
        }

        ItemStack helmetItemStack = player.getInventory().getItemInMainHand();
        player.getInventory().setHelmet(helmetItemStack);
        player.getInventory().removeItem(helmetItemStack);
        player.sendMessage(MessageUtility.fixColor("&7(Silent) &3" + player.getName() + " &bis now wearing a &3" + player.getInventory().getHelmet().getType().toString().toLowerCase()+ "&7."));
        return true;
    }
}
