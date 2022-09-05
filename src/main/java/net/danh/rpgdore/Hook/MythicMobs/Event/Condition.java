package net.danh.rpgdore.Hook.MythicMobs.Event;

import io.lumine.mythic.bukkit.events.MythicConditionLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Condition implements Listener {

    @EventHandler
    public void onMythicConditionLoad(MythicConditionLoadEvent e) {
        if (e.getConditionName().equalsIgnoreCase("rpgdore_condition")) {
            e.register(new net.danh.rpgdore.Hook.MythicMobs.Condition(e.getConfig()));
        }
    }
}
