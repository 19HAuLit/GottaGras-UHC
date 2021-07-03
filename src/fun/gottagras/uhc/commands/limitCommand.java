package fun.gottagras.uhc.commands;

import fun.gottagras.uhc.Main;
import fun.gottagras.uhc.menu.limitStuffMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class limitCommand implements CommandExecutor {
    private final Main main;
    public limitCommand(Main main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (commandSender instanceof Player)
        {
            ((Player) commandSender).openInventory(new limitStuffMenu(main).menu());
        }
        else
        {
            commandSender.sendMessage("§6Seul les joueurs peuvent utiliser cette commande !");
        }
        return false;
    }
}
