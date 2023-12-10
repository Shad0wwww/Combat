package dk.yzhy.commands;

import dk.yzhy.utils.SeeCombat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetCombat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 2){
            try{
                Player p = Bukkit.getPlayer(args[0]);
                int a = Integer.parseInt(args[1]);
                SeeCombat.seeCombat(p,a);
            }catch(Exception e){
                if(sender instanceof Player){
                    sender.sendMessage("§cForkert brug: §7/setcombat <spiller> <sekunder>");
                }
            }
        }else{
            if(sender instanceof Player){
                sender.sendMessage("§cForkert brug: §7/setcombat <spiller> <sekunder>");
            }
        }
        return true;
    }
}
