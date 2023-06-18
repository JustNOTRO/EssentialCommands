package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PowerToolCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

         if (!(sender instanceof Player player)) {
             sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
             return false;
         }

         if (!player.hasPermission("essentials.powertool")) {
             player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
             return false;
         }

        if (args.length < 1) {
            player.sendMessage(MessageUtility.fixColor("&7(Silent) &cUsage&7: &b/powertool <command>"));
            return false;
        }

        String powerToolCommand = String.join(" ", args);
        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (itemStack.getType().isAir()) {
            player.sendMessage(MessageUtility.fixColor("&cYou must be holding an item in your hand to set a powertool command&7."));
            return false;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(powerToolCommand);

        itemStack.setItemMeta(itemMeta);
        player.sendMessage(MessageUtility.fixColor("&7(Silent) &bPowertool command set for the item in your hand&7."));
        return true;
    }
}
