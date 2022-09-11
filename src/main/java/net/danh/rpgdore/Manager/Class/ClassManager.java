package net.danh.rpgdore.Manager.Class;

import io.lumine.mythic.bukkit.MythicBukkit;
import net.danh.dcore.Utils.Chat;
import net.danh.rpgdore.Manager.PData.Level;
import net.danh.rpgdore.Resource.File;
import net.danh.rpgdore.Resource.FileManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ClassManager {

    private final String file;

    public ClassManager(String file) {
        this.file = file;
    }

    public String getFileName() {
        return file;
    }

    public FileManager getFileManager() {
        return new FileManager("Class", file);
    }

    public String getClassName() {
        return Chat.colorize(getFileManager().getConfig().getString("name", file));
    }

    public int getMaxLevel() {
        return getFileManager().getConfig().getInt("max-level", 100);
    }

    public List<String> getListSkill() {
        return getFileManager().getConfig().getStringList("skills");
    }

    public int getPriority() {
        return File.getConfig().getConfig().getInt("class." + file + ".priority", 0);
    }

    public List<String> getSkillName() {
        List<String> skills = new ArrayList<>();
        getListSkill().forEach(s -> skills.add(s.split(";")[0]));
        return skills;
    }

    public int getReqSkills(String skill) {
        for (String s : getListSkill()) {
            if (skill.equalsIgnoreCase(s.split(";")[0])) {
                return Integer.parseInt(s.split(";")[1]);
            }
        }
        return 0;
    }

    public void castSkill(Player p, String skill) {
        int level = Level.getLevel(p);
        int req = getReqSkills(skill);
        if (getSkillName().contains(skill)) {
            if (level >= req) {
                MythicBukkit.inst().getSkillManager().getSkill(skill).ifPresentOrElse(value -> MythicBukkit.inst().getAPIHelper().castSkill(p, value.getInternalName()), () -> net.danh.dcore.Utils.Player.sendPlayerMessage(p, File.getMessage().getConfig().getString("skill_is_null", "&cSkill #name# is null").replace("#name#", skill)));
            } else {
                net.danh.dcore.Utils.Player.sendPlayerMessage(p, File.getMessage().getConfig().getString("not_enough_level", "&cYou need reach level #level#").replace("#level#", String.valueOf(req)));
            }
        }
    }
}

