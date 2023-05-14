package me.notro.essentialcommands.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemStackCreationUtils {

    public static ItemStack createTeleportBow() {
        ItemStack bowItem = new ItemStack(Material.BOW);
        ItemMeta bowMeta = bowItem.getItemMeta();

        bowMeta.setDisplayName(Message.fixColor("&9Teleport Bow"));
        bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);

        List<String> bowLore = new ArrayList<>();
        bowLore.add(Message.fixColor("&eShoot an arrow to teleport"));

        bowMeta.setLore(bowLore);
        bowItem.setItemMeta(bowMeta);
        return bowItem;
    }

    public static ItemStack createTeleportSword() {
        ItemStack swordItem = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta swordMeta = swordItem.getItemMeta();

        swordMeta.setDisplayName(Message.fixColor("&9Aspect of the End"));

        List<String> swordLore = new ArrayList<>();
        swordLore.add(Message.fixColor("&6Ability: Instant Transmission &e&lRIGHT CLICK"));
        swordLore.add(Message.fixColor("&9&lRARE SWORD"));

        swordMeta.setLore(swordLore);
        swordItem.setItemMeta(swordMeta);
        return swordItem;
    }

    public static ItemStack createGrapplingHook() {
        ItemStack itemStack = new ItemStack(Material.FISHING_ROD);
        ItemMeta meta = itemStack.getItemMeta();

        meta.setDisplayName(Message.fixColor("&aGrappling Hook"));
        List<String> lore = new ArrayList<>();
        lore.add(Message.fixColor("&eIn order to get pulled out &6&lRIGHT CLICK"));

        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
