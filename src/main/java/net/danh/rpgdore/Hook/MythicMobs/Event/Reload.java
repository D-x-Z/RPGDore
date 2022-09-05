package net.danh.rpgdore.Hook.MythicMobs.Event;

import io.lumine.mythic.bukkit.events.MythicReloadedEvent;
import net.danh.dcore.Utils.Chat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Reload implements Listener {

    @EventHandler
    public void onMythicReload(MythicReloadedEvent e) {
        e.getInstance().getLogger().info(Chat.colorize("&a✓&7 Loaded system compatible with RPGDore"));
    }
}
