package com.craftyn.casinoslots.command;

import org.bukkit.command.CommandSender;

import com.craftyn.casinoslots.CasinoSlots;
import com.craftyn.casinoslots.enums.Settings;
import com.craftyn.casinoslots.util.PermissionUtil;

public class CasinoReload extends AnCommand {

    /**
     * Is initiated by a /casino reload, which is intended to reload the config.
     * 
     * @param plugin The main plugin class
     * @param args The other arguments passed along with 'reload'
     * @param sender The one who did the command
     */
    public CasinoReload(CasinoSlots plugin, String[] args, CommandSender sender) {
        super(plugin, args, sender);
    }

    public Boolean process() {
        // Permissions
        if(player != null) {
            if(!PermissionUtil.isAdmin(player)) {
                noPermission();
                return true;
            }
        }

        plugin.reloadConfig();
        plugin.getConfigData().reloadConfigs();
        plugin.getTypeManager().reloadTypes();
        plugin.getSlotManager().reloadSlots();
        plugin.reloadUpdateCheck();

        if(Settings.inDebug()) senderSendMessage("Debugging enabled.");
        senderSendMessage("Configuration reloaded");
        return true;
    }

}