package fun.gottagras.uhc.commands;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class broadcastCommand implements CommandExecutor
{
    private Main main;
    public broadcastCommand(Main main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        StringBuilder msg = new StringBuilder();
        for (String text : strings)
        {
            msg.append(text + " ");
        }
        Bukkit.broadcastMessage("§4" + msg.toString());
        return false;
    }
}
