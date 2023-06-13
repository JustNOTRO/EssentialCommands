package me.notro.essentialcommands;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfiguration {

    private final File configurationFile;
    @Getter private final FileConfiguration fileConfiguration;

    private final EssentialCommands plugin;
    private final String fileName;

    public CustomConfiguration(@NonNull EssentialCommands plugin, @NonNull String name) {
        this.plugin = plugin;
        this.fileName = name;

        configurationFile = new File(plugin.getDataFolder(), name);

        if (!configurationFile.exists()) {
            configurationFile.getParentFile().mkdirs();
            plugin.saveResource(name, false);
        }

        fileConfiguration = new YamlConfiguration();
        YamlConfiguration.loadConfiguration(configurationFile);
    }

    public void save() {
        try {
            fileConfiguration.save(configurationFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
