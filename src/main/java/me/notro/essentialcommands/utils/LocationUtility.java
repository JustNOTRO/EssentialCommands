package me.notro.essentialcommands.utils;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

public class LocationUtility {

    public static void setLocation(Location location, ConfigurationSection configurationSection) {
        configurationSection.set("world", location.getWorld().getName());
        configurationSection.set("x", location.getX());
        configurationSection.set("y", location.getY());
        configurationSection.set("z", location.getZ());
        configurationSection.set("pitch", location.getPitch());
        configurationSection.set("yaw", location.getYaw());
    }

    public static Location getLocation(ConfigurationSection configurationSection) {
        return configurationSection.getLocation("location");
    }
}
