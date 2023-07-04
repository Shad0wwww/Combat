package dk.yzhy.listeners;

import dk.yzhy.Combat;
import dk.yzhy.utils.API;
import dk.yzhy.utils.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        if (API.inCombat(event.getPlayer())) {
            event.getPlayer().damage(999);
            if (Boolean.parseBoolean(ConfigLoader.getString("Beskeder.BroadcastLogOut.Enabled"))) {
                String m = ConfigLoader.getString("Beskeder.BroadcastLogOut.Message").replaceAll("%player%", event.getPlayer().getName());
                Bukkit.broadcastMessage(m);
            }
            if(event.getPlayer().hasMetadata("InCombat")){
                event.getPlayer().removeMetadata("InCombat", Combat.getInstance());
            }
        }
    }
}
