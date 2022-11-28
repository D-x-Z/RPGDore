package net.danh.rpgdore.Hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.danh.rpgdore.Manager.Number;
import net.danh.rpgdore.Manager.PData.Level;
import net.danh.rpgdore.Manager.PData.Mana;
import net.danh.rpgdore.Manager.PData.Stamina;
import net.danh.rpgdore.Manager.PData.XP;
import net.danh.rpgdore.RPGDore;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlaceholderAPI extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "rpgdore";
    }

    @Override
    public @NotNull String getAuthor() {
        return RPGDore.getRPGDore().getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return RPGDore.getRPGDore().getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player p, @NotNull String args) {
        if (p == null) return "";
        if (args.equalsIgnoreCase("xp")) {
            return String.valueOf(XP.getXP(p));
        }
        if (args.equalsIgnoreCase("level")) {
            return String.valueOf(Level.getLevel(p));
        }
        if (args.equalsIgnoreCase("xp_req")) {
            return String.valueOf(Level.getLevel(p) * 1000);
        }
        if (args.equalsIgnoreCase("mana")) {
            return String.valueOf(Mana.getMana(p));
        }
        if (args.equalsIgnoreCase("max_mana")) {
            return String.valueOf(Mana.getMaxMana(p));
        }
        if (args.equalsIgnoreCase("stamina")) {
            return String.valueOf(Stamina.getStamina(p));
        }
        if (args.equalsIgnoreCase("max_stamina")) {
            return String.valueOf(Stamina.getMaxStamina(p));
        }
        if (args.equalsIgnoreCase("format_xp")) {
            return Number.format(XP.getXP(p));
        }
        if (args.equalsIgnoreCase("format_level")) {
            return Number.format(Level.getLevel(p));
        }
        if (args.equalsIgnoreCase("format_xp_req")) {
            return Number.format(Level.getLevel(p) * 1000);
        }
        if (args.equalsIgnoreCase("format_mana")) {
            return Number.format(Mana.getMana(p));
        }
        if (args.equalsIgnoreCase("format_max_mana")) {
            return Number.format(Mana.getMaxMana(p));
        }
        if (args.equalsIgnoreCase("format_stamina")) {
            return Number.format(Stamina.getStamina(p));
        }
        if (args.equalsIgnoreCase("format_max_stamina")) {
            return Number.format(Stamina.getMaxStamina(p));
        }
        if (args.equalsIgnoreCase("version")) {
            return getVersion();
        }
        return null;
    }
}
