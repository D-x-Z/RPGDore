package net.danh.rpgdore.MMOItems;

import net.Indyuce.mmoitems.api.player.PlayerData;
import net.Indyuce.mmoitems.api.player.RPGPlayer;
import net.Indyuce.mmoitems.comp.rpg.RPGHandler;
import net.danh.rpgdore.Manager.PData.Level;
import net.danh.rpgdore.Manager.PData.Mana;
import net.danh.rpgdore.Manager.PData.Stamina;
import org.jetbrains.annotations.NotNull;

public class Handler implements RPGHandler {
    @Override
    public RPGPlayer getInfo(PlayerData playerData) {
        return new RPGDorePlayer(playerData);
    }

    @Override
    public void refreshStats(PlayerData playerData) {

    }

    public static class RPGDorePlayer extends RPGPlayer {

        public RPGDorePlayer(@NotNull PlayerData playerData) {
            super(playerData);
        }

        @Override
        public int getLevel() {
            return Level.getLevel(getPlayer());
        }

        @Override
        public String getClassName() {
            return "";
        }

        @Override
        public double getMana() {
            return Mana.getMana(getPlayer());
        }

        @Override
        public void setMana(double v) {
            Mana.setMana(getPlayer(), (int) v);
        }

        @Override
        public double getStamina() {
            return Stamina.getStamina(getPlayer());
        }

        @Override
        public void setStamina(double v) {
            Stamina.setStamina(getPlayer(), (int) v);
        }
    }
}
