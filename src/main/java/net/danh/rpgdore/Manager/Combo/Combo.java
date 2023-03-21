package net.danh.rpgdore.Manager.Combo;

public enum Combo {

    MOT(ClickType.RIGHT, ClickType.RIGHT, ClickType.RIGHT),
    HAI(ClickType.RIGHT, ClickType.RIGHT, ClickType.LEFT),
    BA(ClickType.RIGHT, ClickType.LEFT, ClickType.RIGHT),
    BON(ClickType.LEFT, ClickType.RIGHT, ClickType.RIGHT),
    NAM(ClickType.LEFT, ClickType.LEFT, ClickType.RIGHT),
    SAU(ClickType.LEFT, ClickType.RIGHT, ClickType.LEFT),
    BAY(ClickType.RIGHT, ClickType.LEFT, ClickType.LEFT);

    private final ClickType[] clickTypes;

    Combo(ClickType... types) {
        this.clickTypes = types;
    }

    public ClickType[] getClickTypes() {
        return clickTypes;
    }
}
