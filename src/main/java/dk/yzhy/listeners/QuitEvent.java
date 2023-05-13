package dk.yzhy.listeners;

import dk.yzhy.utils.API;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        if (API.inCombat(event.getPlayer())) {
            event.getPlayer().damage(999);
        }
    }
}
