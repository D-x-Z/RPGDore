package net.danh.rpgdore.Manager;

import net.danh.dcore.Utils.Chat;
import net.danh.rpgdore.RPGDore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.*;

public class Hologram {

    public static Map<Entity, Integer> indicators = new HashMap<>();
    public static Set<Entity> stands = indicators.keySet();
    public static List<Entity> removal = new ArrayList<>();


    public static double getRandomOffset() {
        double random = Math.random();
        if (Math.random() > 0.5) random *= -1;
        return random;
    }

    public static void createHolo(Player p, Location location, String text) {
        Location loc = location.clone().add(getRandomOffset(), 1, getRandomOffset());
        Bukkit.getScheduler().scheduleSyncDelayedTask(RPGDore.getRPGDore(), () -> p.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
            armorStand.setMarker(true);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setSmall(true);
            armorStand.setCustomNameVisible(true);
            armorStand.setCustomName(Chat.colorize(text));
            indicators.put(armorStand, 30);
        }));
    }

    public static void deleteHolo() {
        for (Entity stand : stands) {
            int ticksLeft = indicators.get(stand);
            if (ticksLeft == 0) {
                stand.remove();
                removal.add(stand);
                continue;
            }
            ticksLeft--;
            indicators.put(stand, ticksLeft);
        }
        removal.forEach(stands::remove);
    }
}
