package net.danh.rpgdore.Command;

import net.danh.dcore.NMS.NMSAssistant;
import net.danh.rpgdore.Manager.PData.*;
import net.danh.rpgdore.Manager.Version;
import net.danh.rpgdore.Resource.File;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.danh.dcore.Utils.Player.sendConsoleMessage;
import static net.danh.dcore.Utils.Player.sendPlayerMessage;

public class CMDBase extends net.danh.dcore.Commands.CMDBase {
    public CMDBase(JavaPlugin core) {
        super(core, "rpgdore");
    }

    @Override
    public void playerexecute(Player p, String[] args) {
        if (p.hasPermission("rpgdore.admin")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("v")) {
                    sendPlayerMessage(p, "&e&m                     &b RPGDore &e&m                    ");
                    sendPlayerMessage(p, "&6Server Version:&f " + new NMSAssistant().getNMSVersion());
                    sendPlayerMessage(p, "&6Plugin Version:&f " + new Version().getOriginalVersion());
                    sendPlayerMessage(p, "&6Premium:&f " + new Version().isPremium().getSymbol());
                    sendPlayerMessage(p, "&6Dev Build Version:&f " + new Version().getDevBuildVersion());
                    sendPlayerMessage(p, "&6Dev Build:&f " + new Version().isDevBuild().getSymbol());
                    sendPlayerMessage(p, "&6Release Link:&f " + new Version().getReleaseLink());
                    sendPlayerMessage(p, "&e&m                     &b RPGDore &e&m                    ");
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    File.reloadFiles(p);
                }
                if (args[0].equalsIgnoreCase("help")) {
                    net.danh.dcore.Utils.Player.sendPlayerMessage(p, File.getMessage().getConfig().getStringList("dore_help"));
                }
            }
            if (args.length == 4) {
                Player t = Bukkit.getPlayer(args[2]);
                if (t == null) return;
                if (args[1].equalsIgnoreCase("set")) {
                    if (args[0].equalsIgnoreCase("xp")) {
                        XP.setXP(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("level")) {
                        Level.setLevel(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("mana")) {
                        Mana.setMana(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("max_mana")) {
                        Mana.setMaxMana(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("stamina")) {
                        Stamina.setStamina(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("max_stamina")) {
                        Stamina.setMaxStamina(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("class")) {
                        ClassName.setClassName(t, args[3]);
                    }
                    if (args[0].equalsIgnoreCase("priority")) {
                        Priority.setPriority(t, Integer.valueOf(args[3]));
                    }
                }
                if (args[1].equalsIgnoreCase("add")) {
                    if (args[0].equalsIgnoreCase("xp")) {
                        XP.addXP(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("level")) {
                        Level.addLevel(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("mana")) {
                        Mana.addMana(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("max_mana")) {
                        Mana.addMaxMana(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("stamina")) {
                        Stamina.addStamina(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("max_stamina")) {
                        Stamina.addMaxStamina(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("priority")) {
                        Priority.addPriority(t, Integer.valueOf(args[3]));
                    }
                }
                if (args[1].equalsIgnoreCase("remove")) {
                    if (args[0].equalsIgnoreCase("xp")) {
                        XP.removeXP(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("level")) {
                        Level.removeLevel(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("mana")) {
                        Mana.removeMana(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("max_mana")) {
                        Mana.removeMaxMana(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("stamina")) {
                        Stamina.removeStamina(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("max_stamina")) {
                        Stamina.removeMaxStamina(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("priority")) {
                        Priority.removePriority(t, Integer.valueOf(args[3]));
                    }
                }
            }
        }
    }

    @Override
    public void consoleexecute(ConsoleCommandSender c, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("v")) {
                sendConsoleMessage(c, "&e-------------------- &bRPGDore &e--------------------");
                sendConsoleMessage(c, "&6Server Version:&f " + new NMSAssistant().getNMSVersion());
                sendConsoleMessage(c, "&6Plugin Version:&f " + new Version().getOriginalVersion());
                sendConsoleMessage(c, "&6Premium:&f " + new Version().isPremium().getSymbol());
                sendConsoleMessage(c, "&6Dev Build Version:&f " + new Version().getDevBuildVersion());
                sendConsoleMessage(c, "&6Dev Build:&f " + new Version().isDevBuild().getSymbol());
                sendConsoleMessage(c, "&6Release Link:&f " + new Version().getReleaseLink());
                sendConsoleMessage(c, "&e-------------------- &bRPGDore &e--------------------");
            }
            if (args[0].equalsIgnoreCase("reload")) {
                File.reloadFiles(c);
            }
            if (args[0].equalsIgnoreCase("help")) {
                net.danh.dcore.Utils.Player.sendConsoleMessage(c, File.getMessage().getConfig().getStringList("dore_help"));
            }
        }
        if (args.length == 4) {
            Player t = Bukkit.getPlayer(args[2]);
            if (t == null) return;
            if (args[1].equalsIgnoreCase("set")) {
                if (args[0].equalsIgnoreCase("xp")) {
                    XP.setXP(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("level")) {
                    Level.setLevel(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("mana")) {
                    Mana.setMana(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("max_mana")) {
                    Mana.setMaxMana(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("stamina")) {
                    Stamina.setStamina(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("max_stamina")) {
                    Stamina.setMaxStamina(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("class")) {
                    ClassName.setClassName(t, args[3]);
                }
                if (args[0].equalsIgnoreCase("priority")) {
                    Priority.setPriority(t, Integer.valueOf(args[3]));
                }
            }
            if (args[1].equalsIgnoreCase("add")) {
                if (args[0].equalsIgnoreCase("xp")) {
                    XP.addXP(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("level")) {
                    Level.addLevel(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("mana")) {
                    Mana.addMana(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("max_mana")) {
                    Mana.addMaxMana(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("stamina")) {
                    Stamina.addStamina(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("max_stamina")) {
                    Stamina.addMaxStamina(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("priority")) {
                    Priority.addPriority(t, Integer.valueOf(args[3]));
                }
            }
            if (args[1].equalsIgnoreCase("remove")) {
                if (args[0].equalsIgnoreCase("xp")) {
                    XP.removeXP(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("level")) {
                    Level.removeLevel(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("mana")) {
                    Mana.removeMana(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("max_mana")) {
                    Mana.removeMaxMana(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("stamina")) {
                    Stamina.removeStamina(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("max_stamina")) {
                    Stamina.removeMaxStamina(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("priority")) {
                    Priority.removePriority(t, Integer.valueOf(args[3]));
                }
            }
        }
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        List<String> arg_1 = new ArrayList<>(Arrays.asList("xp", "level", "mana", "max_mana", "stamina", "max_stamina", "class", "priority"));
        List<String> arg_2 = new ArrayList<>(Arrays.asList("add", "set", "remove"));
        if (sender.hasPermission("rpgdore.admin")) {
            if (args.length == 1) {
                commands.add("xp");
                commands.add("level");
                commands.add("help");
                commands.add("mana");
                commands.add("max_mana");
                commands.add("stamina");
                commands.add("max_stamina");
                commands.add("class");
                commands.add("version");
                commands.add("priority");
                commands.add("v");
                commands.add("reload");
                StringUtil.copyPartialMatches(args[0], commands, completions);
            }
            if (args.length == 2) {
                if (arg_1.contains(args[0])) {
                    commands.add("set");
                    commands.add("add");
                    commands.add("remove");
                    StringUtil.copyPartialMatches(args[1], commands, completions);
                }
            }
            if (args.length == 3) {
                if (arg_2.contains(args[1])) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        commands.add(p.getName());
                    }
                    StringUtil.copyPartialMatches(args[2], commands, completions);
                }
            }
        }
        Collections.sort(completions);
        return completions;
    }
}
