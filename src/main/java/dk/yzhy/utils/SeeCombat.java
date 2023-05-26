package dk.yzhy.utils;

import dk.yzhy.Main;
import dk.yzhy.tasks.CombatSystem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class SeeCombat {
    public static void seeCombat(Player player, Integer a) {
        player.setMetadata("InCombat", new FixedMetadataValue(Main.getInstance(), a));
        if (!player.hasMetadata("InCombat")) {
            CombatSystem.combatTag(player);
            if (Boolean.parseBoolean(ConfigLoader.getString("Beskeder.Enabled"))) {
                String m = ConfigLoader.getString("Beskeder.CombatEnable");
                m = ChatColor.translateAlternateColorCodes('&', m.replaceAll("%tid%", ConfigLoader.getString("Combat.Tid")));
                player.sendMessage(m);
            }
        }
    }
}
