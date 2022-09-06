package net.danh.rpgdore.Manager;

public enum ManagerData {
    MANA("mana"),
    MAX_MANA("max_mana"),
    STAMINA("stamina"),
    MAX_STAMINA("max_stamina"),
    LEVEL("level"),
    XP("xp");

    private final String name;

    ManagerData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
