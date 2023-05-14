package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SignCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essential.sign")) {
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(Message.fixColor("&cUsage&7: &b/sign <player> <amount>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(Message.fixColor(Message.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        ItemStack itemStack = new ItemStack(Material.OAK_SIGN);

        if (args.length == 1) {
            itemStack.setAmount(1);
            player.getInventory().addItem(itemStack);
            return true;
        }

        int amount = Integer.parseInt(args[1]);

        itemStack.setAmount(amount);
        target.getInventory().addItem(itemStack);
        target.sendMessage(Message.fixColor("&bGranted &3" + target.getName() + " &ba sign&7."));
        return true;
    }
}
