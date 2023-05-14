package dk.yzhy.listeners;

import dk.yzhy.utils.API;
import dk.yzhy.utils.SeeCombat;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


public class DamageByEntityEvent implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!event.isCancelled()) {
            if (event.getEntity() instanceof Player) {
                if (!event.getEntity().hasMetadata("event")) {
                    Player victim = (Player) event.getEntity();
                    Player attacker;
                    if (!(event.getDamager() instanceof Player)) {
                        attacker = (Player) (((Projectile)event.getDamager()).getShooter());
                    } else {
                        attacker = (Player) event.getDamager();
                    }
                    String group = PlaceholderAPI.setPlaceholders(attacker, "%vault_rank%");
                    String groupvic = PlaceholderAPI.setPlaceholders(victim, "%vault_rank%");
                    if (!(groupvic.contains("vagt") || groupvic.contains("officer") || groupvic.contains("inspektør") || groupvic.contains("direktør"))) {
                        if (attacker != victim) {
                            if (API.getCombat(victim) < 15) {
                                SeeCombat.seeCombat(victim.getPlayer(), 15);
                            }
                        }
                    }
                    if (!(group.contains("vagt") || group.contains("officer") || group.contains("inspektør") || group.contains("direktør"))) {
                        if (API.getCombat(attacker) < 15) {
                            SeeCombat.seeCombat(attacker.getPlayer(), 15);
                        }
                    }
                }
            }
        }
    }
}
