package dk.yzhy.listeners;

import dk.yzhy.utils.API;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {
    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        if (event.getEntity() != null) {
            Player p = (Player) event.getEntity();
            if (API.inCombat(p)) {
                API.setCombat(p,0);
            }
        }
    }
}
