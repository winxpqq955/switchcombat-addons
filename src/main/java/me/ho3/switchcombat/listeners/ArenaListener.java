package me.ho3.switchcombat.listeners;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import me.ho3.switchcombat.Main;
import me.ho3.switchcombat.ui.SwitchVersionGUI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ArenaListener implements Listener {
    @EventHandler
    public void onBwArenaJoin(PlayerJoinArenaEvent e) {
        if (e.isCancelled()) return;
        if (e.isSpectator()) return;
        if (e.getArena() == null) return;
        if (e.getArena().getStatus() == GameState.waiting || e.getArena().getStatus() == GameState.starting) {
            Bukkit.getScheduler().runTaskLater(Main.plugin, () -> {
                if (e.getArena().isPlayer(e.getPlayer()) || e.getArena().getStatus() != GameState.playing) {
                    SwitchVersionGUI.giveItem(e.getPlayer());
                }
            }, 30L);
        }
    }
}
