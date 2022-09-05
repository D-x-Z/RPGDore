package net.danh.rpgdore.Manager.PData;

import net.danh.rpgdore.Manager.ManagerData;
import org.bukkit.entity.Player;

import static net.danh.rpgdore.Manager.ManagerPlayerData.data;
import static net.danh.rpgdore.Manager.ManagerPlayerData.getData;

public class Mana {

    public static int getMana(Player p) {
        return data.getOrDefault(getData(p, ManagerData.MANA), 0);
    }

    public static int getMaxMana(Player p) {
        return data.getOrDefault(getData(p, ManagerData.MAX_MANA), 1000);
    }

    public static void addMana(Player p, Integer amount) {
        int after_add = getMana(p) + amount;
        if (after_add < getMaxMana(p)) {
            data.replace(getData(p, ManagerData.MANA), getMana(p) + amount);
        } else {
            data.put(getData(p, ManagerData.MANA), getMaxMana(p));
        }
    }

    public static void removeMana(Player p, Integer amount) {
        int after_remove = getMana(p) - amount;
        if (after_remove > 0) {
            data.replace(getData(p, ManagerData.MANA), after_remove);
        } else {
            data.put(getData(p, ManagerData.MANA), 0);
        }
    }

    public static void setMana(Player p, Integer amount) {
        if (amount >= 0) {
            data.put(getData(p, ManagerData.MANA), amount);
        }
    }

    public static void addMaxMana(Player p, Integer amount) {
        data.replace(getData(p, ManagerData.MAX_MANA), getMaxMana(p) + amount);
    }

    public static void removeMaxMana(Player p, Integer amount) {
        int after_remove = getMaxMana(p) - amount;
        data.replace(getData(p, ManagerData.MAX_MANA), Math.max(after_remove, 1000));
    }

    public static void setMaxMana(Player p, Integer amount) {
        data.put(getData(p, ManagerData.MAX_MANA), Math.max(amount, 1000));
    }
}

