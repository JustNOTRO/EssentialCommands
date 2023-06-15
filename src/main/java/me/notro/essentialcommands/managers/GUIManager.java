package me.notro.essentialcommands.managers;

import lombok.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class GUIManager {

    private final Player player;
    private Inventory inventory;

    public void createGUIMenu(int size, String displayName) {
        inventory = Bukkit.createInventory(player, size, displayName);
    }

    public void createGUIItem(int index, @NonNull Material material, @NonNull String displayName, @Nullable String lore) {
        ItemStack itemStack = new ItemStack(material);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);

        List<String> loreList = new ArrayList<>();
        loreList.add(lore);

        itemMeta.setLore(loreList);
        itemStack.setItemMeta(itemMeta);
       inventory.setItem(index, itemStack);
    }
}