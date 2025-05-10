package me.ho3.switchcombat.ui;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import me.ho3.switchcombat.Main;
import me.ho3.switchcombat.OCMAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SwitchVersionGUI {
    public static final String SWTICH_VERSION_IDENTIFIER = "BWSWITCHVERSION";
    public static ArrayList<UUID> openGUIs = new ArrayList<>();

    public static void openGUI(Player player) {
        IArena arena = Main.bw.getArenaUtil().getArenaByPlayer(player);
        if (arena == null) return;
        if (arena.getStatus() == GameState.playing) return;
        if (arena.getStatus() == GameState.restarting) return;

        openGUIs.add(player.getUniqueId());
        Gui gui = Gui.normal()
                .setStructure(
                        "# . . . . . . . #",
                        "# . A . . . B . #",
                        "# . . . . . . . #",
                        "# . . . . . . . #")
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('A', new SimpleItem(new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§6§l切换战斗机制到1.8"), click -> {
                    OCMAPI.setCombatMode(click.getPlayer().getUniqueId(), click.getPlayer().getWorld().getUID(), "old");
                    click.getPlayer().sendMessage("§a§l战斗机制已切换到1.8");
                    click.getPlayer().closeInventory();
                }))
                .addIngredient('B', new SimpleItem(new ItemBuilder(Material.NETHERITE_AXE).setDisplayName("§6§l切换战斗机制到1.9"), click -> {
                    OCMAPI.setCombatMode(click.getPlayer().getUniqueId(), click.getPlayer().getWorld().getUID(), "new");
                    click.getPlayer().sendMessage("§a§l战斗机制已切换到1.9");
                    click.getPlayer().closeInventory();
                }))
                .build();
        Window window = Window.single()
                .setViewer(player)
                .setTitle("切换战斗机制")
                .setGui(gui)
                .build();

        window.open();
//        int size = 27;
//        Inventory inv = Bukkit.createInventory(null, size + 9, "切换战斗机制");
//        final var diamondSword = new ItemStack(Material.valueOf("DIAMOND_SWORD"));
//        inv.setItem(9, diamondSword);
//        final var swordItemMeta = diamondSword.getItemMeta();
//        if (null == swordItemMeta) {
//            return;
//        }
//        swordItemMeta.setDisplayName("§6§l切换战斗机制到1.8");
//        Main.bw.getVersionSupport().addCustomData(diamondSword, "SWITCH_1_8");
//
//        final var diamondAxe = new ItemStack(Material.valueOf("DIAMOND_AXE"));
//        final var itemMeta = diamondAxe.getItemMeta();
//        if (null == itemMeta) {
//            return;
//        }
//        itemMeta.setDisplayName("§6§l切换战斗机制到1.9");
//        inv.setItem(13, diamondAxe);
//        Main.bw.getVersionSupport().addCustomData(diamondAxe, "SWITCH_1_9");
//        player.openInventory(inv);
    }

    public static void giveItem(Player p) {
        ItemStack i;
        try {
            i = new ItemStack(Material.valueOf("DIAMOND_SWORD"));
        } catch (Exception ex) {
            Main.plugin.getLogger().severe("what the hell Material is invalid!");
            //noinspection CallToPrintStackTrace
            ex.printStackTrace();
            return;
        }


        ItemMeta im = i.getItemMeta();
        if (null == im) {
            return;
        }
        List<String> lore = new ArrayList<>();
        lore.add("当我听到策划提出这个时, 我觉得要把它送进医院里");
        im.setLore(lore);
        im.setDisplayName("§6§l切换战斗机制");
        i.setItemMeta(im);
        i = Main.bw.getVersionSupport().addCustomData(i, SWTICH_VERSION_IDENTIFIER);
        p.getInventory().setItem(1, i);
    }
}
