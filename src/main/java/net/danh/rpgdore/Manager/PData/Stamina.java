package net.danh.rpgdore.Manager.PData;

import net.danh.rpgdore.Manager.ManagerData;
import org.bukkit.entity.Player;

import static net.danh.rpgdore.Manager.ManagerPlayerData.data;
import static net.danh.rpgdore.Manager.ManagerPlayerData.getData;

public class Stamina {

    public static int getStamina(Player p) {
        return data.getOrDefault(getData(p, ManagerData.STAMINA), 0);
    }

    public static int getMaxStamina(Player p) {
        return data.getOrDefault(getData(p, ManagerData.MAX_STAMINA), 1000);
    }

    public static void addStamina(Player p, Integer amount) {
        int after_add = getStamina(p) + amount;
        if (after_add < getMaxStamina(p)) {
            data.replace(getData(p, ManagerData.STAMINA), getStamina(p) + amount);
        } else {
            data.put(getData(p, ManagerData.STAMINA), getMaxStamina(p));
        }
    }

    public static void removeStamina(Player p, Integer amount) {
        int after_remove = getStamina(p) - amount;
        if (after_remove > 0) {
            data.replace(getData(p, ManagerData.STAMINA), after_remove);
        } else {
            data.put(getData(p, ManagerData.STAMINA), 0);
        }
    }

    public static void setStamina(Player p, Integer amount) {
        if (amount >= 0) {
            data.put(getData(p, ManagerData.STAMINA), amount);
        }
    }

    public static void addMaxStamina(Player p, Integer amount) {
        data.replace(getData(p, ManagerData.MAX_STAMINA), getMaxStamina(p) + amount);
    }

    public static void removeMaxStamina(Player p, Integer amount) {
        int after_remove = getMaxStamina(p) - amount;
        data.replace(getData(p, ManagerData.MAX_STAMINA), Math.max(after_remove, 1000));
    }

    public static void setMaxStamina(Player p, Integer amount) {
        data.put(getData(p, ManagerData.MAX_STAMINA), Math.max(amount, 1000));
    }
}

