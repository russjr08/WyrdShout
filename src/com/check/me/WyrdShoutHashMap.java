package com.check.me;

import java.util.HashMap;

/**
 * Class to store shout and mute timer.
 * @author Highace2
 */
public class WyrdShoutHashMap{
    
    private WyrdShout plugin;

    public WyrdShoutHashMap(WyrdShout plugin)
    {
        this.plugin = plugin;
    }
    
    public static HashMap<String, Long> shoutTimer = new HashMap<>();
    public static HashMap<String, Long> muteTimer = new HashMap<>();

    public WyrdShoutHashMap() {
    }

    /**
     * Set the shout timer.
     * @param player The name of the commandsender.
     */
    public void setShoutTimer(String player){
	shoutTimer.put(player, System.currentTimeMillis());
    }

    /**
     * Set the mute timer.
     * @param player The name of the player which needs to be muted.
     * @param args The message retrieved from the commandsender. No need to retrieve the long.
     */
    public void setMuteTimer(String player, String[] args){
        WyrdShoutUtil util = new WyrdShoutUtil();    
        long finalResult = 0;
	for(String s:args){
            finalResult += util.formatTimeFromPlayers(s);
	}
	muteTimer.put(player, System.currentTimeMillis() + finalResult);
    }

    /**
     * Get the shout timer.
     * @param player The name of the commandsender.
     * @return A long in for System.currentTimeMillis() before next shout.
     */
    public long getShoutTimer(String player){
        if(shoutTimer.containsKey(player)){
            return shoutTimer.get(player) + plugin.getConfig().getLong("shout.delay");
        } else {
            return 0;
        }
    }
    /**
     * Get the mute timer
     * @param player the name of the player which needs to be retrieved timer from
     * @return A long for System.currentTimeMillis() muted till.
     */
    public long getMuteTimer(String player){
        if(muteTimer.containsKey(player)){
            return muteTimer.get(player);
        } else {
         return 0;
        }
    }
}
