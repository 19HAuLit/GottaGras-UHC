package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class pvpListener implements Listener {
    private Main main;
    public pvpListener(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event)
    {
        if (!main.uhc_state.equals("pve") && !main.uhc_state.equals("waiting"))
        {
            Entity damager = event.getDamager();
            Entity damaged = event.getEntity();
            if (damaged instanceof Player)
            {
                if (damager instanceof Player)
                {
                    friendlyFire(event, damager, damaged);
                }
                if (damager instanceof Projectile)
                {
                    if (((Projectile) damager).getShooter() instanceof Player)
                    {
                        damager = (Entity) ((Projectile) damager).getShooter();

                        friendlyFire(event, damager, damaged);
                    }
                }
            }
        }
    }

    public void friendlyFire(EntityDamageByEntityEvent event, Entity damager, Entity damaged) {
        if (!main.uhc_team_friendly_fire)
        {
            if (!main.checkOneTeamLeft())
            {
                for (String team : main.uhc_team.keySet())
                {
                    if (main.uhc_team.get(team).contains(damaged.getName()) && main.uhc_team.get(team).contains(damager.getName())) event.setCancelled(true);
                }
            }
        }
    }
}
