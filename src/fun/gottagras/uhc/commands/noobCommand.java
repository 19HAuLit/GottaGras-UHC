package fun.gottagras.uhc.commands;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class noobCommand implements CommandExecutor {
    private final Main main;
    public noobCommand(Main main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (strings.length >= 1)
        {
            Player noob = null;
            for (Player player: Bukkit.getOnlinePlayers())
            {
                if (player.getName().equals(strings[0]))
                {
                    noob = player;
                }
            }
            if (noob != null)
            {
                if (main.noob_list.contains(noob.getUniqueId().toString()))
                {
                    main.noob_list.remove(noob.getUniqueId().toString());
                    Bukkit.broadcastMessage("§7[§6-§7] NoobList: §6" + noob.getDisplayName());
                }
                else
                {
                    main.noob_list.add(noob.getUniqueId().toString());
                    Bukkit.broadcastMessage("§7[§6+§7] NoobList: §6" + noob.getDisplayName());
                }
            }
        }
        return false;
    }
}
