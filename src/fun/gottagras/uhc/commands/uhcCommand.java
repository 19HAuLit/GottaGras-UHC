package fun.gottagras.uhc.commands;

import fun.gottagras.uhc.Main;
import fun.gottagras.uhc.menu.uhcMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class uhcCommand implements CommandExecutor {
    private Main main;
    public uhcCommand(Main main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (commandSender instanceof Player)
        {
            ((Player) commandSender).openInventory(new uhcMenu(main).menu());
        }
        else
        {
            commandSender.sendMessage("§6Seul les joueurs peuvent utiliser cette commande !");
        }
        return false;
    }
}
