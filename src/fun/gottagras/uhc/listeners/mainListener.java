package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Team;

import java.util.Set;

public class mainListener implements Listener {
    private final Main main;
    public mainListener(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        event.setJoinMessage("§7[§6+§7] §6" + event.getPlayer().getDisplayName());
        Player player = event.getPlayer();
        if (!main.uhc_state.equals("waiting"))
        {
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
        main.playerAddColorTeam(player);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event)
    {
        event.setCancelled(true);
        if (main.uhc_team_size == 1) Bukkit.broadcastMessage(event.getPlayer().getDisplayName().replaceAll(" ", "") + "§r: " + event.getMessage().replaceAll("&", "§"));
        else if (event.getMessage().startsWith("!")) Bukkit.broadcastMessage(event.getPlayer().getDisplayName().replaceAll(" ", "") + "§r: " + event.getMessage().replaceAll("&", "§").replaceFirst("!", ""));
        else
        {
            for (int i = 0; i < main.symbolList.size(); i++)
            {
                for (int j = 0; j < main.colorList.size(); j++)
                {
                    String key = main.colorList.get(j) + main.symbolList.get(i) + main.nameList.get(j);
                    if (main.uhc_team.get(key).contains(event.getPlayer().getName()))
                    {
                        for (Player player : Bukkit.getOnlinePlayers())
                        {
                            if (main.uhc_team.get(key).contains(player.getName()))
                            {
                                player.sendMessage(event.getPlayer().getDisplayName().replaceAll(" ", "") + " [Team]§r: " + event.getMessage().replaceAll("&", "§"));
                            }
                        }
                    }
                }
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
