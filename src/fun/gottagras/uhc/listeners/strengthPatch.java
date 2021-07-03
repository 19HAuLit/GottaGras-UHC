package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class strengthPatch implements Listener {
    private final Main main;
    public strengthPatch(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event)
    {
        Entity damager = event.getDamager();
        if (damager instanceof Player)
        {
            Collection<PotionEffect> effect = ((Player)damager).getActivePotionEffects();
            for (PotionEffect potionEffect : effect)
            {
                PotionEffectType potionType = potionEffect.getType();
                if (potionType.getName().equals("INCREASE_DAMAGE")) event.setDamage(event.getDamage()*0.65);
            }
        }
    }
}
