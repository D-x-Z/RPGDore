package net.danh.rpgdore.Command;

import net.danh.dcore.Commands.CMDBase;
import net.danh.rpgdore.Manager.Class.ClassManager;
import net.danh.rpgdore.Manager.PData.ClassName;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassCMD extends CMDBase {
    public ClassCMD(JavaPlugin core) {
        super(core, "class");
    }

    @Override
    public void playerexecute(Player p, String[] args) {
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("cast")) {
                String skill = args[1];
                String classname = ClassName.getClassName(p);
                new ClassManager(classname).castSkill(p, skill);
            }
        }
    }

    @Override
    public void consoleexecute(ConsoleCommandSender consoleCommandSender, String[] strings) {

    }

    @Override
    public List<String> TabComplete(CommandSender commandSender, String[] args) {
        if (commandSender instanceof Player p) {
            List<String> completions = new ArrayList<>();
            List<String> commands = new ArrayList<>();
            if (args.length == 1) {
                commands.add("cast");
                StringUtil.copyPartialMatches(args[0], commands, completions);
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("cast")) {
                    String classname = ClassName.getClassName(p);
                    StringUtil.copyPartialMatches(args[1], new ClassManager(classname).getSkillName(), completions);
                }
            }
            Collections.sort(completions);
            return completions;
        } else {
            return null;
        }
    }
}
