package dk.yzhy;

import dk.yzhy.listeners.DamageByEntityEvent;
import dk.yzhy.listeners.DeathEvent;
import dk.yzhy.listeners.QuitEvent;
import dk.yzhy.utils.Config;
import dk.yzhy.utils.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Main extends JavaPlugin {
    private static Main instance;
    public static Config mainConfig;
    public static FileConfiguration mainConfigYML;
    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new DamageByEntityEvent(), this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DeathEvent(), this);
        this.getCommand("combat").setExecutor(new dk.yzhy.commands.Combat());
        ParseType();
        LoadYAML();
    }
    @Override
    public void onDisable() {
        System.out.println("[Combat] Combat unloaded!");
    }
    private void LoadYAML() {
        if (!(new File(getDataFolder(), "config.yml")).exists())saveResource("config.yml", false);
        mainConfig = new Config(this, null, "config.yml");
        mainConfigYML = mainConfig.getConfig();
        ConfigLoader.loadALL();
    }
    private void ParseType() {
        try {
            System.setProperty("file.encoding", "UTF-8");
            Field charset = Charset.class.getDeclaredField("defaultCharset");
            charset.setAccessible(true);
            charset.set(null, null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static Main getInstance() {
        return instance;
    }

}
