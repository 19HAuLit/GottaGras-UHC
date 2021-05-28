package fun.gottagras.uhc.commands;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class noobCommand implements CommandExecutor {
    private Main main;
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
            boolean isIn = false;
            for (Player player: Bukkit.getOnlinePlayers())
            {
                if (player.getDisplayName().equals(strings[0]))
                {
                    noob = player;
                }
            }
            int i = 0;
            for (Player player: main.noob_list)
            {
                if (player != null)
                {
                    if (player.getDisplayName().equals(strings[0]))
                    {
                        isIn = true;
                        main.noob_list[i] = null;
                        commandSender.sendMessage("§6" + strings[0] + " a été retiré de la NoobList");
                    }
                }
                i++;
            }
            if (!isIn)
            {
                main.noob_list[main.noob_tracker] = noob;
                main.noob_tracker++;
                commandSender.sendMessage("§6" + strings[0] + " a été ajouté de la NoobList");
            }
        }
        return false;
    }
}
