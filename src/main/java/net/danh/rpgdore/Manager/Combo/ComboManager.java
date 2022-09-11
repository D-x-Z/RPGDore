package net.danh.rpgdore.Manager.Combo;

public class ComboManager {
    private final String skill1;
    private final String skill2;
    private final String skill3;
    private final String skill4;
    private final String skill5;
    private final String skill6;
    private final String skill7;

    public ComboManager(String skill1, String skill2, String skill3, String skill4, String skill5, String skill6, String skill7) {
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.skill4 = skill4;
        this.skill5 = skill5;
        this.skill6 = skill6;
        this.skill7 = skill7;
    }

    public String getSkill1() {
        return skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public String getSkill4() {
        return skill4;
    }

    public String getSkill5() {
        return skill5;
    }

    public String getSkill6() {
        return skill6;
    }

    public String getSkill7() {
        return skill7;
    }
}
