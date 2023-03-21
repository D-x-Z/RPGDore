package net.danh.rpgdore.Manager;

import net.danh.rpgdore.Manager.Combo.Combo;
import net.danh.rpgdore.Manager.Combo.ComboManager;
import net.danh.rpgdore.Manager.Combo.Manager;
import net.danh.rpgdore.Manager.PData.*;
import net.danh.rpgdore.RPGDore;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.HashMap;

public class ManagerPlayerData {

    public static HashMap<String, Integer> data = new HashMap<>();
    public static HashMap<String, String> classname = new HashMap<>();

    @NotNull
    public static String getData(@NotNull Player p, @NotNull ManagerData type) {
        return p.getName() + "_" + type.getName();
    }

    public static void loadPlayerData(Player p) {
        try {
            PlayerData playerData = ManagerPlayerData.getPlayerDatabase(p);
            Level.setLevel(p, playerData.getLevel());
            XP.setXP(p, playerData.getXP());
            Mana.setMana(p, playerData.getMana());
            Mana.setMaxMana(p, playerData.getMaxMana());
            Stamina.setStamina(p, playerData.getStamina());
            Stamina.setMaxStamina(p, playerData.getMaxStamina());
            ClassName.setClassName(p, playerData.getClassName());
            Priority.setPriority(p, playerData.getPriority());
            Manager.setSkill(p, Combo.MOT, playerData.getComboManager().getSkill1());
            Manager.setSkill(p, Combo.HAI, playerData.getComboManager().getSkill2());
            Manager.setSkill(p, Combo.BA, playerData.getComboManager().getSkill3());
            Manager.setSkill(p, Combo.BON, playerData.getComboManager().getSkill4());
            Manager.setSkill(p, Combo.NAM, playerData.getComboManager().getSkill5());
            Manager.setSkill(p, Combo.SAU, playerData.getComboManager().getSkill6());
            Manager.setSkill(p, Combo.BAY, playerData.getComboManager().getSkill7());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void savePlayerData(Player p) {
        RPGDore.getDatabase().updateTable(new PlayerData(p.getName(), XP.getXP(p)
                , Level.getLevel(p), Mana.getMana(p), Mana.getMaxMana(p), Stamina.getStamina(p), Stamina.getMaxStamina(p), ClassName.getClassName(p)
                , Priority.getPriority(p), new ComboManager(Manager.getSkill(p, Combo.MOT), Manager.getSkill(p, Combo.HAI)
                , Manager.getSkill(p, Combo.BA), Manager.getSkill(p, Combo.BON), Manager.getSkill(p, Combo.NAM)
                , Manager.getSkill(p, Combo.SAU), Manager.getSkill(p, Combo.BAY))));
    }

    public static PlayerData getPlayerDatabase(Player player) throws SQLException {

        PlayerData playerStats = RPGDore.getDatabase().getPlayerData(player.getName());

        if (playerStats == null) {
            playerStats = new PlayerData(player.getName(), 0, 1, 1000, 1000, 1000, 1000, "NONE", 0, new ComboManager("NONE", "NONE", "NONE", "NONE", "NONE", "NONE", "NONE"));
            RPGDore.getDatabase().createTable(playerStats);
        }

        return playerStats;
    }

}
