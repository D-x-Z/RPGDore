package net.danh.rpgdore.Event;

import io.lumine.mythic.api.config.MythicConfig;
import io.lumine.mythic.bukkit.BukkitAPIHelper;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.lib.api.event.PlayerAttackEvent;
import io.lumine.mythic.lib.api.player.EquipmentSlot;
import io.lumine.mythic.lib.damage.AttackMetadata;
import io.lumine.mythic.lib.damage.DamageMetadata;
import io.lumine.mythic.lib.damage.DamageType;
import io.lumine.mythic.lib.element.Element;
import io.lumine.mythic.lib.player.PlayerMetadata;
import net.Indyuce.mmoitems.api.player.PlayerData;
import net.danh.rpgdore.Manager.Class.ClassManager;
import net.danh.rpgdore.Manager.Combo.ClickType;
import net.danh.rpgdore.Manager.Combo.Combo;
import net.danh.rpgdore.Manager.Combo.Manager;
import net.danh.rpgdore.Manager.PData.ClassName;
import net.danh.rpgdore.RPGDore;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class Interact implements Listener {

    @EventHandler
    public void onEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            if (new BukkitAPIHelper().isMythicMob(e.getEntity())) {
                MythicConfig config = MythicBukkit.inst().getAPIHelper().getMythicMob(MythicBukkit.inst().getAPIHelper().getMythicMobInstance(e.getEntity()).getMobType()).getConfig();
                PlayerData playerData = PlayerData.get((Player) e.getDamager());
                DamageMetadata damage = new DamageMetadata(e.getDamage(), Objects.requireNonNull(Element.valueOf(config.getString("Elements")), "Element ID is null"), DamageType.WEAPON);
                PlayerMetadata statMap = playerData.getMMOPlayerData().getStatMap().cache(EquipmentSlot.MAIN_HAND);
                PlayerAttackEvent playerAttackEvent = new PlayerAttackEvent(e, new AttackMetadata(damage, (LivingEntity) e.getEntity(), statMap));
                Bukkit.getPluginManager().callEvent(playerAttackEvent);
            }

        }
    }

    @EventHandler
    public void onAttack(PlayerAttackEvent event) {
        if (new BukkitAPIHelper().isMythicMob(event.getEntity())) {
            if (event.getDamage().hasType(DamageType.WEAPON)) {
                event.getAttacker().getPlayer().sendMessage(MythicBukkit.inst().getAPIHelper().getMythicMobInstance(event.getEntity()).getMobType());
                event.getAttacker().getPlayer().sendMessage(MythicBukkit.inst().getAPIHelper().getMythicMob(MythicBukkit.inst().getAPIHelper().getMythicMobInstance(event.getEntity()).getMobType()).getConfig().getString("Type"));
/*                final double critChance = Math.min(event.getAttacker().getStat("CRITICAL_STRIKE_CHANCE"), MythicLib.plugin.getAttackEffects().getMaxWeaponCritChance());
                final double attackCharge = MythicLib.plugin.getVersion().getWrapper().getAttackCooldown(event.getAttacker().getPlayer());
                for (Element element : MythicLib.plugin.getElements().getAll()) {

                    // If the flat damage is 0; cancel everything asap
                    final StatProvider attackerStats = event.getAttacker();
                    double damage = attackerStats.getStat(element.getId() + "_DAMAGE") * attackCharge;
                    if (damage == 0) continue;

                    // Multiply flat damage by the percent based stat
                    final double percentDamage = attackerStats.getStat(element.getId() + "_DAMAGE_PERCENT");
                    damage *= 1 + Math.max(-1, percentDamage / 100);
                    if (damage == 0) continue;

                    // Apply elemental weakness
                    final StatProvider opponentStats = StatProvider.get(event.getEntity());
                    final double weakness = opponentStats.getStat(element.getId() + "_WEAKNESS");
                    damage *= 1 + Math.max(-1, weakness / 100);
                    if (damage == 0) continue;

                    // Apply elemental defense
                    double defense = opponentStats.getStat(element.getId() + "_DEFENSE");
                    defense *= 1 + Math.max(-1, opponentStats.getStat(element.getId() + "_DEFENSE_PERCENT") / 100);
                    damage = new DefenseFormula().getAppliedDamage(defense, damage);

                    // Register the damage packet
                    event.getDamage().add(damage, element);

                    // Apply critical strikes
                    final boolean crit = new Random().nextDouble() < critChance / 100;
                    element.getSkill(crit).cast(new TriggerMetadata(event.getAttacker(), event.getAttack(), event.getEntity()));
                    if (crit) event.getDamage().registerElementalCriticalStrike(element);
                }
*/
            }
        }
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Manager.addClick(e.getPlayer(), ClickType.RIGHT);
        }
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Manager.addClick(e.getPlayer(), ClickType.LEFT);
        }
        Combo optionalCombo = Manager.getCombo(e.getPlayer());
        if (optionalCombo != null) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(RPGDore.getRPGDore(), () -> useSkill(e.getPlayer(), optionalCombo));
        }
    }

    public void useSkill(Player p, Combo combo) {
        ClassManager classManager = new ClassManager(ClassName.getClassName(p));
        String skill = Manager.getSkill(p, combo);
        if (skill != null) {
            classManager.castSkill(p, skill);
        }
    }
}
