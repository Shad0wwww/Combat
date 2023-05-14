package dk.yzhy.tasks;

import dk.yzhy.Combat;
import dk.yzhy.utils.ActionBar;
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
                        if (!player.hasMetadata("take")) {
                            ActionBar.sendActionbar(player, "§4§l[!] §7Du er i combat §f(" + index + "s)");
                        }
                    } else {
                        player.removeMetadata("InCombat", Combat.getInstance());
                        ActionBar.sendActionbar(player, "§4§l[!] §7Du er ikke længere i combat!");
                        cancel();
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(Combat.getInstance(), 0L, 20L);
    }

}
