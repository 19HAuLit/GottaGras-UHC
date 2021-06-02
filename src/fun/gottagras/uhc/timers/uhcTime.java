package fun.gottagras.uhc.timers;

import fun.gottagras.uhc.Main;
import fun.gottagras.uhc.listeners.goneFishing;
import fun.gottagras.uhc.listeners.superHeroes;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class uhcTime extends BukkitRunnable implements Runnable
{
    private Main main;
    public uhcTime(Main main)
    {
        this.main = main;
    }

    @Override
    public void run()
    {
        // TIME INVINCIBLE
        if (main.uhc_time == main.uhc_invincible)
        {
            main.uhc_state = "pve";
            Bukkit.broadcastMessage("§7Invincibilité désactivée");
        }

        // TIME PVP
        if (main.uhc_time == main.uhc_pvp)
        {
            main.uhc_state = "pvp";
            Bukkit.broadcastMessage("§7PvP activé");
            for (Player player: main.uhc_real_player_list)
            {
                if (player != null)
                {
                    player.setHealth(20);
                    if (player.getUniqueId().toString().equalsIgnoreCase("d53059f2-7004-4fbf-bb7f-253657a552db")) player.getInventory().addItem(new ItemStack(Material.DIAMOND, 64));
                }
            }
        }

        // TIME BORDER
        if (main.uhc_time == main.uhc_border)
        {
            main.uhc_state = "border";
            Bukkit.broadcastMessage("§7Border en mouvement");

            WorldBorder worldBorder = Bukkit.getWorld("uhc").getWorldBorder();
            WorldBorder worldNetherBorder = Bukkit.getWorld("uhc_nether").getWorldBorder();
            WorldBorder worldTheEndBorder = Bukkit.getWorld("uhc_the_end").getWorldBorder();

            worldBorder.setSize(100, main.uhc_meetup- main.uhc_border);
            worldNetherBorder.setSize(100, main.uhc_meetup- main.uhc_border);
            worldTheEndBorder.setSize(100, main.uhc_meetup- main.uhc_border);
        }

        // TIME MEETUP
        if (main.uhc_time == main.uhc_meetup)
        {
            main.uhc_state = "meetup";
            Bukkit.broadcastMessage("§7Fin du mouvement de la border");
            Bukkit.getWorld("uhc").getWorldBorder().setSize(100);
            for (Player player: main.uhc_real_player_list)
            {
                if (player != null) player.giveExpLevels(667);
            }
        }

        // GONEFISHING
        if (main.uhc_format.equals("gonefishing") || main.uhc_skyhigh)
        {
            new goneFishing(main).skyHigh();
        }

        // SUPERHEROES
        if (main.uhc_superHeroes && main.uhc_invincible == main.uhc_time)
        {

            for (Player player: main.uhc_real_player_list)
            {
                new superHeroes(main).giveEffects(player);
            }
        }

        // CHECK WIN
        if (!main.win && !main.uhc_state.equals("start") && !main.uhc_state.equals("pve")) main.checkWin();

        main.uhc_time ++;
    }
}
