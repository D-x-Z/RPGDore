package net.danh.rpgdore.Manager.PData;

import net.danh.rpgdore.Manager.ManagerData;
import org.bukkit.entity.Player;

import static net.danh.rpgdore.Manager.ManagerPlayerData.data;
import static net.danh.rpgdore.Manager.ManagerPlayerData.getData;

public class Level {

    public static int getLevel(Player p) {
        return data.getOrDefault(getData(p, ManagerData.LEVEL), 1);
    }

    public static void addLevel(Player p, Integer amount) {
        data.replace(getData(p, ManagerData.LEVEL), getLevel(p) + amount);
    }

    public static void removeLevel(Player p, Integer amount) {
        int after_remove = getLevel(p) - amount;
        if (after_remove > 0) {
            data.replace(getData(p, ManagerData.LEVEL), after_remove);
        } else {
            data.put(getData(p, ManagerData.LEVEL), 0);
        }
    }

    public static void setLevel(Player p, Integer amount) {
        data.put(getData(p, ManagerData.LEVEL), amount);
    }
}

