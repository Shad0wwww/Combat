package dk.yzhy.utils;

import dk.yzhy.Main;
import dk.yzhy.tasks.CombatSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class SeeCombat {
    public static void seeCombat(Player player, Integer a) {
        if (player.hasMetadata("InCombat")) {
            player.setMetadata("InCombat", new FixedMetadataValue(Main.getInstance(), a));
        }else {
            player.setMetadata("InCombat", new FixedMetadataValue(Main.getInstance(), a));
            CombatSystem.combatTag(player);
            if (Boolean.parseBoolean(ConfigLoader.getString("Beskeder.Enabled"))) {
                String m = ConfigLoader.getString("Beskeder.CombatEnable").replaceAll("%tid%", ConfigLoader.getString("Combat.Tid"));
                player.sendMessage(m);
            }
        }
    }
}
