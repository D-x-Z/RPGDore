package net.danh.rpgdore.Manager;

import net.danh.dcore.Utils.DVersion;
import net.danh.dcore.Utils.Status;
import net.danh.rpgdore.RPGDore;

public class Version extends DVersion {

    @Override
    public String getOriginalVersion() {
        return RPGDore.getRPGDore().getDescription().getVersion();
    }

    @Override
    public String getDevBuildVersion() {
        return Status.TRUE.getSymbol();
    }

    @Override
    public Status isDevBuild() {
        return Status.TRUE;
    }

    @Override
    public Status isPremium() {
        return Status.FALSE;
    }

    @Override
    public String getReleaseLink() {
        return Status.FALSE.getSymbol();
    }
}
