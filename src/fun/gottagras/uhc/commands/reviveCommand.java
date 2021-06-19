package fun.gottagras.uhc.commands;

import fun.gottagras.uhc.Main;
import fun.gottagras.uhc.listeners.superHeroes;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class reviveCommand implements CommandExecutor {
    private Main main;
    public reviveCommand(Main main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        boolean isDid = false;
        if (strings.length >= 1)
        {
            for (Player player:Bukkit.getOnlinePlayers())
            {
                if (player.getDisplayName().equals(strings[0]))
                {
                    if (!main.uhc_player_list.contains(player.getUniqueId().toString()))
                    {
                        // AJOUT A LA LISTE
                        main.uhc_player_list.add(player.getUniqueId().toString());
                        main.uhc_real_player_list.add(player.getUniqueId().toString());
                        main.uhc_player_number ++;
                        main.uhc_real_player_number ++;

                        // SUMMON
                        main.resetPlayer(player);
                        main.randomTp(player);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 254), false);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 254), false);

                        // GIVE STUFF
                        switch (main.uhc_format)
                        {
                            case "meetup":
                                new startCommand(main).giveMeetupStuff(player);
                                break;

                            case "gonefishing":
                                new startCommand(main).giveGoneFishingStuff(player);
                                break;

                            default:
                                new startCommand(main).giveUHCStuff(player);
                                break;
                        }

                        if (main.uhc_superHeroes)
                        {
                            new superHeroes(main).giveEffects(player);
                        }

                        // MSG
                        Bukkit.broadcastMessage("§6" +player.getDisplayName()+ "§7 a eu le droit à un revive ... C'est honteux");
                        isDid = true;
                    }
                }
            }
        }

        if (!isDid) commandSender.sendMessage("§6/revive <player>");
        return false;
    }
}
