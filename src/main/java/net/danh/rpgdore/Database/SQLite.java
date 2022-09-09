package net.danh.rpgdore.Database;

import net.danh.rpgdore.RPGDore;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class SQLite extends Database {
    public String SQLiteCreateTokensTable = "CREATE TABLE IF NOT EXISTS playerdata (" + // make sure to put your table name in here too.
            "`player` LONGTEXT NOT NULL," +
            "`xp` BIGINT DEFAULT 0," +
            "`level` BIGINT DEFAULT 1," +
            "`mana` BIGINT DEFAULT 1000," +
            "`max_mana` BIGINT DEFAULT 1000," +
            "`stamina` BIGINT DEFAULT 1000," +
            "`max_stamina` BIGINT DEFAULT 1000," +
            "`class` LONGTEXT DEFAULT NONE," +
            "PRIMARY KEY (`player`)" +  // This is creating 3 columns RPGDorePlayer, Kills, Total. Primary key is what you are going to use as your indexer. Here we want to use player so
            ");"; // we can search by player, and get kills and total. If you have somehow were searching kills it would provide total and player.
    String dbname;

    public SQLite(JavaPlugin instance) {
        super(instance);
        dbname = "playerdata"; // Set the table name here e.g. player_kills
    }

    // SQL creation stuff, You can leave the blow stuff untouched.
    public Connection getSQLConnection() {
        File dataFolder = new File(plugin.getDataFolder(), dbname + ".db");
        if (!dataFolder.exists()) {
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                RPGDore.getRPGDore().getLogger().log(Level.SEVERE, "File write error: " + dbname + ".db");
            }
        }
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return connection;
        } catch (SQLException ex) {
            RPGDore.getRPGDore().getLogger().log(Level.SEVERE, "SQLite exception on initialize", ex);
        } catch (ClassNotFoundException ex) {
            RPGDore.getRPGDore().getLogger().log(Level.SEVERE, "You need the SQLite J BDC library. Google it. Put it in /lib folder.");
        }
        return null;
    }

    public void load() {
        connection = getSQLConnection();
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(SQLiteCreateTokensTable);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initialize();
    }
}
