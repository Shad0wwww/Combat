package dk.yzhy.tasks;

import dk.yzhy.Combat;
import dk.yzhy.utils.Actionbar;
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
                        if (Boolean.parseBoolean(ConfigLoader.getString("ActionBar.Enabled"))) {
                            String ab = ConfigLoader.getString("ActionBar.Tekst.ICombat").replaceAll("%tid%", String.valueOf(index));
                            Actionbar.sendActionbar(player, ab);
                        }
                    } else {
                        player.removeMetadata("InCombat", Combat.getInstance());
                        if (Boolean.parseBoolean(ConfigLoader.getString("ActionBar.Enabled"))) {
                            String ab = ConfigLoader.getString("ActionBar.Tekst.UdeCombat").replaceAll("%tid%", String.valueOf(index));
                            Actionbar.sendActionbar(player, ab);
                        }
                        if (Boolean.parseBoolean(ConfigLoader.getString("Beskeder.Enabled"))) {
                            String m = ConfigLoader.getString("Beskeder.CombatDisable");
                            player.sendMessage(m);
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
