package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class cutClean implements Listener {
    private final Main main;
    public cutClean(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void on(PlayerPickupItemEvent event)
    {
        ItemStack itemStack = event.getItem().getItemStack();
        Material material = itemStack.getType();
        if (main.uhc_cutclean)
        {
            if (material == Material.RAW_BEEF) itemStack.setType(Material.COOKED_BEEF);
            else if (material == Material.RAW_CHICKEN) itemStack.setType(Material.COOKED_CHICKEN);
            else if (material == Material.MUTTON) itemStack.setType(Material.COOKED_MUTTON);
            else if (material == Material.RAW_FISH) itemStack.setType(Material.COOKED_FISH);
            else if (material == Material.RABBIT) itemStack.setType(Material.COOKED_RABBIT);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event)
    {
        Block block = event.getBlock();
        Material material = block.getType();
        Location location = block.getLocation();
        Player player = event.getPlayer();
        if (main.uhc_cutclean)
        {
            Location dropLocation = new Location(location.getWorld(), location.getX()+0.5, location.getY()+0.5, location.getZ()+0.5);
            if (material == Material.IRON_ORE)
            {
                event.setCancelled(true);
                block.setType(Material.AIR);
                Bukkit.getWorld(dropLocation.getWorld().getName()).dropItem(dropLocation, new ItemStack(Material.IRON_INGOT));
                player.giveExp(2);
            }
            else if (material == Material.GOLD_ORE)
            {
                event.setCancelled(true);
                block.setType(Material.AIR);
                Bukkit.getWorld(dropLocation.getWorld().getName()).dropItem(dropLocation, new ItemStack(Material.GOLD_INGOT));
                player.giveExp(4);
            }
            else if (material == Material.GRAVEL)
            {
                event.setCancelled(true);
                block.setType(Material.AIR);
                Bukkit.getWorld(dropLocation.getWorld().getName()).dropItem(dropLocation, new ItemStack(Material.FLINT));
            }
        }
    }
}
