package net.danh.rpgdore;

import net.Indyuce.mmoitems.MMOItems;
import net.danh.dcore.NMS.NMSAssistant;
import net.danh.dcore.Resource.Files;
import net.danh.dcore.Utils.Chat;
import net.danh.dcore.Utils.Status;
import net.danh.rpgdore.Command.CMDBase;
import net.danh.rpgdore.Database.Database;
import net.danh.rpgdore.Database.SQLite;
import net.danh.rpgdore.Event.JoinQuit;
import net.danh.rpgdore.Hook.MythicMobs.Event.Condition;
import net.danh.rpgdore.Hook.MythicMobs.Event.Mechanic;
import net.danh.rpgdore.Hook.MythicMobs.Event.Reload;
import net.danh.rpgdore.Hook.PlaceholderAPI;
import net.danh.rpgdore.Hook.ShopGUIPlusHook;
import net.danh.rpgdore.MMOItems.Handler;
import net.danh.rpgdore.Manager.Hologram;
import net.danh.rpgdore.Manager.ManagerPlayerData;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class RPGDore extends JavaPlugin {

    private static RPGDore ins;
    private static Database db;

    public static Database getDatabase() {
        return db;
    }

    public static RPGDore getRPGDore() {
        return ins;
    }

    public static RPGDore.SERVER_TYPE getServerType() {
        if (hasClass("com.destroystokyo.paper.PaperConfig") || hasClass("io.papermc.paper.configuration.Configuration")) {
            return RPGDore.SERVER_TYPE.PAPER;
        } else if (hasClass("org.spigotmc.SpigotConfig")) {
            return RPGDore.SERVER_TYPE.SPIGOT;
        } else {
            return RPGDore.SERVER_TYPE.BUKKIT;
        }
    }

    private static boolean hasClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public void onLoad() {
        ins = this;
        if (getServer().getPluginManager().getPlugin("MMOItems") != null) {
            MMOItems.plugin.setRPG(new Handler());
        }
    }

    @Override
    public void onEnable() {
        checkVersion(this);
        RPGDore.getRPGDore().getLogger().info(Chat.colorize("&e-------------------- &bRPGDore &e--------------------"));
        registerPlaceholderAPI();
        MMOItemsHook();
        MythicMobsHook();
        registerEvents();
        registerCMD();
        loadFiles();
        loadDataBase();
        getServer().getOnlinePlayers().forEach(ManagerPlayerData::loadPlayerData);
        RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Loaded data for online players while the plugin was starting"));
        hookIntoShopGUIPlus();
        RPGDore.getRPGDore().getLogger().info(Chat.colorize("&e-------------------- &bRPGDore &e--------------------"));
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, Hologram::deleteHolo, 0L, 1L);
    }

    @Override
    public void onDisable() {
        getServer().getOnlinePlayers().forEach(ManagerPlayerData::savePlayerData);
        Hologram.deleteHolo();
        new Files(this, "config").save();
    }

    private void hookIntoShopGUIPlus() {
        if (Bukkit.getPluginManager().getPlugin("ShopGUIPlus") != null) {
            Bukkit.getPluginManager().registerEvents(new ShopGUIPlusHook(), this);
            getLogger().info("ShopGUI+ detected.");
        } else {
            getLogger().warning("ShopGUI+ not found.");
        }
    }


    private String getServerName(SERVER_TYPE server_type) {
        if (server_type.equals(SERVER_TYPE.BUKKIT)) {
            return "Bukkit";
        } else if (server_type.equals(SERVER_TYPE.SPIGOT)) {
            return "Spigot";
        } else if (server_type.equals(SERVER_TYPE.PAPER)) {
            return "Paper";
        } else {
            return "Unknown";
        }
    }

    public void checkVersion(JavaPlugin plugin) {
        NMSAssistant nms = new NMSAssistant();
        if (nms.isVersionLessThanOrEqualTo(13)) {
            RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Found Server Version: " + new NMSAssistant().getNMSVersion() + " &9( " + getServerName(SERVER_TYPE.PAPER) + " )"));
            RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.FALSE.getSymbol() + "&cRPGDore doesn't support this version, please upgrade to version 1.14+!"));
            getServer().getPluginManager().disablePlugin(plugin);
            return;
        }
        if (getServerType().equals(RPGDore.SERVER_TYPE.PAPER)) {
            RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Found Server Version: " + new NMSAssistant().getNMSVersion() + " &9( " + getServerName(SERVER_TYPE.PAPER) + " )"));
        } else if (getServerType().equals(RPGDore.SERVER_TYPE.SPIGOT)) {
            RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Found Server Version: " + new NMSAssistant().getNMSVersion() + " &9( " + getServerName(SERVER_TYPE.SPIGOT) + " )"));
            RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&c Please use PaperMC for fully support"));
        } else if (getServerType().equals(RPGDore.SERVER_TYPE.BUKKIT)) {
            RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Found Server Version: " + new NMSAssistant().getNMSVersion() + " &9( " + getServerName(SERVER_TYPE.BUKKIT) + " )"));
            RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&c Please use PaperMC for fully support"));
            getServer().getPluginManager().disablePlugin(plugin);
            return;
        }
        RPGDore.getRPGDore().getLogger().info(Chat.colorize("&7"));
    }

    public void registerPlaceholderAPI() {
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPI().register();
            RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Loaded system compatible with PlaceholderAPI"));
            RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e %rpgdore_xp%, %rpgdore_level%, %rpgdore_xp_req%, %rpgdore_mana%, %rpgdore_max_mana%, %rpgdore_stamina%, %rpgdore_max_stamina%, %rpgdore_version%"));
            RPGDore.getRPGDore().getLogger().info(Chat.colorize("&7"));
        }
    }

    public void MMOItemsHook() {
        if (getServer().getPluginManager().getPlugin("MMOItems") != null) {
            MMOItems.plugin.setRPG(new Handler());
            RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Loaded system compatible with MMOItems"));
            RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Hooked RPGDore data (player's level, player's mana, player's stamina) to MMOItems"));
            RPGDore.getRPGDore().getLogger().info(Chat.colorize("&7"));
        }

    }

    public void MythicMobsHook() {
        Plugin mythicmobs = getServer().getPluginManager().getPlugin("MythicMobs");
        if (mythicmobs != null) {
            if (!mythicmobs.getDescription().getVersion().startsWith("4")) {
                getServer().getPluginManager().registerEvents(new Reload(), this);
                getServer().getPluginManager().registerEvents(new Mechanic(), this);
                getServer().getPluginManager().registerEvents(new Condition(), this);
                RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Loaded system compatible with MythicMobs"));
                RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Mechanics:"));
                RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e - rpgdore_mechanic{action=[add/remove];type=[xp/level/mana/max_mana/stamina/max_stamina];amount=[number-number/number]}"));
                RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e TargetConditions:"));
                RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e - rpgdore_condition{t=[xp/level/mana/max_mana/stamina/max_stamina];a=[number]} true"));
            } else {
                RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.FALSE.getSymbol() + "&c Loaded system compatible with MythicMobs"));
                RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.FALSE.getSymbol() + "&c You can't register custom mechanics and conditions in MythicMobs v4!"));
            }
            RPGDore.getRPGDore().getLogger().info(Chat.colorize("&7"));
        }
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new JoinQuit(), this);
        RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Registered events"));
        RPGDore.getRPGDore().getLogger().info(Chat.colorize("&7"));
    }

    public void registerCMD() {
        new CMDBase(this);
        RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Registered commands"));
        RPGDore.getRPGDore().getLogger().info(Chat.colorize("&7"));
    }

    public void loadFiles() {
        new Files(this, "config").load();
        RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Loaded config"));
        RPGDore.getRPGDore().getLogger().info(Chat.colorize("&7"));
    }

    public void loadDataBase() {
        db = new SQLite(this);
        db.load();
        RPGDore.getRPGDore().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Loaded player data (SQLite)"));
        RPGDore.getRPGDore().getLogger().info(Chat.colorize("&7"));
    }


    public enum SERVER_TYPE {
        PAPER, SPIGOT, BUKKIT
    }
}
