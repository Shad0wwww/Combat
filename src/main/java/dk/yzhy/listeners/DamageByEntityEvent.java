package dk.yzhy.listeners;

import dk.yzhy.Main;
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
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
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
                        if ((API.getCombat(victim) < time) && !victim.hasMetadata("CancelCombat") && attacker != victim) {
                            if (Boolean.parseBoolean(ConfigLoader.getString("Bypass.Enabled"))) {
                                if (!victim.hasPermission(ConfigLoader.getString("Bypass.Permission"))) {
                                    SeeCombat.seeCombat(victim.getPlayer(), time);
                                }
                            } else {
                                SeeCombat.seeCombat(victim.getPlayer(), time);
                            }
                        }
                        if (((API.getCombat(attacker) < time) && !attacker.hasMetadata("CancelCombat"))) {
                            if (Boolean.parseBoolean(ConfigLoader.getString("Bypass.Enabled"))) {
                                if (!attacker.hasPermission(ConfigLoader.getString("Bypass.Permission"))) {
                                    SeeCombat.seeCombat(attacker.getPlayer(), time);
                                }
                            } else {
                                SeeCombat.seeCombat(attacker.getPlayer(), time);
                            }
                        }
                    }
                }
            }
        });
    }
}
