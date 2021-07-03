package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class nofall implements Listener
{
    private final Main main;
    public nofall(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event)
    {
        EntityDamageEvent.DamageCause damageCause = event.getCause();
        Player player;
        if (!main.uhc_nofall) return;
        if (event.getEntity() instanceof Player) player = (Player) event.getEntity();
        else return;
        if (damageCause == EntityDamageEvent.DamageCause.FALL)
        {
            event.setCancelled(true);
        }
    }
}
