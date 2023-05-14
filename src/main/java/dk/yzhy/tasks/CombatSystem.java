package dk.yzhy.tasks;

import dk.yzhy.Combat;
import dk.yzhy.utils.ActionBar;
import dk.yzhy.utils.ConfigLoader;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class CombatSystem {
    public static void combatTag(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (player.hasMetadata("InCombat")) {
                    int index = player.getMetadata("InCombat").get(0).asInt();
                    if (index >= 1) {
                        player.setMetadata("InCombat", new FixedMetadataValue(Combat.getInstance(), index - 1));
                        if (Boolean.getBoolean(ConfigLoader.getString("ActionBar.Enabled"))) {
                            String ab = ConfigLoader.getString("ActionBar.Tekst.ICombat");
                            ab = ab.replaceAll("%tid%", ConfigLoader.getString("Combat.Tid"));
                            ActionBar.sendActionbar(player, ab);
                        }
                    } else {
                        player.removeMetadata("InCombat", Combat.getInstance());
                        if (Boolean.getBoolean(ConfigLoader.getString("ActionBar.Enabled"))) {
                            String ab = ConfigLoader.getString("ActionBar.Tekst.UdeCombat");
                            ab = ab.replaceAll("%tid%", ConfigLoader.getString("Combat.Tid"));
                            ActionBar.sendActionbar(player, ab);
                        }
                        cancel();
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(Combat.getInstance(), 0L, 20L);
    }

}
