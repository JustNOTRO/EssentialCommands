package me.notro.essentialcommands.systems;

import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class KitManager {

    private final Player player;

    public void createKit(ItemStack[] itemStack, Material[] materials, @Nullable final String kitName, @Nullable String lore, @Nullable Material weaponMaterials) {
        itemStack[3] = new ItemStack(materials[3]);
        itemStack[2] = new ItemStack(materials[2]);
        itemStack[1] = new ItemStack(materials[1]);
        itemStack[0] = new ItemStack(materials[0]);

        for (int i = 3; i > -1; i--) {
            ItemMeta itemMeta = itemStack[i].getItemMeta();
            itemMeta.setDisplayName(kitName);

            List<String> kitLore = new ArrayList<>();
            kitLore.add(lore);

            itemMeta.setLore(kitLore);
            itemStack[i].setItemMeta(itemMeta);
        }

        player.getInventory().setArmorContents(itemStack);
        player.getInventory().addItem(new ItemStack(weaponMaterials));
    }
}