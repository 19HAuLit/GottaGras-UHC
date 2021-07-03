package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class waitingListener implements Listener {
    private final Main main;
    public waitingListener(Main main)
    {
        this.main = main;
    }

    public ItemStack scenarios()
    {
        ItemStack itemStack = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7Scenarios");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack teams()
    {
        ItemStack itemStack = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§6Teams");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        if (main.uhc_state.equals("waiting"))
        {
            Player player = event.getPlayer();
            Location spawn = new Location(Bukkit.getWorld("world"), 0, 255, 0);
            player.teleport(spawn);
            main.resetPlayer(player);
            player.getInventory().addItem(scenarios(), teams());
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        if (main.uhc_state.equals("waiting"))
        {
            Player player = event.getPlayer();
            event.setCancelled(player.getGameMode() != GameMode.CREATIVE);
            if(event.getItem() != null)
            {
                if (event.getItem().getType() != Material.AIR)
                {
                    String itemName = event.getItem().getItemMeta().getDisplayName();
                    if (itemName.equals(scenarios().getItemMeta().getDisplayName()))
                    {
                        player.performCommand("uhc");
                    }
                    else if (itemName.equals(teams().getItemMeta().getDisplayName()))
                    {
                        player.performCommand("team");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event)
    {
        if (main.uhc_state.equals("waiting"))
        {
            event.setCancelled(event.getCause() != EntityDamageEvent.DamageCause.VOID);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event)
    {
        if (main.uhc_state.equals("waiting"))
        {
            Player player = event.getPlayer();
            player.setFoodLevel(20);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event)
    {
        if (main.uhc_state.equals("waiting"))
        {
            Player player = event.getPlayer();
            if (player.getGameMode() != GameMode.CREATIVE) event.setCancelled(true);
        }
    }
}
