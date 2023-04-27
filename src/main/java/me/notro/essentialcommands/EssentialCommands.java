package me.notro.essentialcommands;

import lombok.AccessLevel;
import lombok.Setter;
import me.notro.essentialcommands.listeners.*;
import lombok.Getter;
import me.notro.essentialcommands.commands.*;
import me.notro.essentialcommands.utils.Config;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public final class EssentialCommands extends JavaPlugin {

    @Getter
    private static EssentialCommands instance;

    @Getter
    @Setter(value = AccessLevel.PACKAGE)
    private Config punishmentsConfig;

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(Message.fixColor("&7[&b&lEssential Commands &7] &8>> &7is &aOnline."));

        //Spigot Commands
        Bukkit.getPluginCommand("heal").setExecutor(new HealCommand());
        Bukkit.getPluginCommand("feed").setExecutor(new FeedCommand());
        Bukkit.getPluginCommand("god").setExecutor(new GodCommand());
        Bukkit.getPluginCommand("give").setExecutor(new GiveCommand());
        Bukkit.getPluginCommand("vanish").setExecutor(new VanishCommand());
        Bukkit.getPluginCommand("clear").setExecutor(new ClearInventoryCommand());
        Bukkit.getPluginCommand("teleport").setExecutor(new TeleportCommand());
        Bukkit.getPluginCommand("setspawn").setExecutor(new SetSpawnCommand());
        Bukkit.getPluginCommand("spawn").setExecutor(new SpawnCommand());
        Bukkit.getPluginCommand("fly").setExecutor(new FlyCommand());
        Bukkit.getPluginCommand("time").setExecutor(new TimeCommand());
        Bukkit.getPluginCommand("freeze").setExecutor(new FreezeCommand());
        Bukkit.getPluginCommand("gamemode").setExecutor(new GamemodeCommand());
        Bukkit.getPluginCommand("weather").setExecutor(new WeatherCommand());
        Bukkit.getPluginCommand("kit").setExecutor(new KitCommand());
        Bukkit.getPluginCommand("push").setExecutor(new PushCommand());
        Bukkit.getPluginCommand("speed").setExecutor(new SpeedCommand());
        Bukkit.getPluginCommand("enchant").setExecutor(new EnchantCommand());
        Bukkit.getPluginCommand("staffchat").setExecutor(new StaffChatCommand());
        Bukkit.getPluginCommand("build").setExecutor(new BuildCommand());
        Bukkit.getPluginCommand("tpall").setExecutor(new TeleportAllCommand());
        Bukkit.getPluginCommand("wear").setExecutor(new WearCommand());
        Bukkit.getPluginCommand("killall").setExecutor(new KillAllCommand());
        Bukkit.getPluginCommand("kick").setExecutor(new KickCommand());
        Bukkit.getPluginCommand("effect").setExecutor(new EffectCommand());
        Bukkit.getPluginCommand("ban").setExecutor(new BanCommand());
        Bukkit.getPluginCommand("unban").setExecutor(new UnbanCommand());
        Bukkit.getPluginCommand("ping").setExecutor(new PingCommand());
        Bukkit.getPluginCommand("nuke").setExecutor(new NukeCommand());
        Bukkit.getPluginCommand("mute").setExecutor(new MuteCommand());
        Bukkit.getPluginCommand("unmute").setExecutor(new UnmuteCommand());
        Bukkit.getPluginCommand("whitelist").setExecutor(new WhitelistCommand());
        Bukkit.getPluginCommand("mutechat").setExecutor(new MutechatCommand());
        Bukkit.getPluginCommand("msg").setExecutor(new MessageCommand());
        Bukkit.getPluginCommand("particle").setExecutor(new ParticleGUICommand());

        setPunishmentsConfig(Config.getConfig("punishments"));


        //Spigot Listeners
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityExplodeListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerToggleFlightListener(), this);
        Bukkit.getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerRespawnListener(), this);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage(Message.fixColor("&7[&b&lEssential Commands &7] &8>> &7is &cOffline."));
    }

    public static class MetadataValues {
        public static FixedMetadataValue blocksBreakMetaData(boolean state) {
            return new FixedMetadataValue(instance, state);
        }
    }
}
