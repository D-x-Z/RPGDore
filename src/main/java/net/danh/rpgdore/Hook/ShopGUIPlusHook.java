package net.danh.rpgdore.Hook;

import net.brcdev.shopgui.ShopGuiPlusApi;
import net.brcdev.shopgui.event.ShopGUIPlusPostEnableEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ShopGUIPlusHook implements Listener {

    @EventHandler
    public void onShopGUIPlusPostEnable(ShopGUIPlusPostEnableEvent event) {
        ShopGuiPlusApi.registerItemProvider(new MMOItems());
    }
}
