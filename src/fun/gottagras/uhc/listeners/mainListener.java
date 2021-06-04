package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
        if (!main.uhc_state.equals("waiting"))
        {
            Player player = event.getPlayer();
            boolean inGame = false;

            for (int i = main.uhc_player_number;i > 0;i--)
            {
                if (main.uhc_player_list[i-1] != null)
                {
                    if (main.uhc_player_list[i-1].getDisplayName().equals(player.getDisplayName()))
                    {
                        main.uhc_player_list[i-1] = player;
                        main.uhc_real_player_number ++;
                        main.uhc_real_player_list[main.uhc_join_tracker] = player;
                        main.uhc_join_tracker ++;
                        inGame = true;
                    }
                }
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
        if (!main.uhc_state.equals("waiting"))
        {
            Player player = event.getPlayer();

            for (int i = main.uhc_join_tracker;i > 0;i--)
            {
                if (main.uhc_real_player_list[i-1] != null)
                {
                    if (main.uhc_real_player_list[i-1].getDisplayName().equals(player.getDisplayName()))
                    {
                        main.uhc_real_player_list[i-1] = null;
                        main.uhc_real_player_number --;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event)
    {
        Player player = event.getEntity();
        main.resetPlayer(player);
        if (player.getWorld() == Bukkit.getWorld("world"))
        {
            player.teleport(new Location(Bukkit.getWorld("world"),0, 255, 0));
        }
        else
        {
            player.setGameMode(GameMode.SPECTATOR);
        }

        for (int i = main.uhc_join_tracker; i > 0; i--)
        {
            if (main.uhc_real_player_list[i-1] != null)
            {
                if (main.uhc_real_player_list[i-1].getDisplayName().equals(player.getDisplayName()))
                {
                    for(int j = main.uhc_player_number; j > 0; j--)
                    {
                        if (main.uhc_player_list[j-1] != null)
                        {
                            if (main.uhc_player_list[j-1].getDisplayName().equals(player.getDisplayName()))
                            {
                                main.uhc_player_list[j-1] = null;
                            }
                        }
                    }
                    main.uhc_player_number --;
                    main.uhc_real_player_list[i-1] = null;
                    main.uhc_real_player_number --;
                }
            }
        }

        // DROP GAPPLE ON DEATH
        player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.GOLDEN_APPLE));
    }
}
