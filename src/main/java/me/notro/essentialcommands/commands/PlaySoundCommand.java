package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.MessageUtility;
import me.notro.essentialcommands.utils.SoundsUtility;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlaySoundCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(MessageUtility.fixColor(MessageUtility.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.sound")) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length < 4) {
            player.sendMessage(MessageUtility.fixColor("&cUsage&7: &b/playsound <player> <sound> <volume> <pitch>"));
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(MessageUtility.fixColor(MessageUtility.NO_PLAYER_EXISTENCE.getDefaultMessage()));
            return false;
        }

        Sound sound = Sound.valueOf(args[1]);
        float volume;
        float pitch;

        try {
            volume = Float.parseFloat(args[2]);
            pitch = Float.parseFloat(args[3]);
        } catch (NumberFormatException exception) {
            player.sendMessage(MessageUtility.fixColor("&cVolume/Pitch must be numeric&7."));
            return false;
        }

        if (volume <= 0) {
            player.sendMessage(MessageUtility.fixColor("&cVolume must be at least 1&7."));
            return false;
        }

        SoundsUtility.playSound(target, sound, volume, pitch);
        player.sendMessage(MessageUtility.fixColor("&7(Silent) &bPlayed &3" + sound.name() + " &bto &3" + player.getName() + "&7."));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 2) {
            List<String> soundList = new ArrayList<>();
            Sound[] sounds = Sound.values();

            for (Sound sound : sounds) soundList.add(sound.name());

            return soundList;
        }
        return null;
    }
}
