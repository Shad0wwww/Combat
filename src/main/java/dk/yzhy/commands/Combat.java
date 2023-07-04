package dk.yzhy.commands;

import dk.yzhy.utils.API;
import dk.yzhy.utils.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Combat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(args.length >= 1 && args[0].equalsIgnoreCase("reload") && p.hasPermission("combat.admin")){
            try {
                long tidBefore = System.currentTimeMillis();
                dk.yzhy.Combat.mainConfig.reloadConfig();
                dk.yzhy.Combat.mainConfigYML = dk.yzhy.Combat.mainConfig.getConfig();
                ConfigLoader.loadALL();
                p.sendMessage("§aDu genindlæste configurationen & alle beskederne. §7(" + (System.currentTimeMillis() - tidBefore) + "ms)");
                Bukkit.getOnlinePlayers().forEach(pl -> pl.removeMetadata("InCombat", dk.yzhy.Combat.getInstance()));
            } catch (Exception e) {
                p.sendMessage("§cNoget gik galt, tjek loggen! Kør evt. din config igennem en YML parser online!");
                e.printStackTrace();
            }
        } else {
            if (API.inCombat(p)) {
                String m = ConfigLoader.getString("Beskeder.CombatCommand.CombatEnabled");
                m = ChatColor.translateAlternateColorCodes('&', m.replaceAll("%tid%", String.valueOf(API.getCombat(p))));
                p.sendMessage(m);
            } else {
                String m = ConfigLoader.getString("Beskeder.CombatCommand.CombatDisabled");
                m = ChatColor.translateAlternateColorCodes('&', m);
                p.sendMessage(m);
            }
        }
        return true;
    }
}
