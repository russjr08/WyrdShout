package com.check.me;

import com.check.me.Commands.WyrdAdminExecutor;
import com.check.me.Commands.WyrdMuteExecutor;
import com.check.me.Commands.WyrdShoutExecutor;
import java.io.File;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is the main class of this plugin.
 * It connects with every other class and loads configs.
 * 
 * @author Highace2
 */
public class WyrdShout extends JavaPlugin {
    
    Logger log = Logger.getLogger("Minecraft");
    
    /**
     * Get message and save config when the plugin is disabled.
     * 
     * @Author Highace2
     */
    @Override
    public void onDisable(){
        log = this.getLogger();
        saveConfig();
        log.info("Config saved");
    }

    private WyrdShoutExecutor WyrdShoutExecutor;
    private WyrdAdminExecutor WyrdAdminExecutor;
    private WyrdMuteExecutor WyrdMuteExecutor;
    
    
    /**
     * This method conncts everything together on enable of this plugin
     */
    @Override
    public void onEnable(){
        WyrdShoutExecutor = new WyrdShoutExecutor(this);
        getCommand("wshout").setExecutor(WyrdShoutExecutor);
        getCommand("ws").setExecutor(WyrdShoutExecutor);
        getCommand("shout").setExecutor(WyrdShoutExecutor);
        getCommand("wyrdshout").setExecutor(WyrdShoutExecutor);
        
        WyrdMuteExecutor = new WyrdMuteExecutor(this);
        getCommand("wmute").setExecutor(WyrdMuteExecutor);
        getCommand("wyrdmute").setExecutor(WyrdMuteExecutor);
        getCommand("wm").setExecutor(WyrdMuteExecutor);
        
        WyrdAdminExecutor = new WyrdAdminExecutor(this);
        getCommand("wyrdadmin").setExecutor(WyrdAdminExecutor);
        getCommand("wadmin").setExecutor(WyrdAdminExecutor);
        getCommand("wa").setExecutor(WyrdAdminExecutor);
        
        loadConfig();
        log = this.getLogger();
        log.info("Config loaded");
    }
    
    /**
     * Creates all configs.
     */
        public void loadConfig(){
            new File("Plugins/WyrdShout").mkdir();
            getConfig().options().header("WyrdShout version 0.3");
            getConfig().addDefault("shout.shout-prefix" , "&c[Shout]");
            getConfig().addDefault("shout.delay", 150);
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
}