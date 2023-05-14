package me.notro.essentialcommands.systems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class GUIManager {

    private Player player;
    private Inventory inventory;

    public Inventory createGUIMenu(int size, String displayName) {
        inventory = Bukkit.createInventory(player, size, displayName);

        return inventory;
    }

    public void createGUIItems(int index, ItemStack itemStack, String displayName, @Nullable List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
       getInventory().setItem(index, itemStack);
    }
}
