package dk.yzhy;

import dk.yzhy.listeners.DamageByEntityEvent;
import dk.yzhy.listeners.DeathEvent;
import dk.yzhy.listeners.QuitEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Combat extends JavaPlugin {
    private static Combat instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new DamageByEntityEvent(), this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DeathEvent(), this);
        this.getCommand("combat").setExecutor(new dk.yzhy.commands.Combat());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static Combat getInstance() {
        return instance;
    }

}
