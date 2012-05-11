package com.check.me.Commands;

import com.check.me.WyrdShout;
import com.check.me.WyrdShoutUtil;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class manages mute commands
 * @author Highace2
 */
public class WyrdMuteExecutor implements CommandExecutor {
    	  
    private WyrdShout plugin;

    public WyrdMuteExecutor(WyrdShout plugin)
    {
        this.plugin = plugin;
    }
    
    Logger log = Logger.getLogger("Minecraft");
    
    /**
     * Method manages mute commands. 
     * @param sender Sender of the command
     * @param cmd Command
     * @param label Alias
     * @param args Arguments
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        WyrdShoutUtil util = new WyrdShoutUtil();
            if(cmd.getName().equalsIgnoreCase("wmute") || cmd.getName().equalsIgnoreCase("wm") || cmd.getName().equalsIgnoreCase("wyrdmute")){
            if(sender instanceof Player){
            Player player = (Player) sender;
                if(plugin.permCheck(player, "wshout.admin.mute") || plugin.permCheck(player, "wshout.admin.*") || plugin.permCheck(player, "wshout.*")){
                        if(args.length >= 1){
                            player.sendMessage(ChatColor.RED + "Not enough arguments!");
                        } else if(args.length <= 5){
                            player.sendMessage(ChatColor.RED + "To many arguments!");
                        } else {
                            if(args[0].equalsIgnoreCase("getmute")){            
                                Player mutedPlayer = Bukkit.getPlayer(args[1]);
                                Long l =  - System.currentTimeMillis();
                                player.sendMessage(ChatColor.DARK_AQUA + "[WyrdShout]" + ChatColor.BLUE + mutedPlayer.getDisplayName() + " has been muted for" + util.formatTime(l));
                            }
                            if(Bukkit.getPlayer(args[0]).isOnline()){
                                long finalResult = 0;
                                boolean first = true;
                                for(String s:args){
                                    if(first == true){
                                        first = false;
                                    } else {
                                        finalResult += util.formatTimeFromPlayers(s);
                                    }
                                }
                                Player mutePlayer = Bukkit.getPlayer(args[0]);
                                WyrdShoutExecutor.muteTimer.put(mutePlayer, (System.currentTimeMillis() + finalResult));
                            } else {
                                player.sendMessage(ChatColor.DARK_AQUA + "[WyrdShout]" + ChatColor.BLUE + args[0] + " is not online!");
                            }
                        }
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have access to that command!");
                }
                } else {
                log.info("This command can only be executed by a player!");
                }
            }
            return false;
    }
}
