package dk.yzhy.tasks;

import dk.yzhy.Main;
import dk.yzhy.utils.Actionbar;
import dk.yzhy.utils.ConfigLoader;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import java.util.Map;
import java.util.concurrent.*;

public class CombatSystem {
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    private static final Map<Player, ScheduledFuture<?>> combatTasks = new ConcurrentHashMap<>();
    public static void combatTag(Player player) {
        if (combatTasks.containsKey(player)) {
            player.removeMetadata("InCombat", Main.getInstance());
            cancelCombatTask(player);
        }
        int initialDelay = 0;
        int period = 1000;
        Runnable combatTask = () -> {
            if (player.hasMetadata("InCombat")) {
                int index = player.getMetadata("InCombat").get(0).asInt();
                if (index >= 1) {
                    player.setMetadata("InCombat", new FixedMetadataValue(Main.getInstance(), index - 1));
                    if (Boolean.parseBoolean(ConfigLoader.getString("ActionBar.Enabled"))) {
                        String ab = ConfigLoader.getString("ActionBar.Tekst.ICombat").replaceAll("%tid%", String.valueOf(index));
                        Actionbar.sendActionbar(player, ab);
                    }
                } else {
                    player.removeMetadata("InCombat", Main.getInstance());
                    if (Boolean.parseBoolean(ConfigLoader.getString("ActionBar.Enabled"))) {
                        String ab = ConfigLoader.getString("ActionBar.Tekst.UdeCombat").replaceAll("%tid%", String.valueOf(index));
                        Actionbar.sendActionbar(player, ab);
                    }
                    if (Boolean.parseBoolean(ConfigLoader.getString("Beskeder.Enabled"))) {
                        String m = ConfigLoader.getString("Beskeder.CombatDisable");
                        player.sendMessage(m);
                    }
                    cancelCombatTask(player);
                }
            } else {
                cancelCombatTask(player);
            }
        };
        ScheduledFuture<?> taskFuture = executorService.scheduleAtFixedRate(combatTask, initialDelay, period, TimeUnit.MILLISECONDS);
        combatTasks.put(player, taskFuture);
    }
    private static void cancelCombatTask(Player player) {
        ScheduledFuture<?> taskFuture = combatTasks.get(player);
        if (taskFuture != null) {
            taskFuture.cancel(true);
            combatTasks.remove(player);
        }
    }
}
