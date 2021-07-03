package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class pveListener implements Listener {
    private final Main main;
    public pveListener(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event)
    {
        if (main.uhc_state.equals("pve"))
        {
            Entity damager = event.getDamager();
            Entity damaged = event.getEntity();
            if (damaged instanceof Player)
            {
                if (damager instanceof Player)
                {
                    event.setCancelled(true);
                }
                if (damager instanceof Projectile)
                {
                    if (((Projectile) damager).getShooter() instanceof Player)
                    {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
