package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class mainListener implements Listener {
    private Main main;
    public mainListener(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        event.setJoinMessage("§7[§6+§7] §6" + event.getPlayer().getDisplayName());
        if (!main.uhc_state.equals("waiting"))
        {
            Player player = event.getPlayer();
            boolean inGame = false;

            if (main.uhc_player_list.contains(player.getUniqueId().toString()))
            {
                inGame = true;
                main.uhc_real_player_list.add(player.getUniqueId().toString());
                main.uhc_real_player_number ++;
            }

            if (!inGame)
            {
                main.resetPlayer(player);
                player.teleport(new Location(Bukkit.getWorld("uhc"), 0, Bukkit.getWorld("uhc").getHighestBlockYAt(0, 0), 0));
                player.setGameMode(GameMode.SPECTATOR);
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        event.setQuitMessage("§7[§6-§7] §6" + event.getPlayer().getDisplayName());
        if (!main.uhc_state.equals("waiting"))
        {
            Player player = event.getPlayer();

            if (main.uhc_real_player_list.contains(player.getUniqueId().toString()))
            {
                main.uhc_real_player_list.remove(player.getUniqueId().toString());
                main.uhc_real_player_number --;
            }
        }
    }}
