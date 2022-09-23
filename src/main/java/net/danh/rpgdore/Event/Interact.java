package net.danh.rpgdore.Event;

import net.danh.rpgdore.Manager.Class.ClassManager;
import net.danh.rpgdore.Manager.Combo.ClickType;
import net.danh.rpgdore.Manager.Combo.Combo;
import net.danh.rpgdore.Manager.Combo.Manager;
import net.danh.rpgdore.Manager.PData.ClassName;
import net.danh.rpgdore.RPGDore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Interact implements Listener {
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
