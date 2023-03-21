package net.danh.rpgdore.Resource;

import net.danh.rpgdore.RPGDore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class FileManager {
    private final String name;
    private final JavaPlugin core;
    private final File file;
    private final String folder;
    private FileConfiguration config;

    public FileManager(String folder, String file) {
        this.name = file;
        this.folder = folder;
        this.core = RPGDore.getRPGDore();
        this.file = new File(core.getDataFolder(), folder + File.separator + name + ".yml");
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void load(boolean isEmptyFile) {
        File file = new File(this.core.getDataFolder(), folder + File.separator + this.name + ".yml");
        if (!file.exists()) {
            try {
                if (!isEmptyFile) {
                    this.core.saveResource(folder + File.separator + this.name + ".yml", false);
                } else {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void load() {
        File file = new File(this.core.getDataFolder(), folder + File.separator + this.name + ".yml");
        File ex = new File(this.core.getDataFolder(), folder + File.separator + "ex-class-file.yml");
        if (!file.exists()) {
            try {
                this.core.saveResource(folder + File.separator + "ex-class-file.yml", false);
                ex.renameTo(file);
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public String getName() {
        return this.name;
    }

    public File getFile() {
        return this.file;
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public void save(boolean isClassFile, boolean isEmptyFile) {
        if (this.file.exists()) {
            try {
                this.config.save(this.file);
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        } else {
            if (isClassFile) {
                load();
            } else {
                load(isEmptyFile);
            }
            save(isClassFile, isEmptyFile);
        }
    }

}
