package dk.yzhy.tasks;

import dk.yzhy.Main;
import dk.yzhy.utils.ActionBar;
import dk.yzhy.utils.ConfigLoader;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class CombatSystem {
    public static void combatTag(Player player) {
        Main.executorService.submit(() -> {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (player.hasMetadata("InCombat")) {
                        int index = player.getMetadata("InCombat").get(0).asInt();
                        if (index >= 1) {
                            player.setMetadata("InCombat", new FixedMetadataValue(Main.getInstance(), index - 1));
                            if (Boolean.parseBoolean(ConfigLoader.getString("ActionBar.Enabled"))) {
                                String ab = ConfigLoader.getString("ActionBar.Tekst.ICombat");
                                ab = ChatColor.translateAlternateColorCodes('&', ab.replaceAll("%tid%", String.valueOf(index)));
                                ActionBar.sendActionbar(player, ab);
                            }
                        } else {
                            player.removeMetadata("InCombat", Main.getInstance());
                            if (Boolean.parseBoolean(ConfigLoader.getString("ActionBar.Enabled"))) {
                                String ab = ConfigLoader.getString("ActionBar.Tekst.UdeCombat");
                                ab = ChatColor.translateAlternateColorCodes('&', ab.replaceAll("%tid%", String.valueOf(index)));
                                ActionBar.sendActionbar(player, ab);
                            }
                            if (Boolean.parseBoolean(ConfigLoader.getString("Beskeder.Enabled"))) {
                                String m = ChatColor.translateAlternateColorCodes('&', ConfigLoader.getString("Beskeder.CombatDisable"));
                                player.sendMessage(m);
                            }
                            cancel();
                        }
                    } else {
                        cancel();
                    }
                }
            }.runTaskTimer(Main.getInstance(), 0L, 20L);
        });
    }

}
