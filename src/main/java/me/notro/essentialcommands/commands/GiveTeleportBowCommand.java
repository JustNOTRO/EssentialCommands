package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.Message;
import me.notro.essentialcommands.utils.ItemStackCreationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveTeleportBowCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.teleport.bow")) {
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        ItemStack teleportBow = ItemStackCreationUtils.createTeleportBow();

        target.getInventory().addItem(teleportBow);
        target.getInventory().addItem(new ItemStack(Material.ARROW));
        target.sendMessage(Message.fixColor("&aSuccessfully added Teleport Bow!"));
        return true;
    }
}
