package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class the_end implements Listener {
    private final Main main;
    public the_end(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onPortal(PlayerPortalEvent event)
    {
        Player player = event.getPlayer();
        String portalTypeName = player.getLocation().getBlock().getType().toString();
        if (main.uhc_the_end && portalTypeName.equalsIgnoreCase("ENDER_PORTAL"))
        {
            if (player.getLocation().getWorld() == Bukkit.getWorld("uhc_the_end"))
            {
                Location location = new Location(Bukkit.getWorld("uhc"), 0, Bukkit.getWorld("world").getHighestBlockYAt(0, 0), 0);
                player.teleport(location);
            }
            else if (player.getLocation().getWorld() == Bukkit.getWorld("uhc"))
            {
                Location location = new Location(Bukkit.getWorld("uhc_the_end"), 0, Bukkit.getWorld("uhc_the_end").getHighestBlockYAt(0, 0), 0);
                player.teleport(location);
            }
        }
    }
}
