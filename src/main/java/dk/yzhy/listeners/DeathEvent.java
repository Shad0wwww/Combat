package dk.yzhy.listeners;

import dk.yzhy.utils.API;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DeathEvent implements Listener {
    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        if (API.inCombat(p)) {
            API.setCombat(p,0);
        }
    }
}
