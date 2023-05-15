package dk.yzhy.listeners;

import dk.yzhy.utils.API;
import dk.yzhy.utils.ConfigLoader;
import dk.yzhy.utils.SeeCombat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageByEntityEvent implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (Boolean.parseBoolean(ConfigLoader.getString("Combat.Enabled"))) {
            if (!event.isCancelled()) {
                if (event.getEntity() instanceof Player) {
                    Player victim = (Player) event.getEntity();
                    Player attacker;
                    if (!(event.getDamager() instanceof Player)) {
                        attacker = (Player) (((Projectile) event.getDamager()).getShooter());
                    } else {
                        attacker = (Player) event.getDamager();
                    }
                    Integer time = ConfigLoader.getInt("Combat.Tid");
                    if ((!victim.hasPermission(ConfigLoader.getString("Bypass.Permission")) && API.getCombat(victim) < time) && !victim.hasMetadata("CancelCombat") && attacker != victim) {
                        SeeCombat.seeCombat(victim.getPlayer(), time);
                    }
                    if ((!attacker.hasPermission(ConfigLoader.getString("Bypass.Permission")) && (API.getCombat(attacker) < time) && !attacker.hasMetadata("CancelCombat"))) {
                        SeeCombat.seeCombat(attacker.getPlayer(), time);
                    }
                }
            }
        }
    }
}
