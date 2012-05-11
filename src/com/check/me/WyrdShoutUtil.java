package com.check.me;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
 * Utility class minded on configuring large amount of codes in other classes
 * with out having to write it all in the current class. Easy to access by 
 * creating an object for this class and call that. Then use the period for member access.
 * Remember to enter the parameters for every method.
 * 
 * @author Highace2
 */
public class WyrdShoutUtil {
	 
	private WyrdShout plugin;
        
        public WyrdShoutUtil(){
            this.plugin = plugin;
        }
        
        /**
         * colorize messages. Used for colorizing messages to players
         * in minecraft.
         * 
         * @param message The message which needs colors.
         * @return The message just with colors.
         */
	public String colorizeMessages(String message) {
	    message = message.replaceAll("(&([a-f0-9]))", "\u00A7$2");
	    return message;
	}
	  
        /**
         * Converts System.currentTimeMillis() to a message which can be displayed
         * and easily understood.
         * @param time The time which needs to be converted
         * @return A message from time.
         */
	public String formatTime(long time) {
		String r = "";
		if (time >= 60000)
                    r += Math.round(time/60000) + " minutes and ";
                r += Math.round(time%60000/1000)+" seconds";
		return r;
	}
	
        /**
         * Configure colors from config.yml
         * @param config The text which needs to be converted.
         * @return The color.
         */
	public String configColor(String config) {
		config = config.replaceAll("BLACK", "&0");
		config = config.replaceAll("DARK_BLUE", "&1");
		config = config.replaceAll("DARK_GREEN", "&2");
		config = config.replaceAll("DARK_AQUA", "&3");
		config = config.replaceAll("DARK_RED", "&4");
		config = config.replaceAll("PURPLE", "&5");
		config = config.replaceAll("GOLD", "&6");
		config = config.replaceAll("GREY", "&7");
		config = config.replaceAll("DARK_GREY", "&8");
		config = config.replaceAll("INDIGO", "&9");
		config = config.replaceAll("BRIGHT_GREEN", "&a");
		config = config.replaceAll("AQUA", "&b");
		config = config.replaceAll("RED", "&c");
		config = config.replaceAll("PINK", "&d");
		config = config.replaceAll("YELLOW", "&e");
		config = config.replaceAll("WHITE", "&f");
                config = config.replaceAll("(&([a-f0-9]))", "\u00A7$2");
		return config;
	}
        
        /**
         * Formats a time retrieved from players
         * @param time The args which needs to be configured.
         * @return A long for time in milliseconds.
         * @see com.check.me.WyrdShoutHashMap#setMuteTimer(java.lang.String, java.lang.String[]) 
         */
        public long formatTimeFromPlayers(String time) {
            long hour;
            long minute;
            long second;
            if(time.endsWith("h")){
                time.replaceAll("h", "");
                try {
                    hour = Long.parseLong(time);
                } catch (NumberFormatException nfe){
                    hour = Long.MIN_VALUE;
                }
                hour = hour*3600000;
                return hour;
            }
            else if(time.endsWith("m")){
                time.replaceAll("m", "");
                try {
                minute = Long.parseLong(time);
                } catch (NumberFormatException nfe){
                    minute = Long.MIN_VALUE;
                }
                minute = minute*60000;
                return minute;
            }
            else if(time.endsWith("s")){
                time.replaceAll("s", "");
                try {
                second = Long.parseLong(time);
                } catch (NumberFormatException nfe){
                    second = Long.MIN_VALUE;
                }
                second = second*1000;
                return second;
            } else {
                return 0;
            }
        }
        
       /**
        * Set the broadcast message with prefix
        * @param player The player which shouts
        * @param args The message
        * @return Player name and message
        */
        public String messagePrefix(Player player, String[] args){
            String outputMessage = "";
            for(String s: args){
                outputMessage += " " + s;
            }
            PermissionUser user = PermissionsEx.getUser(player);
            String message = " " +user.getPrefix() + player.getDisplayName() + ":" + ChatColor.GRAY + outputMessage;
            return message;
        }
        
        /**
         * Set the broadcast message without prefix
         * @param player The player which shouts
         * @param args The message
         * @return Player name and message 
         */
        public String messageNonPrefix(Player player, String[] args){
            String outputMessage = "";
            for(String s: args){
                outputMessage += " " + s;
            }
            String message =  " " + player.getDisplayName() + ":" + ChatColor.GRAY + outputMessage; 
            return message;
        }
}