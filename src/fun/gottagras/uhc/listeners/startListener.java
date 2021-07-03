package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class startListener implements Listener
{
    private final Main main;
    public startListener(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event)
    {
        if (main.uhc_state.equals("start"))
        {
            if (event.getEntity() instanceof Player)
            {
                event.setCancelled(event.getCause() != EntityDamageEvent.DamageCause.VOID);
            }
        }
    }
}
