package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class nether implements Listener
{
    private final Main main;
    public nether(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onPortal(PlayerPortalEvent event)
    {
        Player player = event.getPlayer();
        String portalTypeName = player.getLocation().getBlock().getType().toString();
        if (main.uhc_nether && portalTypeName.equalsIgnoreCase("PORTAL"))
        {
            if (player.getLocation().getWorld() == Bukkit.getWorld("uhc_nether"))
            {
                Location portalFindLoc = new Location(Bukkit.getWorld("uhc"), player.getLocation().getX()*8 , player.getLocation().getY(), player.getLocation().getZ()*8);
                event.useTravelAgent(true);
                event.getPortalTravelAgent().setCanCreatePortal(true);
                Location portalLoc = event.getPortalTravelAgent().findOrCreate(portalFindLoc);
                Location playerTpLoc = new Location(Bukkit.getWorld("uhc"), portalLoc.getX()+0.5, portalLoc.getY(), portalLoc.getZ()+0.5);
                player.teleport(playerTpLoc);
            }
            else if (player.getLocation().getWorld() == Bukkit.getWorld("uhc"))
            {
                Location portalFindLoc = new Location(Bukkit.getWorld("uhc_nether"), player.getLocation().getX()/8 , player.getLocation().getY(), player.getLocation().getZ()/8);
                event.useTravelAgent(true);
                event.getPortalTravelAgent().setCanCreatePortal(true);
                Location portalLoc = event.getPortalTravelAgent().findOrCreate(portalFindLoc);
                Location playerTpLoc = new Location(Bukkit.getWorld("uhc_nether"), portalLoc.getX()+0.5, portalLoc.getY(), portalLoc.getZ()+0.5);
                player.teleport(playerTpLoc);
            }
        }
    }
}
