package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.MessageUtility;
import me.notro.essentialcommands.utils.SoundsUtility;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler
    public void onPlayerMuted(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection punishmentSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("punishments.mute");
        String message = punishmentSection.getString("message");

        if (punishmentSection.getStringList("players").contains(player.getUniqueId().toString())) {
            player.sendMessage(message);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerMuteChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection muteChatSection = EssentialCommands.getInstance().getConfig().getConfigurationSection("mute-chat");

        if (player.hasPermission("essentials.mutechat.bypass")) return;
        if (muteChatSection.getBoolean("chat-muted")) {
            player.sendMessage(MessageUtility.fixColor("&cThe chat is currently muted&7."));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerMention(AsyncPlayerChatEvent event) {
        Player target = event.getPlayer();
        if (event.getMessage().equals(target.getName())) SoundsUtility.playSound(target, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
    }

    @EventHandler
    public void onPlayerSocialSpy(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String[] args = event.getMessage().split(" ");

        ConfigurationSection socialSpySection = EssentialCommands.getInstance().getConfig().getConfigurationSection("social-spies");
        List<String> socialSpies = socialSpySection.getStringList("targeted-players");
        StringBuilder stringBuilder = new StringBuilder();

        for (String arg : args) stringBuilder.append(arg).append(" ");

        if (socialSpies.contains(player.getUniqueId().toString())) player.sendMessage(MessageUtility.fixColor("&7[Social Spy] &c" + player.getName() + ": " + stringBuilder));
    }
}