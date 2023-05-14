package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.ItemStackCreationUtils;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TeleportSwordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.teleport.sword")) {
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.sendMessage(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage());
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        ItemStack teleportSword = ItemStackCreationUtils.createTeleportSword();

        if (target == null) {
            player.sendMessage(Message.fixColor(Message.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }
        target.getInventory().addItem(teleportSword);
        target.sendMessage(Message.fixColor("&9Aspect of the End"));
        return true;
    }
}
