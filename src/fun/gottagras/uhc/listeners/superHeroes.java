package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.Random;

public class superHeroes implements Listener {
    private Main main;
    public superHeroes(Main main)
    {
        this.main = main;
    }

    public void giveEffects(Player player)
    {
        Random random = new Random();
        if (player != null)
        {
            int randomEffectNumber = random.nextInt(5);
            switch (randomEffectNumber)
            {
                // DOUBLE HEAL
                case 0:
                    player.setHealthScale(40);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 20*60*60*24, 4));
                    player.setHealth(40);
                    player.sendMessage("§7Vous venez d'obtenir§6 Double Heal");
                    break;
                // RESISTANCE
                case 1:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*60*60*24, 0));
                    player.sendMessage("§7Vous venez d'obtenir§6 Resistance");
                    break;
                // STRENGTH
                case 2:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20*60*60*24, 0));
                    player.sendMessage("§7Vous venez d'obtenir§6 Force");
                    break;
                // SPEED
                case 3:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*60*60*24, 1));
                    player.sendMessage("§7Vous venez d'obtenir§6 Speed");
                    break;
                // JUMP BOOST
                case 4:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20*60*60*24, 4));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*60*60*24, 0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20*60*60*24, 0));
                    player.sendMessage("§7Vous venez d'obtenir§6 Jump Boost");
                    break;
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event)
    {
        if (!main.uhc_superHeroes) return;
        EntityDamageEvent.DamageCause damageCause = event.getCause();
        Player player;
        if (event.getEntity() instanceof Player) player = (Player) event.getEntity();
        else return;
        if (damageCause == EntityDamageEvent.DamageCause.FALL)
        {
            Collection<PotionEffect> effect = player.getActivePotionEffects();
            for (PotionEffect potionEffect : effect)
            {
                PotionEffectType potionType = potionEffect.getType();
                if (potionType.getName().equals("JUMP")) event.setCancelled(true);
            }
        }
    }
}
