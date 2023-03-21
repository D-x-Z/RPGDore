package net.danh.rpgdore.Command;

import net.danh.dcore.Commands.CMDBase;
import net.danh.rpgdore.Manager.Class.ClassManager;
import net.danh.rpgdore.Manager.Combo.Combo;
import net.danh.rpgdore.Manager.Combo.Manager;
import net.danh.rpgdore.Manager.PData.ClassName;
import net.danh.rpgdore.Resource.File;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClassCMD extends CMDBase {
    public ClassCMD(JavaPlugin core) {
        super(core, "class");
    }

    @Override
    public void playerexecute(Player p, String[] args) {
        if (args.length == 1) {
            net.danh.dcore.Utils.Player.sendPlayerMessage(p, File.getMessage().getConfig().getStringList("class_help"));
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("cast")) {
                String skill = args[1];
                String classname = ClassName.getClassName(p);
                new ClassManager(classname).castSkill(p, skill);
            }
        }
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("bind")) {
                String combo = args[1];
                String skill = args[2];
                if (combo.equalsIgnoreCase("RRR")) {
                    Manager.setSkill(p, Combo.MOT, skill);
                    net.danh.dcore.Utils.Player.sendPlayerMessage(p, File.getMessage().getConfig().getString("bind_skill", "&aBinded skill #name# to #combo#").replaceAll("#name#", skill).replaceAll("#combo#", File.getMessage().getConfig().getString("combo.RRR", "Right Right Right")));
                }
                if (combo.equalsIgnoreCase("RRL")) {
                    Manager.setSkill(p, Combo.HAI, skill);
                    net.danh.dcore.Utils.Player.sendPlayerMessage(p, File.getMessage().getConfig().getString("bind_skill", "&aBinded skill #name# to #combo#").replaceAll("#name#", skill).replaceAll("#combo#", File.getMessage().getConfig().getString("combo.RRL", "Right Right Left")));
                }
                if (combo.equalsIgnoreCase("RLR")) {
                    Manager.setSkill(p, Combo.BA, skill);
                    net.danh.dcore.Utils.Player.sendPlayerMessage(p, File.getMessage().getConfig().getString("bind_skill", "&aBinded skill #name# to #combo#").replaceAll("#name#", skill).replaceAll("#combo#", File.getMessage().getConfig().getString("combo.RLR", "Right Left Right")));
                }
                if (combo.equalsIgnoreCase("LRR")) {
                    Manager.setSkill(p, Combo.BON, skill);
                    net.danh.dcore.Utils.Player.sendPlayerMessage(p, File.getMessage().getConfig().getString("bind_skill", "&aBinded skill #name# to #combo#").replaceAll("#name#", skill).replaceAll("#combo#", File.getMessage().getConfig().getString("combo.LRR", "Left Right Right")));
                }
                if (combo.equalsIgnoreCase("LLR")) {
                    Manager.setSkill(p, Combo.NAM, skill);
                    net.danh.dcore.Utils.Player.sendPlayerMessage(p, File.getMessage().getConfig().getString("bind_skill", "&aBinded skill #name# to #combo#").replaceAll("#name#", skill).replaceAll("#combo#", File.getMessage().getConfig().getString("combo.LLR", "Left Right Right")));
                }
                if (combo.equalsIgnoreCase("LRL")) {
                    Manager.setSkill(p, Combo.SAU, skill);
                    net.danh.dcore.Utils.Player.sendPlayerMessage(p, File.getMessage().getConfig().getString("bind_skill", "&aBinded skill #name# to #combo#").replaceAll("#name#", skill).replaceAll("#combo#", File.getMessage().getConfig().getString("combo.LRL", "Left Right Left")));
                }
                if (combo.equalsIgnoreCase("RLL")) {
                    Manager.setSkill(p, Combo.BAY, skill);
                    net.danh.dcore.Utils.Player.sendPlayerMessage(p, File.getMessage().getConfig().getString("bind_skill", "&aBinded skill #name# to #combo#").replaceAll("#name#", skill).replaceAll("#combo#", File.getMessage().getConfig().getString("combo.RLL", "Right Left Left")));
                }
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
            List<String> combo = new ArrayList<>(Arrays.asList("RRR", "RRL", "RLR", "LRR", "LLR", "LRL", "RLL"));
            String classname = ClassName.getClassName(p);
            if (args.length == 1) {
                commands.add("help");
                commands.add("cast");
                commands.add("bind");
                StringUtil.copyPartialMatches(args[0], commands, completions);
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("cast")) {
                    StringUtil.copyPartialMatches(args[1], new ClassManager(classname).getSkillName(), completions);
                }
                if (args[0].equalsIgnoreCase("bind")) {
                    StringUtil.copyPartialMatches(args[1], combo, completions);
                }
            }
            if (args.length == 3) {
                if (combo.contains(args[1])) {
                    StringUtil.copyPartialMatches(args[2], new ClassManager(classname).getSkillName(), completions);
                }
            }
            Collections.sort(completions);
            return completions;
        } else {
            return null;
        }
    }
}
