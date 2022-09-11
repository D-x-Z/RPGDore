package net.danh.rpgdore.Manager.PData;

import net.danh.rpgdore.Manager.Class.ClassManager;
import net.danh.rpgdore.Manager.ManagerData;
import net.danh.rpgdore.Manager.ManagerPlayerData;
import net.danh.rpgdore.Resource.File;
import org.bukkit.entity.Player;

public class ClassName {

    public static String getClassName(Player p) {
        return ManagerPlayerData.classname.get(ManagerPlayerData.getData(p, ManagerData.CLASS));
    }

    public static void setClassName(Player p, String class_name) {
        int p_p = Priority.getPriority(p);
        int c_p = new ClassManager(class_name).getPriority();
        if (p_p >= c_p) {
            ManagerPlayerData.classname.put(ManagerPlayerData.getData(p, ManagerData.CLASS), class_name);
        } else {
            net.danh.dcore.Utils.Player.sendPlayerMessage(p, File.getMessage().getConfig().getString("not_enough_priority", "&cYou need reach priority #amount# to choose this class!")
                    .replaceAll("#amount#", String.valueOf(c_p)));
        }
    }
}
