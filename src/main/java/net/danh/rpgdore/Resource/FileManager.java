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

    public void load() {
        File file = new File(this.core.getDataFolder(), folder + File.separator + this.name + ".yml");
        if (!file.exists()) {
            try {
                this.core.saveResource(folder + File.separator + this.name + ".yml", false);
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void loadWithEmptyFile() {
        File file = new File(this.core.getDataFolder(), folder + File.separator + this.name + ".yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
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

    public void save() {
        try {
            this.config.save(this.file);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}
