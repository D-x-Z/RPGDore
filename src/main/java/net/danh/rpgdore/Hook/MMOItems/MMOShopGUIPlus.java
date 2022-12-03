package net.danh.rpgdore.Hook.MMOItems;

import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import net.brcdev.shopgui.provider.item.ItemProvider;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import static net.Indyuce.mmoitems.MMOItems.plugin;

public class MMOShopGUIPlus extends ItemProvider {
    public MMOShopGUIPlus() {
        super("MMOItems");
    }

    @Override
    public boolean isValidItem(ItemStack itemStack) {
        return NBTItem.get(itemStack).hasType() && NBTItem.get(itemStack).get("MMOITEMS_ITEM_ID") != null;
    }

    @Override
    public ItemStack loadItem(ConfigurationSection configurationSection) {
        String type = configurationSection.getString("mmoItems.type");
        String id = configurationSection.getString("mmoItems.id");
        MMOItem mmoitem = plugin.getMMOItem(plugin.getTypes().get(type), id);
        if (mmoitem != null) {
            return mmoitem.newBuilder().build();
        }
        return null;
    }

    @Override
    public boolean compare(ItemStack itemStack, ItemStack itemStack1) {
        NBTItem item1 = NBTItem.get(itemStack);
        NBTItem item2 = NBTItem.get(itemStack1);
        return item1.getType().equalsIgnoreCase(item2.getType()) && item1.get("MMOITEMS_ITEM_ID").equals(item2.get("MMOITEMS_ITEM_ID"));
    }

}