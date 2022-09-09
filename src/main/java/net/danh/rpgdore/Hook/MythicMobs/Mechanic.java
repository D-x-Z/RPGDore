package net.danh.rpgdore.Hook.MythicMobs;

import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.ITargetedEntitySkill;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.api.skills.SkillResult;
import io.lumine.mythic.bukkit.BukkitAdapter;
import net.danh.rpgdore.Manager.Hologram;
import net.danh.rpgdore.Manager.PData.Level;
import net.danh.rpgdore.Manager.PData.Mana;
import net.danh.rpgdore.Manager.PData.Stamina;
import net.danh.rpgdore.Manager.PData.XP;
import net.danh.rpgdore.Resource.File;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Random;

public class Mechanic implements ITargetedEntitySkill {

    protected final String action;
    protected final String type;
    protected final String amount;

    public Mechanic(MythicLineConfig config) {
        this.action = config.getString(new String[]{"action"});
        this.type = config.getString(new String[]{"type"});
        this.amount = config.getString(new String[]{"amount"});
    }

    @Override
    public SkillResult castAtEntity(SkillMetadata data, AbstractEntity target) {
        if (target.isPlayer()) {
            Player p = (Player) BukkitAdapter.adapt(target);
            if (p != null) {
                if (action.equalsIgnoreCase("add")) {
                    if (amount.contains("-")) {
                        if (Integer.parseInt(amount.split("-")[1]) - Integer.parseInt(amount.split("-")[0]) > 1) {
                            int RPGDore = new Random().nextInt(Integer.parseInt(amount.split("-")[0]), Integer.parseInt(amount.split("-")[1]));
                            if (type.equalsIgnoreCase("xp")) {
                                XP.addXP(p, RPGDore);
                                Hologram.createHolo(p, data.getCaster().getLocation().toPosition().toLocation(), Objects.requireNonNull(File.getConfig().getConfig().getString("holo.xp"))
                                        .replaceAll("#xp#", String.format("%,d", RPGDore))
                                        .replaceAll("#player#", p.getDisplayName()));
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("level")) {
                                Level.addLevel(p, RPGDore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("mana")) {
                                Mana.addMana(p, RPGDore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("max_mana")) {
                                Mana.addMaxMana(p, RPGDore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("stamina")) {
                                Stamina.addStamina(p, RPGDore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("max_stamina")) {
                                Stamina.addMaxStamina(p, RPGDore);
                                return SkillResult.SUCCESS;
                            }
                        } else {
                            int RPGDore = Integer.parseInt(amount.split("-")[0]);
                            if (type.equalsIgnoreCase("xp")) {
                                XP.addXP(p, RPGDore);
                                Hologram.createHolo(p, data.getCaster().getLocation().toPosition().toLocation(), Objects.requireNonNull(File.getConfig().getConfig().getString("holo.xp"))
                                        .replaceAll("#xp#", String.format("%,d", RPGDore))
                                        .replaceAll("#player#", p.getDisplayName()));
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("level")) {
                                Level.addLevel(p, RPGDore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("mana")) {
                                Mana.addMana(p, RPGDore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("max_mana")) {
                                Mana.addMaxMana(p, RPGDore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("stamina")) {
                                Stamina.addStamina(p, RPGDore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("max_stamina")) {
                                Stamina.addMaxStamina(p, RPGDore);
                                return SkillResult.SUCCESS;
                            }
                        }
                    } else {
                        int RPGDore = Integer.parseInt(amount);
                        if (type.equalsIgnoreCase("xp")) {
                            XP.addXP(p, RPGDore);
                            Hologram.createHolo(p, data.getCaster().getLocation().toPosition().toLocation(), Objects.requireNonNull(File.getConfig().getConfig().getString("holo.xp"))
                                    .replaceAll("#xp#", String.format("%,d", RPGDore))
                                    .replaceAll("#player#", p.getDisplayName()));
                            return SkillResult.SUCCESS;
                        }
                        if (type.equalsIgnoreCase("level")) {
                            Level.addLevel(p, RPGDore);
                            return SkillResult.SUCCESS;
                        }
                        if (type.equalsIgnoreCase("mana")) {
                            Mana.addMana(p, RPGDore);
                            return SkillResult.SUCCESS;
                        }
                        if (type.equalsIgnoreCase("max_mana")) {
                            Mana.addMaxMana(p, RPGDore);
                            return SkillResult.SUCCESS;
                        }
                        if (type.equalsIgnoreCase("stamina")) {
                            Stamina.addStamina(p, RPGDore);
                            return SkillResult.SUCCESS;
                        }
                        if (type.equalsIgnoreCase("max_stamina")) {
                            Stamina.addMaxStamina(p, RPGDore);
                            return SkillResult.SUCCESS;
                        }
                    }
                }
                if (action.equalsIgnoreCase("remove")) {
                    if (amount.contains("-")) {
                        if (Integer.parseInt(amount.split("-")[1]) - Integer.parseInt(amount.split("-")[0]) > 1) {
                            int RPGDore = new Random().nextInt(Integer.parseInt(amount.split("-")[0]), Integer.parseInt(amount.split("-")[1]));
                            if (type.equalsIgnoreCase("xp")) {
                                XP.removeXP(p, RPGDore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("level")) {
                                Level.removeLevel(p, RPGDore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("mana")) {
                                Mana.removeMana(p, RPGDore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("max_mana")) {
                                Mana.removeMaxMana(p, RPGDore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("stamina")) {
                                Stamina.removeStamina(p, RPGDore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("max_stamina")) {
                                Stamina.removeMaxStamina(p, RPGDore);
                                return SkillResult.SUCCESS;
                            }
                        } else {
                            int rpgdore = Integer.parseInt(amount.split("-")[0]);
                            if (type.equalsIgnoreCase("xp")) {
                                XP.removeXP(p, rpgdore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("level")) {
                                Level.removeLevel(p, rpgdore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("mana")) {
                                Mana.removeMana(p, rpgdore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("max_mana")) {
                                Mana.removeMaxMana(p, rpgdore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("stamina")) {
                                Stamina.removeStamina(p, rpgdore);
                                return SkillResult.SUCCESS;
                            }
                            if (type.equalsIgnoreCase("max_stamina")) {
                                Stamina.removeMaxStamina(p, rpgdore);
                                return SkillResult.SUCCESS;
                            }
                        }
                    } else {
                        int rpgdore = Integer.parseInt(amount);
                        if (type.equalsIgnoreCase("xp")) {
                            XP.removeXP(p, rpgdore);
                            return SkillResult.SUCCESS;
                        }
                        if (type.equalsIgnoreCase("level")) {
                            Level.removeLevel(p, rpgdore);
                            return SkillResult.SUCCESS;
                        }
                        if (type.equalsIgnoreCase("mana")) {
                            Mana.removeMana(p, rpgdore);
                            return SkillResult.SUCCESS;
                        }
                        if (type.equalsIgnoreCase("max_mana")) {
                            Mana.removeMaxMana(p, rpgdore);
                            return SkillResult.SUCCESS;
                        }
                        if (type.equalsIgnoreCase("stamina")) {
                            Stamina.removeStamina(p, rpgdore);
                            return SkillResult.SUCCESS;
                        }
                        if (type.equalsIgnoreCase("max_stamina")) {
                            Stamina.removeMaxStamina(p, rpgdore);
                            return SkillResult.SUCCESS;
                        }
                    }
                }
            }
        }
        return SkillResult.SUCCESS;
    }
}
