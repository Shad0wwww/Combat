package dk.yzhy.listeners;

import dk.yzhy.utils.API;
import dk.yzhy.utils.ConfigLoader;
import dk.yzhy.utils.SeeCombat;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageByEntityEvent implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player){
            if (Boolean.parseBoolean(ConfigLoader.getString("Combat.Enabled")) && !event.isCancelled()) {
                Player victim = (Player) event.getEntity();
                Player attacker;
                if (!(event.getDamager() instanceof Player)) {
                    attacker = (Player) (((Projectile) event.getDamager()).getShooter());
                } else {
                    attacker = (Player) event.getDamager();
                }
                int time = ConfigLoader.getInt("Combat.Tid");
                boolean bypassEnabled = Boolean.parseBoolean(ConfigLoader.getString("Bypass.Enabled"));
                if (API.getCombat(victim) < time && attacker != victim) {
                    if (!bypassEnabled || (!victim.hasPermission(ConfigLoader.getString("Bypass.Permission")))) {
                        SeeCombat.seeCombat(victim.getPlayer(), time);
                    }
                }
                if (API.getCombat(attacker) < time) {
                    if (!bypassEnabled || (!attacker.hasPermission(ConfigLoader.getString("Bypass.Permission")))) {
                        SeeCombat.seeCombat(attacker.getPlayer(), time);
                    }
                }
            }
        }
    }
}
