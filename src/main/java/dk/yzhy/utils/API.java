package dk.yzhy.utils;

import org.bukkit.entity.Player;

public class API {
    public static Integer getCombat(Player p) {
        if (p.hasMetadata("InCombat")) {
            return p.getMetadata("InCombat").get(0).asInt();
        } else {
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
