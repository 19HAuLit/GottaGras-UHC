package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class hasteyBoy implements Listener {
    private final Main main;
    public hasteyBoy(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        if (main.uhc_hasteyboy)
        {
            ItemStack itemStack = event.getItem();
            if (itemStack == null) return;
            if (itemStack.getType() == Material.WOOD_AXE || itemStack.getType() == Material.STONE_AXE || itemStack.getType() == Material.GOLD_AXE || itemStack.getType() == Material.IRON_AXE || itemStack.getType() == Material.DIAMOND_AXE || itemStack.getType() == Material.WOOD_PICKAXE || itemStack.getType() == Material.STONE_PICKAXE || itemStack.getType() == Material.GOLD_PICKAXE || itemStack.getType() == Material.IRON_PICKAXE || itemStack.getType() == Material.DIAMOND_PICKAXE || itemStack.getType() == Material.WOOD_SPADE || itemStack.getType() == Material.STONE_SPADE || itemStack.getType() == Material.GOLD_SPADE || itemStack.getType() == Material.IRON_SPADE || itemStack.getType() == Material.DIAMOND_SPADE)
            {
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.addEnchant(Enchantment.DIG_SPEED, 5, true);
                itemMeta.spigot().setUnbreakable(true);
                itemStack.setItemMeta(itemMeta);
            }
        }
    }
}
