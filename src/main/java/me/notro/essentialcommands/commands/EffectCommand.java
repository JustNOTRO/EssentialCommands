package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class EffectCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConfigurationSection soundSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("sound.commands");

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        if (!player.hasPermission("essentials.effect")) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.fixColor(Message.NO_PERMISSION.getDefaultMessage()));
            return false;
        }

        if (args.length == 0) {
            player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
            player.sendMessage(Message.NO_ARGUMENTS_PROVIDED.getDefaultMessage());
            return false;
        }

        switch (args[0].toLowerCase()) {

            case "give" -> {

                if (args.length < 5) {
                    player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                    player.sendMessage(Message.fixColor("&7(Silent) &cusage&7: &b/effect <give/clear> <player> <effectType> <duration> <amplifier>"));
                    return false;
                }

                Player target = Bukkit.getPlayer(args[1]);

                if (target == null) {
                    player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                    player.sendMessage(Message.fixColor(Message.NO_PLAYER_EXISTENCE.getDefaultMessage()));
                    return false;
                }

                PotionEffectType potionEffectType = PotionEffectType.getByName(args[2]);

                if (potionEffectType == null) {
                    player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                    player.sendMessage(Message.fixColor("&7(Silent) &ceffect does not exist."));
                    return false;
                }

                int duration = Integer.parseInt(args[3]);
                int amplifier = Integer.parseInt(args[4]);

                target.addPotionEffect(new PotionEffect(potionEffectType, duration, amplifier));
                player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
                player.sendMessage(Message.fixColor("&7(Silent) &7added &b " + target.getName() + "&3 " + potionEffectType.getName().toLowerCase() + "&7."));
            }
            case "clear" -> {

                if (player.getActivePotionEffects().isEmpty()) {
                    player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("rejected")), 1, 1);
                    player.sendMessage(Message.fixColor("&7(Silent) &cYou don't have any active potions&7."));
                    return false;
                }

                player.getActivePotionEffects().forEach(activePotion -> {
                    player.removePotionEffect(activePotion.getType());
                    player.playSound(player.getLocation(), Sound.valueOf(soundSection.getString("allowed")), 1, 1);
                    player.sendMessage(Message.fixColor("&7(Silent) &7removed &b " + player.getName() + "&3 " + activePotion.getType().getName().toLowerCase() + "&7."));
                });
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            List<String> options = new ArrayList<>();
            options.add("give");
            options.add("clear");
            return options;
        }

        if (args.length == 2) {
            List<String> players = new ArrayList<>();
            Player[] allPlayers = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(allPlayers);

            for (int i = 0; i < allPlayers.length; i++) {
                players.add(allPlayers[i].getName());
            }
            return players;
        }

        if (args.length == 3) {
            List<String> effects = new ArrayList<>();
            PotionEffectType[] effectTypes = PotionEffectType.values();

            for (PotionEffectType e : effectTypes) {
                effects.add(e.getName().toLowerCase());
            }
            return effects;
        }
        return null;
    }
}