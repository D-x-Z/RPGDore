package net.danh.rpgdore.Manager.PData;

import net.danh.rpgdore.Manager.ManagerData;
import net.danh.rpgdore.Manager.ManagerPlayerData;
import org.bukkit.entity.Player;

public class ClassName {

    public static String getClassName(Player p) {
        return ManagerPlayerData.classname.get(ManagerPlayerData.getData(p, ManagerData.CLASS));
    }

    public static void setClassName(Player p, String class_name) {
        ManagerPlayerData.classname.put(ManagerPlayerData.getData(p, ManagerData.CLASS), class_name);
    }
}
