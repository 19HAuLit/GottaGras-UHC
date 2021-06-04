package fun.gottagras.uhc.commands;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class invseeCommand implements CommandExecutor {
    private Main main;
    public invseeCommand(Main main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (commandSender instanceof Player)
        {
            Player player = (Player) commandSender;
            if (player.getGameMode() == GameMode.SPECTATOR || player.isOp())
            {
                if (strings.length >= 1)
                {
                    for (Player target: Bukkit.getOnlinePlayers())
                    {
                        if (target.getDisplayName().equalsIgnoreCase(strings[0]))
                        {
                            Inventory inventory = target.getInventory();
                            player.openInventory(inventory);
                        }
                    }
                }
                else commandSender.sendMessage("§6/invsee <player>");
            }
        }
        else commandSender.sendMessage("§6La console ne peut pas faire cette commande");
        return false;
    }
}
