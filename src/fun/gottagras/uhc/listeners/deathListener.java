package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Date;

public class deathListener implements Listener {
    private final Main main;
    public deathListener(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event)
    {
        if (event.getEntity() instanceof Player)
        {
            Player player = (Player) event.getEntity();
            Date date = new Date();

            if (event.getDamager() instanceof Player)
            {
                Player damager = (Player) event.getDamager();
                main.last_damager.put(player.getDisplayName(), damager.getDisplayName());
                main.last_damager_time.put(player.getDisplayName(), date.getTime());
            }
            else if (event.getDamager() instanceof Projectile)
            {
                if (((Projectile) event.getDamager()).getShooter() instanceof Player)
                {
                    Player damager = (Player) ((Projectile) event.getDamager()).getShooter();
                    main.last_damager.put(player.getDisplayName(), damager.getDisplayName());
                    main.last_damager_time.put(player.getDisplayName(), date.getTime());
                }
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event)
    {
        Player player = event.getEntity();

        // RESET PLAYER
        main.resetPlayer(player);

        // SPAWN OR SPEC
        if (player.getWorld() == Bukkit.getWorld("world"))
        {
            player.teleport(new Location(Bukkit.getWorld("world"),0, 255, 0));
        }
        else
        {
            player.setGameMode(GameMode.SPECTATOR);
        }

        // REMOVE PLAYER
        if (main.uhc_player_list.contains(player.getUniqueId().toString()))
        {
            main.uhc_player_list.remove(player.getUniqueId().toString());
            main.uhc_real_player_list.remove(player.getUniqueId().toString());
            main.uhc_player_number --;
            main.uhc_real_player_number --;
        }

        // MSG de mort
        Date date = new Date();
        Long time = date.getTime();

        String player_name = player.getDisplayName();

        String name_damager = main.last_damager.get(player_name);

        if (main.last_damager.containsKey(player_name))
        {
            long delta_time = time - main.last_damager_time.get(player_name);
            if (delta_time < 30000)
            {
                int kill = 1;
                if (main.player_kill.containsKey(name_damager))
                {
                    kill += main.player_kill.get(name_damager);
                }
                main.player_kill.put(name_damager, kill);
                event.setDeathMessage("§6" + name_damager + " §7vient de kill §9" + player_name + " §8(" + main.player_kill.get(name_damager) + ")");
            }
            else
            {
                pveDeathMsg(event, player);
            }
        }
        else
        {
            pveDeathMsg(event, player);
        }

        System.out.println(main.player_kill.toString());

        // DROP GAPPLE ON DEATH
        player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.GOLDEN_APPLE));
    }

    public void pveDeathMsg(PlayerDeathEvent event, Player player) {
        int kill = 1;
        if (main.player_kill.containsKey("PvE"))
        {
            kill += main.player_kill.get("PvE");
        }
        main.player_kill.put("PvE", kill);
        event.setDeathMessage("§7Le §2PvE §7vient de kill §9" + player.getDisplayName() + " §8(" + main.player_kill.get("PvE") + ")");
    }

}
