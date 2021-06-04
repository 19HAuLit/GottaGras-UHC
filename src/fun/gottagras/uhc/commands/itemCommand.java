package fun.gottagras.uhc.commands;

import fun.gottagras.uhc.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class itemCommand implements CommandExecutor {
    private Main main;
    public itemCommand(Main main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (commandSender instanceof Player)
        {
            Player player = (Player) commandSender;
            ItemStack itemStack = player.getItemInHand();
            if (itemStack == null) return false;
            commandSender.sendMessage("§7Material: §6" + itemStack.getType());
            commandSender.sendMessage("§7Display Name: §6" + itemStack.getItemMeta().getDisplayName());
            commandSender.sendMessage("§7Item Flags: §6" + itemStack.getItemMeta().getItemFlags());
            commandSender.sendMessage("§7Lore: §6" + itemStack.getItemMeta().getLore());
            commandSender.sendMessage("§7Data: §6" + itemStack.getData());
            commandSender.sendMessage("§7Type ID: §6" + itemStack.getTypeId());
            commandSender.sendMessage("§7Enchantments: §6" + itemStack.getEnchantments());
        }
        else commandSender.sendMessage("§6La console ne peut pas executer cette commande");
        return false;
    }
}
