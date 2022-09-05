package net.danh.rpgdore.Manager;

import net.danh.dcore.Resource.Files;
import net.danh.rpgdore.Manager.PData.Level;
import net.danh.rpgdore.Manager.PData.Mana;
import net.danh.rpgdore.Manager.PData.Stamina;
import net.danh.rpgdore.Manager.PData.XP;
import net.danh.rpgdore.RPGDore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.HashMap;

public class ManagerPlayerData {

    public static HashMap<String, Integer> data = new HashMap<>();

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void savePlayerData(Player p) {
        RPGDore.getDatabase().updateTable(new PlayerData(p.getName(), XP.getXP(p), Level.getLevel(p), Mana.getMana(p), Mana.getMaxMana(p), Stamina.getStamina(p), Stamina.getMaxStamina(p)));
    }

    public static PlayerData getPlayerDatabase(Player player) throws SQLException {

        PlayerData playerStats = RPGDore.getDatabase().getPlayerData(player.getName());

        if (playerStats == null) {
            playerStats = new PlayerData(player.getName(), 0, 1, 1000, 1000, 1000, 1000);
            RPGDore.getDatabase().createTable(playerStats);
        }

        return playerStats;
    }

    public static FileConfiguration getConfig() {
        return new Files(RPGDore.getRPGDore(), "config").getConfig();
    }

}
