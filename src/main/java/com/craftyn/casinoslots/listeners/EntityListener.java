package com.craftyn.casinoslots.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import com.craftyn.casinoslots.CasinoSlots;
import com.craftyn.casinoslots.classes.OldSlotMachine;

public class EntityListener implements Listener {
    private CasinoSlots plugin;

    public EntityListener (CasinoSlots plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityExplode(EntityExplodeEvent event) {
        if(event.blockList() == null || event.blockList().isEmpty()) return;

        int x;
        Block b;

        for(x = 0; x < event.blockList().size(); ++x){
            b = event.blockList().get(x);

            // Look for match in slots
            for(OldSlotMachine slot : plugin.getSlotManager().getSlots()) {

                for(Block current : slot.getBlocks()) {

                    // Match found, cancel event
                    if(b.equals(current)) {
                        event.setCancelled(true);
                        return;
                    }
                }

                // Check controller as well
                Block current = slot.getController();
                if(b.equals(current)) {
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }
}
