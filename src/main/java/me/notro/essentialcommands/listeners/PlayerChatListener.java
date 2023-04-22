package me.notro.essentialcommands.listeners;

import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.systems.MuteChat;
import me.notro.essentialcommands.utils.Config;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        Config config = EssentialCommands.getInstance().getPunishmentsConfig();
        ConfigurationSection punishmentSection = config.getConfigurationSection(player.getUniqueId().toString());
        if (punishmentSection != null) {
            player.sendMessage(Message.fixColor("&7[&b&lEssential Commands&7] &8>> &cYou are muted!"));
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerMuteChat(PlayerChatEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("essentials.mutechat.bypass")) return;
        if (MuteChat.muteChatAffectedPlayers.contains(player.getUniqueId())) event.setCancelled(true);
    }
}
