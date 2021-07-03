package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class allTreeDrop implements Listener {
    private final Main main;
    public allTreeDrop(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onLeaves(LeavesDecayEvent event)
    {
        Location location = event.getBlock().getLocation();
        if (main.uhc_alltreedrop)
        {
            Location dropLocation = new Location(location.getWorld(), location.getX()+0.5, location.getY()+0.5, location.getZ()+0.5);
            Random random = new Random();
            if (random.nextDouble() < 0.05) Bukkit.getWorld(dropLocation.getWorld().getName()).dropItem(location, new ItemStack(Material.APPLE));
        }
    }
}
