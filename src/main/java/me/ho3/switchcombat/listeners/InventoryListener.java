package me.ho3.switchcombat.listeners;

import me.ho3.switchcombat.Main;
import me.ho3.switchcombat.OCMAPI;
import me.ho3.switchcombat.ui.SwitchVersionGUI;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class InventoryListener implements Listener {

    @EventHandler
    //Prevent inventory move
    public void onInventoryClick(@NotNull InventoryClickEvent e) {
        ItemStack i = e.getCurrentItem();
        if (i == null) return;
        if (i.getType() == Material.AIR) return;
        if (SwitchVersionGUI.openGUIs.contains(e.getWhoClicked().getUniqueId())) e.setCancelled(true);
        if (!Main.bw.getVersionSupport().isCustomBedWarsItem(i)) return;
        String identifier = Main.bw.getVersionSupport().getCustomData(i);
        final var player = e.getWhoClicked();
        final var world = player.getWorld().getUID();
        if (identifier.equals("SWITCH_1_8")) {
            OCMAPI.setCombatMode(player.getUniqueId(), world, "old");
            player.sendMessage("§a§l战斗机制已切换到1.8");
        }else if (identifier.equals("SWITCH_1_9")) {
            OCMAPI.setCombatMode(player.getUniqueId(), world, "new");
            player.sendMessage("§a§l战斗机制已切换到1.9");
        }
        e.getWhoClicked().closeInventory();
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        SwitchVersionGUI.openGUIs.remove(e.getPlayer().getUniqueId());
    }
}