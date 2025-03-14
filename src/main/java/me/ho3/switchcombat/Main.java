package me.ho3.switchcombat;

import com.andrei1058.bedwars.api.BedWars;
import me.ho3.switchcombat.listeners.ArenaListener;
import me.ho3.switchcombat.listeners.InventoryListener;
import me.ho3.switchcombat.listeners.PlayerInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.jetbrains.annotations.NotNull;
public final class Main extends JavaPlugin {

    public static BedWars bw;
    public static Main plugin;
    public static Object ocm;
    @Override
    public void onEnable() {
        plugin = this;

        //Disable if plugin not found
        if (Bukkit.getPluginManager().getPlugin("BedWars1058") == null) {
            getLogger().severe("BedWars1058 was not found. Disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        var registration = Bukkit.getServicesManager().getRegistration(BedWars.class);
        if (null == registration) {
            getLogger().severe("Cannot hook into BedWars1058.");
            Bukkit.getPluginManager().disablePlugin(plugin);
            return;
        }
        bw = registration.getProvider();
        getLogger().info("Hooked into BedWars1058!");

        if (Bukkit.getPluginManager().getPlugin("OldCombatMechanics") == null) {
            getLogger().severe("找不到老学校战斗插件");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        registerListeners(new ArenaListener(), new InventoryListener(), new PlayerInteractListener());

    }

    @Override
    public void onDisable() {
    }

    private static void registerListeners(Listener @NotNull ... listeners) {
        PluginManager pm = Bukkit.getPluginManager();
        for (Listener l : listeners) {
            pm.registerEvents(l, plugin);
        }
    }
}
