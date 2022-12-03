package net.danh.rpgdore.Hook;

import net.brcdev.shopgui.ShopGuiPlusApi;
import net.brcdev.shopgui.event.ShopGUIPlusPostEnableEvent;
import net.danh.rpgdore.Hook.MMOItems.MMOShopGUIPlus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ShopGUIPlusHook implements Listener {

    @EventHandler
    public void onShopGUIPlusPostEnable(ShopGUIPlusPostEnableEvent event) {
        ShopGuiPlusApi.registerItemProvider(new MMOShopGUIPlus());
    }
}