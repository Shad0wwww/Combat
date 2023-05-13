package dk.yzhy.utils;

import dk.yzhy.listeners.DamageByEntityEvent;
import org.bukkit.entity.Player;

public class API {
    public static Integer getCombat(Player p) {
        try {
            return p.getMetadata("InCombat").get(0).asInt();
        } catch(Exception e){
            return 0;
        }
    }
    public static Boolean inCombat(Player p) {
        return p.hasMetadata("InCombat");
    }
    public static void setCombat(Player p, Integer a) {
        DamageByEntityEvent.seeCombat(p, a);
    }
}
