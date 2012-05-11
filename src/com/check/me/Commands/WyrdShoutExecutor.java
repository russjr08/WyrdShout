package com.check.me.Commands;

import com.check.me.WyrdShout;
import com.check.me.WyrdShoutUtil;
import java.util.HashMap;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
 * Contains shout command method
 * @author Highace2
 */
public class WyrdShoutExecutor implements CommandExecutor {
	  
    /**
     * Saves the time for how long time there goes before player can use the shout command again.
     * @author Highace2
     */
    public static HashMap<Player, Long> shoutTimer = new HashMap<>();
     
    /**
     * Saves the time for how long a player have been muted.
     * @author Highace2
     */
    public static HashMap<Player, Long> muteTimer = new HashMap<>();
    
    private WyrdShout plugin;
    
    /**
     * Connects this class with the rest of the plugin.
     * @param plugin
     */
    public WyrdShoutExecutor(WyrdShout plugin)
	  {
	    this.plugin = plugin;
          }
            
    Logger log = Logger.getLogger("Minecraft");
    
    /**
     * Everything with shouting your message to the server.
     * @param sender CommandSender
     * @param cmd Command
     * @param label Alias
     * @param args Arguments
     * @return 
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        WyrdShoutUtil util = new WyrdShoutUtil();
        if(cmd.getName().equalsIgnoreCase("wshout") || cmd.getName().equalsIgnoreCase("ws") || cmd.getName().equalsIgnoreCase("shout") || cmd.getName().equalsIgnoreCase("wyrdshout")){
            PermissionManager pex = PermissionsEx.getPermissionManager();
            if(sender instanceof Player){
                Player player = (Player) sender;
                if(args.length != 0){
                        if(pex.has(player, "wyrdshout.shout") || pex.has(player, "wyrdshout.*")){
                            Long muteTime = muteTimer.get(player);
                            if(muteTime == null || muteTime <= System.currentTimeMillis()){
                                Long lastCommand = shoutTimer.get(player);
                                if(lastCommand == null || lastCommand <= System.currentTimeMillis()){
                                    PermissionUser user = PermissionsEx.getUser(player);
                                    if(user.getPrefix() == null){
                                            if(pex.has(player, "wshout.override") || pex.has(player, "wshout.*")){
                                                plugin.reloadConfig();
                                                Bukkit.broadcastMessage(util.colorizeMessages(plugin.getConfig().getString("shout.shout-prefix") + util.messageNonPrefix(player, args)));
                                                log.info("overrides");
                                            } else {
                                                plugin.reloadConfig();
                                                Bukkit.broadcastMessage(util.colorizeMessages(plugin.getConfig().getString("shout.shout-prefix") + util.messageNonPrefix(player, args)));
                                                shoutTimer.put(player, System.currentTimeMillis() + plugin.getConfig().getLong("shout.delay"));
                                                log.info("do not override");
                                            }
                                            return true;
                                        } else {
                                            if(pex.has(player, "wshout.override") || pex.has(player, "wshout.*")){
                                                plugin.reloadConfig();
                                                Bukkit.broadcastMessage(util.colorizeMessages(plugin.getConfig().getString("shout.shout-prefix") + util.messagePrefix(player, args)));
                                                log.info("overrides with prefix");
                                            } else {
                                                plugin.reloadConfig();
                                                Bukkit.broadcastMessage(util.colorizeMessages(plugin.getConfig().getString("shout.shout-prefix") + util.messagePrefix(player, args)));
                                                shoutTimer.put(player, System.currentTimeMillis() + plugin.getConfig().getLong("shout.delay"));
                                                log.info("do not override with prefix");
                                            }
                                            return true;
                                        }
                                } else {
                                   player.sendMessage(ChatColor.RED + "You must wait " + util.formatTime(shoutTimer.get(player) - System.currentTimeMillis()) + " before using this command again!");
                                }
                            } else {
                                player.sendMessage(ChatColor.RED + "You've been muted for " + util.formatTime(muteTimer.get(player) - System.currentTimeMillis()) + "!");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You don't have access to that command!");
                        }
                    } else {
                    	player.sendMessage(ChatColor.RED + "Too few arguments!");
                    }
                } else {
                    log.info("This command can only be executed by a player");
                }
        }
        return false;
    }
}