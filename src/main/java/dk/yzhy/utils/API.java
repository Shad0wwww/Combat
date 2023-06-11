package dk.yzhy.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class API {
    public static Integer getCombat(Player p) {
        try {
            if (p.hasMetadata("InCombat")) {
                return p.getMetadata("InCombat").get(0).asInt();
            } else {
                return 0;
            }
        } catch(Exception e){
            return 0;
        }
    }
    public static Boolean inCombat(Player p) {
        return p.hasMetadata("InCombat");
    }
    public static void setCombat(Player p, Integer a) {
        SeeCombat.seeCombat(p, a);
    }
}
