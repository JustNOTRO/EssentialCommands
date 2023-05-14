package me.notro.essentialcommands;

import me.notro.essentialcommands.listeners.*;
import lombok.Getter;
import me.notro.essentialcommands.commands.*;
import me.notro.essentialcommands.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public final class EssentialCommands extends JavaPlugin {

    @Getter
    private static EssentialCommands instance;

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(Message.fixColor("&7[&b&lEssential Commands &7] &8>> &7is &aOnline."));

        //Spigot Commands
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("build").setExecutor(new BuildCommand());
        getCommand("clear").setExecutor(new ClearInventoryCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("effect").setExecutor(new EffectCommand());
        getCommand("enchant").setExecutor(new EnchantCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("freeze").setExecutor(new FreezeCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("give").setExecutor(new GiveCommand());
        getCommand("tpbow").setExecutor(new GiveTeleportBowCommand());
        getCommand("god").setExecutor(new GodCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("hologram").setExecutor(new HologramCommand());
        getCommand("kick").setExecutor(new KickCommand());
        getCommand("killall").setExecutor(new KillAllCommand());
        getCommand("kit").setExecutor(new KitCommand());
        getCommand("msg").setExecutor(new MessageCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("teleport").setExecutor(new TeleportCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("time").setExecutor(new TimeCommand());
        getCommand("weather").setExecutor(new WeatherCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("push").setExecutor(new PushCommand());
        getCommand("speed").setExecutor(new SpeedCommand());
        getCommand("staffchat").setExecutor(new StaffChatCommand());
        getCommand("tpall").setExecutor(new TeleportAllCommand());
        getCommand("wear").setExecutor(new WearCommand());
        getCommand("effect").setExecutor(new EffectCommand());
        getCommand("unban").setExecutor(new UnbanCommand());
        getCommand("ping").setExecutor(new PingCommand());
        getCommand("nuke").setExecutor(new NukeCommand());
        getCommand("mute").setExecutor(new MuteCommand());
        getCommand("unmute").setExecutor(new UnmuteCommand());
        getCommand("whitelist").setExecutor(new WhitelistCommand());
        getCommand("mutechat").setExecutor(new MutechatCommand());
        getCommand("particle").setExecutor(new ParticleGUICommand());
        getCommand("summon").setExecutor(new SummonCommand());
        getCommand("tpsword").setExecutor(new TeleportSwordCommand());
        getCommand("smite").setExecutor(new SmiteCommand());
        getCommand("sign").setExecutor(new SignCommand());

        //Spigot Listeners
        getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new EntityExplodeListener(), this);
        getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerPreLoginListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerToggleFlightListener(), this);
        getServer().getPluginManager().registerEvents(new ProjectileHitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerFishListener(), this);
        getServer().getPluginManager().registerEvents(new EntityPickupItemListener(), this);
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
