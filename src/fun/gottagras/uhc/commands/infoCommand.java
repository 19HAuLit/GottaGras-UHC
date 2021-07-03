package fun.gottagras.uhc.commands;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class infoCommand implements CommandExecutor {
    private final Main main;
    public infoCommand(Main main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        commandSender.sendMessage("§6PlayerNumber: §7"+main.uhc_player_number);
        commandSender.sendMessage("§6PlayerList: ");
        for (Player player: Bukkit.getOnlinePlayers())
        {
            if (main.uhc_player_list.contains(player.getUniqueId().toString())) commandSender.sendMessage("§7"+player.getDisplayName());
        }
        commandSender.sendMessage("§6Time: §7"+main.uhc_time);
        commandSender.sendMessage("§6Win: §7"+main.win);
        return false;
    }
}
