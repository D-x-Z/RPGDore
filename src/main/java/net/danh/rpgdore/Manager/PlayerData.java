package net.danh.rpgdore.Manager;

public class PlayerData {

    private final String name;
    private final Integer xp;
    private final Integer level;
    private final Integer mana;
    private final Integer max_mana;
    private final Integer stamina;
    private final Integer max_stamina;

    public PlayerData(String name, Integer xp, Integer level, Integer mana, Integer max_mana, Integer stamina, Integer max_stamina) {
        this.name = name;
        this.xp = xp;
        this.level = level;
        this.mana = mana;
        this.max_mana = max_mana;
        this.stamina = stamina;
        this.max_stamina = max_stamina;
    }

    public String getPlayer() {
        return name;
    }

    public Integer getXP() {
        return xp;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getMana() {
        return mana;
    }

    public Integer getMaxMana() {
        return max_mana;
    }

    public Integer getStamina() {
        return stamina;
    }

    public Integer getMaxStamina() {
        return max_stamina;
    }
}
