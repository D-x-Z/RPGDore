package net.danh.rpgdore.Resource;

import net.danh.dcore.Resource.Files;
import net.danh.rpgdore.RPGDore;

public class File {

    public static Files getConfig() {
        return new Files(RPGDore.getRPGDore(), "config");
    }
    public static Files getMessage() {
        return new Files(RPGDore.getRPGDore(), "message");
    }
}
