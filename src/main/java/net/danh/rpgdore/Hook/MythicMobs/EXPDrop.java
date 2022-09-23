package net.danh.rpgdore.Hook.MythicMobs;

import io.lumine.mythic.api.config.MythicConfig;
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import net.danh.dcore.Random.Number;
import net.danh.rpgdore.Manager.Hologram;
import net.danh.rpgdore.Manager.PData.XP;
import net.danh.rpgdore.Resource.File;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

public class EXPDrop implements Listener {

    public boolean getChance(String chance) {
        if (chance.contains("%")) {
            String c_s = chance.replace("%", "");
            double c_i = Number.getDouble(c_s);
            double s_c = Number.getRandomDouble(0.0, 100.0);
            return c_i > s_c;
        } else {
            double c_i = Number.getDouble(chance);
            c_i = c_i * 100;
            double s_c = Number.getRandomDouble(0.0, 100.0);
            return c_i > s_c;
        }
    }

    @EventHandler
    public void onExpDrop(MythicMobDeathEvent e) {
        if (e.getKiller() instanceof Player p) {
            MythicConfig config = e.getMobType().getConfig();
            for (String s : config.getStringList("Drops")) {
                if (s.contains("rpgdore-xp")) {
                    String[] s1 = s.split(" ");
                    int xp = Number.getInt(s1[1]);
                    if (s1[0].equalsIgnoreCase("rpgdore-xp")) {
                        if (xp > 0) {
                            if (getChance(s1[2])) {
                                XP.addXP(p, xp);
                                Hologram.createHolo(p, e.getMob().getLocation().toPosition().toLocation(), Objects.requireNonNull(File.getConfig().getConfig().getString("holo.xp")).replaceAll("#xp#", String.format("%,d", xp)).replaceAll("#player#", p.getDisplayName()));
                            }
                        }
                    }
                }
            }
        }
    }
}
