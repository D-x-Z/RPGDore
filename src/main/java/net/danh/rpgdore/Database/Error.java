package net.danh.rpgdore.Database;

import net.danh.rpgdore.RPGDore;

import java.util.logging.Level;

public class Error {

    public static void execute(Exception ex) {
        RPGDore.getRPGDore().getLogger().log(Level.SEVERE, "Couldn't execute MySQL statement: ", ex);
    }

    public static void close(Exception ex) {
        RPGDore.getRPGDore().getLogger().log(Level.SEVERE, "Failed to close MySQL connection: ", ex);
    }
}
