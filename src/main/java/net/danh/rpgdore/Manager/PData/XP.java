package net.danh.rpgdore.Manager.PData;

import net.danh.rpgdore.Manager.ManagerData;
import org.bukkit.entity.Player;

import static net.danh.rpgdore.Manager.ManagerPlayerData.data;
import static net.danh.rpgdore.Manager.ManagerPlayerData.getData;

public class XP {

    public static int getXP(Player p) {
        return data.getOrDefault(getData(p, ManagerData.XP), 0);
    }

    public static void addXP(Player p, Integer amount) {
        int after_add = getXP(p) + amount;
        if (after_add < Level.getLevel(p) * 1000) {
            data.replace(getData(p, ManagerData.XP), getXP(p) + amount);
        } else {
            removeXP(p, Level.getLevel(p) * 1000);
            Level.addLevel(p, 1);
            Mana.addMaxMana(p, 1000);
            Stamina.addMaxStamina(p, 1000);
        }
    }

    public static void removeXP(Player p, Integer amount) {
        int after_remove = getXP(p) - amount;
        if (after_remove > 0) {
            data.replace(getData(p, ManagerData.XP), after_remove);
        } else {
            data.put(getData(p, ManagerData.XP), 0);
        }
    }

    public static void setXP(Player p, Integer amount) {
        if (amount < Level.getLevel(p) * 1000) {
            data.put(getData(p, ManagerData.XP), amount);
        } else {
            removeXP(p, Level.getLevel(p) * 1000);
            Level.addLevel(p, 1);
            Mana.addMaxMana(p, 1000);
            Stamina.addMaxStamina(p, 1000);
        }
    }

}

