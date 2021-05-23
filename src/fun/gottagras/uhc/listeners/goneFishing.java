package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class goneFishing implements Listener
{
    private Main main;
    public goneFishing(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        if (main.uhc_format.equals("gonefishing"))
        {
            Material material = event.getMaterial();
            Player player = event.getPlayer();
            if (material == Material.DIRT)
            {
                player.getInventory().setItemInHand(new ItemStack(Material.DIRT, 3));
            }
        }
    }

    public void skyHigh()
    {
        if (main.uhc_state.equals("pvp") && main.uhc_time%60 == 0)
        {
            for (Player player:main.uhc_real_player_list)
            {
                if (player != null)
                {
                    if (player.getLocation().getY() < 200)
                    {
                        player.setHealth(player.getHealth()-4);
                        player.sendMessage("§6Vous devez être au dessus de la couche 200 pour ne plus prendre de dégats");
                    }
                }
            }
        }
    }
}
