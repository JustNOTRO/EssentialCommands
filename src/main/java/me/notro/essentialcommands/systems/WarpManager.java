package me.notro.essentialcommands.systems;

import lombok.Getter;
import lombok.NonNull;
import me.notro.essentialcommands.CustomConfiguration;
import me.notro.essentialcommands.EssentialCommands;
import me.notro.essentialcommands.utils.LocationUtility;
import me.notro.essentialcommands.warp.Warp;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashSet;
import java.util.Set;

@Getter
public class WarpManager {

    private final Set<Warp> warps = new HashSet<>();
    private final CustomConfiguration warpsFile;

    public WarpManager(EssentialCommands plugin, String name) {
        this.warpsFile = new CustomConfiguration(plugin, name);

        loadWarps();
    }

    public void createWarp(@NonNull String name, @NonNull Location location) {
        Warp warp = new Warp(name, location);
        warpsFile.getFileConfiguration().set("warps." + name + ".name", name);
        LocationUtility.setLocation(location, warpsFile.getFileConfiguration().createSection("warps." + name + ".location"));

        warps.add(warp);
        warpsFile.save();
    }

    public void removeWarp(@NonNull Warp warp) {
        warps.remove(warp);
        warpsFile.getFileConfiguration().set("warps." + warp.getName(), null);
        warpsFile.getFileConfiguration().set("warps. " + warp.getLocation(), null);
        warpsFile.save();
    }

    private void loadWarps() {
        if (warpsFile.getFileConfiguration().getConfigurationSection("warps") == null) return;

        for (String key : warpsFile.getFileConfiguration().getConfigurationSection("warps.").getKeys(false)) {
            ConfigurationSection warpSection = warpsFile.getFileConfiguration().getConfigurationSection("warps." + key);
            if (warpSection == null) continue;

            warps.add(new Warp(warpSection.getString("name"), LocationUtility.getLocation(warpSection)));
        }
    }
}