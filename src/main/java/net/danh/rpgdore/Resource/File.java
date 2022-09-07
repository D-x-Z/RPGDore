package net.danh.rpgdore.Resource;

import net.danh.dcore.Resource.Files;
import net.danh.dcore.Utils.Chat;
import net.danh.dcore.Utils.Status;
import net.danh.rpgdore.Manager.Class.ClassManager;
import net.danh.rpgdore.RPGDore;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class File {

    public static void reloadFiles(CommandSender sender) {
        getConfig().save();
        getConfig().load();
        getMessage().save();
        getMessage().load();
        reloadClass(sender);
        sender.sendMessage(Chat.colorize("&aReload complete!"));
    }

    public static Files getConfig() {
        return new Files(RPGDore.getRPGDore(), "config");
    }

    public static Files getMessage() {
        return new Files(RPGDore.getRPGDore(), "message");
    }

    public static void reloadClass(CommandSender sender) {
        for (String class_name : Objects.requireNonNull(File.getConfig().getConfig().getConfigurationSection("class")).getKeys(false)) {
            boolean enable = File.getConfig().getConfig().getBoolean("class." + class_name + ".enable");
            int priority = File.getConfig().getConfig().getInt("class." + class_name + ".priority");
            ClassManager classManager = new ClassManager(class_name);
            if (enable) {
                if (!class_name.equalsIgnoreCase("ex-class-file")) {
                    classManager.getFileManager().save(true, false);
                    sender.sendMessage(Chat.colorize(Status.TRUE.getSymbol() + "#dbdf7f Loaded class " + class_name + " with priority " + priority));
                }
            }
        }
    }
}
