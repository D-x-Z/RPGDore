package net.danh.rpgdore.Hook.MythicMobs;

import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.conditions.IEntityCondition;
import io.lumine.mythic.bukkit.BukkitAdapter;
import net.danh.rpgdore.Manager.PData.Level;
import net.danh.rpgdore.Manager.PData.Mana;
import net.danh.rpgdore.Manager.PData.Stamina;
import net.danh.rpgdore.Manager.PData.XP;
import org.bukkit.entity.Player;

public class Condition implements IEntityCondition {
    protected final String condition_type;
    protected final Integer amount;

    public Condition(MythicLineConfig config) {
        this.condition_type = config.getString(new String[]{"type", "t"});
        this.amount = config.getInteger(new String[]{"amount", "a"});
    }

    @Override
    public boolean check(AbstractEntity abstractEntity) {
        if (abstractEntity.isPlayer()) {
            Player p = (Player) BukkitAdapter.adapt(abstractEntity);
            if (p != null) {
                if (condition_type.equalsIgnoreCase("xp")) {
                    return XP.getXP(p) >= amount;
                }
                if (condition_type.equalsIgnoreCase("level")) {
                    return Level.getLevel(p) >= amount;
                }
                if (condition_type.equalsIgnoreCase("mana")) {
                    return Mana.getMana(p) >= amount;
                }
                if (condition_type.equalsIgnoreCase("max_mana")) {
                    return Mana.getMaxMana(p) >= amount;
                }
                if (condition_type.equalsIgnoreCase("stamina")) {
                    return Stamina.getStamina(p) >= amount;
                }
                if (condition_type.equalsIgnoreCase("max_stamina")) {
                    return Stamina.getMaxStamina(p) >= amount;
                }
            }
            return false;
        }
        return false;
    }
}

