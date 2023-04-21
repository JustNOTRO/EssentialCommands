package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;


public class NukeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.nuke")) {
            Message.playSound(player, Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.toString()));
            return false;
        }

        if (args.length == 0) {
            Message.playSound(player, Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage()));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            Message.playSound(player, Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }
        for (int i = 0; i < 10; i++)
            player.getWorld().spawnEntity(player.getLocation().add(0, 5, 0), EntityType.PRIMED_TNT).setMetadata("tntprimed", EssentialCommands.MetadataValues.blocksBreakMetaData(true));

        return true;
    }
}
