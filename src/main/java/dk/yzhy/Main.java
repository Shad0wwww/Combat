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
        LoadYAML();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private void LoadYAML() {
        if (!(new File(getDataFolder(), "config.yml")).exists())saveResource("config.yml", false);
        mainConfig = new Config(this, null, "config.yml");
        mainConfigYML = mainConfig.getConfig();
        ConfigLoader.loadALL();
    }
    public static Main getInstance() {
        return instance;
    }

}
