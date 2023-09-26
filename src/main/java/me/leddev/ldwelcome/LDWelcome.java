package me.leddev.ldwelcome;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class LDWelcome extends JavaPlugin implements Listener {

    private FileConfiguration config = getConfig();
    private List<String> message;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(this, this);

        message = config.getStringList("message");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.hasPlayedBefore()) {
            return;
        }

        for (String line : message) {
            line = line.replace("{player}", player.getName());
            player.sendMessage(HexUtil.translate(line));
        }
    }
}
