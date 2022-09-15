package net.danh.rpgdore.Manager.Combo;

import net.danh.rpgdore.Resource.File;
import org.bukkit.entity.Player;

import java.util.*;

public class Manager {

    public static Map<String, List<ClickType>> comboMap = new HashMap<>();
    public static HashSet<Combo> combos = new HashSet<>();

    public static HashMap<String, String> skill = new HashMap<>();

    public static void addCombo(Combo combo) {
        combos.add(combo);
    }

    public static void addClick(Player p, ClickType click) {
        if (!comboMap.containsKey(p.getName())) {
            List<ClickType> clicks = new ArrayList<>();
            clicks.add(click);

            comboMap.put(p.getName(), clicks);
        } else {
            if (comboMap.get(p.getName()).size() == 3) {
                comboMap.get(p.getName()).clear();
            }
            comboMap.get(p.getName()).add(click);
        }
        String combo_1 = comboMap.get(p.getName()).toString();
        combo_1 = combo_1.replace("RIGHT", File.getMessage().getConfig().getString("key.Right", "RIGHT"));
        combo_1 = combo_1.replace("LEFT", File.getMessage().getConfig().getString("key.Left", "LEFT"));
        boolean bound = File.getMessage().getConfig().getBoolean("key.RemoveBound");
        if (!bound) {
            net.danh.dcore.Utils.Player.sendPlayerMessageType(p, null, combo_1);
        } else {
            net.danh.dcore.Utils.Player.sendPlayerMessageType(p, null, combo_1
                    .replace("[", "")
                    .replace("]", ""));
        }

    }

    public static Combo getCombo(Player p) {
        for (Combo value : combos) {
            if (Arrays.equals(value.getClickTypes(), comboMap.get(p.getName()).toArray())) {
                return value;
            }
        }
        return null;
    }

    public static String getSkill(Player p, Combo combo) {
        String skill = Manager.skill.get(p.getName() + "_" + combo.name().toUpperCase());
        return Objects.requireNonNullElse(skill, "NONE");
    }

    public static void setSkill(Player p, Combo combo, String skill) {
        if (!skill.equalsIgnoreCase("NONE")) {
            if (Manager.skill.containsKey(p.getName() + "_" + combo.name().toUpperCase())) {
                Manager.skill.replace(p.getName() + "_" + combo.name().toUpperCase(), skill);
            } else {
                Manager.skill.put(p.getName() + "_" + combo.name().toUpperCase(), skill);
            }
        } else {
            if (Manager.skill.containsKey(p.getName() + "_" + combo.name().toUpperCase())) {
                Manager.skill.replace(p.getName() + "_" + combo.name().toUpperCase(), null);
            } else {
                Manager.skill.put(p.getName() + "_" + combo.name().toUpperCase(), null);
            }
        }
    }

    public static Set<Combo> getCombo() {
        return combos;
    }
}
