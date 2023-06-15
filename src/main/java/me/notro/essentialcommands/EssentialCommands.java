package me.notro.essentialcommands;

import me.notro.essentialcommands.listeners.*;
import lombok.Getter;
import me.notro.essentialcommands.commands.*;
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
        getLogger().info("has been Enabled.");

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
        getCommand("smite").setExecutor(new SmiteCommand());
        getCommand("sign").setExecutor(new SignCommand());
        getCommand("kill").setExecutor(new KillCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("troll").setExecutor(new TrollCommand());
        getCommand("rtp").setExecutor(new RandomTeleportCommand());
        getCommand("setwarp").setExecutor(new SetWarpCommand());
        getCommand("unwarp").setExecutor(new RemoveWarpCommand());
        getCommand("sudo").setExecutor(new SudoCommand());
        getCommand("invsee").setExecutor(new InvseeCommand());
        getCommand("firework").setExecutor(new FireworkCommand());
        getCommand("shoot").setExecutor(new ShootCommand());
        getCommand("staffhelp").setExecutor(new StaffHelpCommand());
        getCommand("top").setExecutor(new TopCommand());
        getCommand("experience").setExecutor(new ExperienceCommand());
        getCommand("config").setExecutor(new ReloadConfigCommand());
        getCommand("tphere").setExecutor(new TphereCommand());
        getCommand("kickall").setExecutor(new KickallCommand());
        getCommand("maintenance").setExecutor(new MaintenanceCommand());
        getCommand("report").setExecutor(new ReportCommand());
        getCommand("reports").setExecutor(new ReportsCommand());
        getCommand("list").setExecutor(new ListCommand());
        getCommand("setjail").setExecutor(new SetJailCommand());
        getCommand("jail").setExecutor(new JailCommand());
        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("giveall").setExecutor(new GiveallCommand());
        getCommand("socialmedia").setExecutor(new SocialMediaCommand());
        getCommand("socialspy").setExecutor(new SocialSpyCommand());
        getCommand("playsound").setExecutor(new PlaySoundCommand());
        getCommand("motd").setExecutor(new MotdCommand());
        getCommand("pvp").setExecutor(new PvPCommand());
        getCommand("stafflist").setExecutor(new StaffListCommand());
        getCommand("clearlag").setExecutor(new ClearLagCommand());
        getCommand("gravity").setExecutor(new GravityCommand());


        //Spigot Listeners
        getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new EntityExplodeListener(), this);
        getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new AsyncPlayerPreLoginListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerToggleFlightListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerFishListener(), this);
        getServer().getPluginManager().registerEvents(new EntityPickupItemListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessListener(), this);
        getServer().getPluginManager().registerEvents(new ServerListPingListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("has been Disabled.");
    }

    public static class MetadataValues {
        public static FixedMetadataValue blocksBreakMetaData(boolean state) {
            return new FixedMetadataValue(instance, state);
        }
    }
}