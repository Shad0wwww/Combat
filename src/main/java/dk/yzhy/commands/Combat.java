package dk.yzhy.commands;

import dk.yzhy.utils.API;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Combat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (API.inCombat(p)) {
            p.sendMessage("§4§l[!]§7 Du er i combat i §f" + API.getCombat(p) + "s §7mere!");
        } else {
            p.sendMessage("§4§l[!]§7 Du er ikke i combat!");
        }
        return true;
    }
}
