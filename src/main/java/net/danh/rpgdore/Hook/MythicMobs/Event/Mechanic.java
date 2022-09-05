package net.danh.rpgdore.Hook.MythicMobs.Event;

import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Mechanic implements Listener {

    @EventHandler
    public void onMythicMechanicLoad(MythicMechanicLoadEvent e) {
        if (e.getMechanicName().equalsIgnoreCase("rpgdore_mechanic")) {
            e.register(new net.danh.rpgdore.Hook.MythicMobs.Mechanic(e.getConfig()));
        }
    }
}
