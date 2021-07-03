package fun.gottagras.uhc.commands;

import fun.gottagras.uhc.Main;
import fun.gottagras.uhc.menu.teamMenu;
import fun.gottagras.uhc.menu.uhcMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class teamCommand implements CommandExecutor
{
    private final Main main;
    public teamCommand(Main main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (commandSender instanceof Player)
        {
            Player player = (Player) commandSender;
            if (main.uhc_state.equals("waiting"))
            {
                player.openInventory(new teamMenu(main).menu());
            }
            else player.sendMessage("§6Impossible de changer de team lorsque la game a start");
        }
        else
        {
            commandSender.sendMessage("§6Seul les joueurs peuvent utiliser cette commande !");
        }
        return false;
    }
}
