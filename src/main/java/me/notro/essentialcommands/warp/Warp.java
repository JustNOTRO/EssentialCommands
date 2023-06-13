package me.notro.essentialcommands.warp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@AllArgsConstructor
@Getter
public class Warp {
    private final String name;
    @Setter private Location location;
}
