package com.check.me.Commands;

import com.check.me.WyrdShout;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
 * Contains methods for admin only things.
 * @author Highace2
 */
public class WyrdAdminExecutor implements CommandExecutor {
        
    private WyrdShout plugin;
    
    /**
     * Connects this class as well.
     * @param plugin
     */
    public WyrdAdminExecutor(WyrdShout plugin) {
        this.plugin = plugin;
    }

    Logger log = Logger.getLogger("Minecraft");
    
    /**
     * Configures every single thing about admin commands
     * which is directly linked to config.
     * @param sender Commandsender
     * @param cmd Command
     * @param label Alias
     * @param args Arguments
     * @return 
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(cmd.getName().equalsIgnoreCase("wyrdadmin") || cmd.getName().equalsIgnoreCase("wadmin") || cmd.getName().equalsIgnoreCase("wa")){
            PermissionManager pex = PermissionsEx.getPermissionManager();
            if(args.length != 0){
            if(sender instanceof Player){
                Player player = (Player) sender;
                if(!(args.length == 0)){
                        if(args[0].equalsIgnoreCase("version")){
                            if(pex.has(player, "wshout.admin.version") || pex.has(player, "wshout.admin.*") || pex.has(player, "wshout.*") ){
                                player.sendMessage(ChatColor.DARK_AQUA + "[WyrdShout] " + ChatColor.BLUE + "WyrdShout is running v0.3");
                            } else {
                                player.sendMessage(ChatColor.RED + "You don't have access to that command!");
                            }
                        } else if(args[0].equalsIgnoreCase("delay")){
                            if(pex.has(player, "wshout.admin.delay") || pex.has(player, "wshout.admin.*") || pex.has(player, "wshout.*")) {
                                int intArgs = Integer.parseInt(args[1]);
                                plugin.getConfig().set("delay", intArgs);
                                player.sendMessage(ChatColor.DARK_AQUA + "[WyrdShout]" + ChatColor.BLUE + "Delay between shouts has been set!");
                            } else {
                                player.sendMessage(ChatColor.RED + "You don't have access to that command!");
                            }
                        } else if(args[0].equalsIgnoreCase("shout-prefix")){
                            if(pex.has(player, "wshout.admin.shout-prefix") || pex.has(player, "wshout.admin.*") || pex.has(player, "wshout.*")){
                                plugin.getConfig().set("shout.shout-prefix", args[1]);
                                player.sendMessage(ChatColor.DARK_AQUA + "[WyrdShout]" + ChatColor.BLUE + "Shout prefix has been set!");
                            } else {
                                player.sendMessage(ChatColor.RED + "You don't have access to that command!");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "Wrong arguments!");
                        }
                }
            } else {
                log.info("This command can only be executed by a player!");
            }
            }
        }
        return false;
    }
}