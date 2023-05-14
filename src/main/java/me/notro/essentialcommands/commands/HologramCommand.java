package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.systems.HologramManager;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HologramCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.hologram")) {
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 2) {
            player.sendMessage(Message.fixColor("&7(Silent) &cUsage&7: &b/hologram <player> <text>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(Message.fixColor(Message.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        HologramManager hologramManager = new HologramManager(player);

        switch (args[1].toLowerCase()) {
            case "1" -> {

                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 2; i < args.length; i++) stringBuilder.append(args[i]).append(" ");

                hologramManager.createArmorStand(target.getLocation(), stringBuilder.toString());
            }
            case "2" -> {
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 2; i < args.length; i++) stringBuilder.append(args[i]).append(" ");


                hologramManager.createArmorStand(target.getLocation().add(0, 0.5, 0), stringBuilder.toString());
            }
            default -> player.sendMessage(Message.fixColor("&7(Silent) &cUsage&7: &b/hologram <player> <text>"));
        }
        return true;
    }
}
