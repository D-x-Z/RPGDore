package net.danh.rpgdore.Manager.PData;

import net.danh.rpgdore.Manager.ManagerData;
import org.bukkit.entity.Player;

import static net.danh.rpgdore.Manager.ManagerPlayerData.data;
import static net.danh.rpgdore.Manager.ManagerPlayerData.getData;

public class Priority {

    public static int getPriority(Player p) {
        return data.getOrDefault(getData(p, ManagerData.PRIORITY), 0);
    }

    public static void setPriority(Player p, Integer amount) {
        data.put(getData(p, ManagerData.PRIORITY), amount);
    }

    public static void addPriority(Player p, Integer amount) {
        data.replace(getData(p, ManagerData.PRIORITY), getPriority(p) + amount);
    }

    public static void removePriority(Player p, Integer amount) {
        int after_remove = getPriority(p) - amount;
        if (after_remove > 0) {
            data.replace(getData(p, ManagerData.PRIORITY), after_remove);
        } else {
            data.put(getData(p, ManagerData.PRIORITY), 0);
        }
    }
}
