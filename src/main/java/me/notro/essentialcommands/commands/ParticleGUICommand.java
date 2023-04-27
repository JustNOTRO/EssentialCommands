package me.notro.essentialcommands.commands;

import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ParticleGUICommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(Message.fixColor(Message.NO_SENDER_EXECUTOR.getDefaultMessage()));
            return false;
        }

        Inventory particleInventory = Bukkit.createInventory(player, 9, Message.fixColor("&cParticle Wizard"));

        ItemStack flameItemStack = new ItemStack(Material.BLAZE_ROD);
        ItemMeta flameItemMeta = flameItemStack.getItemMeta();
        flameItemMeta.setDisplayName(Message.fixColor("&4THE DEVIL FLAMES"));
        List<String> particleLore = new ArrayList<>();
        particleLore.add(Message.fixColor("&cSET YOURSELF ON FLAMES"));
        flameItemMeta.setLore(particleLore);
        flameItemStack.setItemMeta(flameItemMeta);

        ItemStack cancelItemStack = new ItemStack(Material.BARRIER);
        ItemMeta cancelItemMeta = cancelItemStack.getItemMeta();
        cancelItemMeta.setDisplayName(Message.fixColor("&cCANCEL PARTICLE"));
        List<String> cancelParticleLore = new ArrayList<>();
        cancelParticleLore.add(Message.fixColor("&cCANCEL YOUR CURRENT PARTICLE"));
        cancelItemMeta.setLore(cancelParticleLore);
        cancelItemStack.setItemMeta(cancelItemMeta);

        particleInventory.setItem(0, flameItemStack);
        particleInventory.setItem(8, cancelItemStack);
        player.openInventory(particleInventory);
        return true;
    }
}
