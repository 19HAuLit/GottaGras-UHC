package fun.gottagras.uhc.listeners;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Map;

public class limitStuffListener implements Listener {
    private Main main;
    public limitStuffListener(Main main)
    {
        this.main = main;
    }

    public void limitEnchant(ItemStack itemStack)
    {
        if (itemStack == null) return;
        ItemMeta itemMeta = itemStack.getItemMeta();
        Material material = itemStack.getType();
        // SWORD
        if (material == Material.DIAMOND_SWORD || material == Material.IRON_SWORD || material == Material.STONE_SWORD || material == Material.GOLD_SWORD || material == Material.WOOD_SWORD)
        {
            if (itemMeta.toString().contains("DAMAGE_ALL="+(main.uhc_stuffLimit_sharpness+1)) || itemMeta.toString().contains("DAMAGE_ALL="+(main.uhc_stuffLimit_sharpness+2)) || itemMeta.toString().contains("DAMAGE_ALL="+(main.uhc_stuffLimit_sharpness+3)) || itemMeta.toString().contains("DAMAGE_ALL="+(main.uhc_stuffLimit_sharpness+4)) || itemMeta.toString().contains("DAMAGE_ALL="+(main.uhc_stuffLimit_sharpness+5)))
            {
                itemMeta.removeEnchant(Enchantment.DAMAGE_ALL);
                itemMeta.addEnchant(Enchantment.DAMAGE_ALL, main.uhc_stuffLimit_sharpness, true);
                itemStack.setItemMeta(itemMeta);
            }
            if (!main.uhc_stuffLimit_fireAspect)
            {
                if (itemMeta.toString().contains("FIRE_ASPECT"))
                {
                    itemMeta.removeEnchant(Enchantment.FIRE_ASPECT);
                    itemStack.setItemMeta(itemMeta);
                }
            }
        }
        // DIAMOND ARMOR
        else if (material == Material.DIAMOND_HELMET || material == Material.DIAMOND_CHESTPLATE || material == Material.DIAMOND_LEGGINGS || material == Material.DIAMOND_BOOTS)
        {
            if (itemMeta.toString().contains("PROTECTION_ENVIRONMENTAL="+(main.uhc_stuffLimit_diamondProtection+1)) || itemMeta.toString().contains("PROTECTION_ENVIRONMENTAL="+(main.uhc_stuffLimit_diamondProtection+2)) || itemMeta.toString().contains("PROTECTION_ENVIRONMENTAL="+(main.uhc_stuffLimit_diamondProtection+3)) || itemMeta.toString().contains("PROTECTION_ENVIRONMENTAL="+(main.uhc_stuffLimit_diamondProtection+4)))
            {
                itemMeta.removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, main.uhc_stuffLimit_diamondProtection, true);
                itemStack.setItemMeta(itemMeta);
            }
        }
        // IRON ARMOR
        else if (material == Material.IRON_HELMET || material == Material.IRON_CHESTPLATE || material == Material.IRON_LEGGINGS || material == Material.IRON_BOOTS)
        {
            if (itemMeta.toString().contains("PROTECTION_ENVIRONMENTAL="+(main.uhc_stuffLimit_ironProtection+1)) || itemMeta.toString().contains("PROTECTION_ENVIRONMENTAL="+(main.uhc_stuffLimit_ironProtection+2)) || itemMeta.toString().contains("PROTECTION_ENVIRONMENTAL="+(main.uhc_stuffLimit_ironProtection+3)) || itemMeta.toString().contains("PROTECTION_ENVIRONMENTAL="+(main.uhc_stuffLimit_ironProtection+4)))
            {
                itemMeta.removeEnchant(Enchantment.PROTECTION_ENVIRONMENTAL);
                itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, main.uhc_stuffLimit_ironProtection, true);
                itemStack.setItemMeta(itemMeta);
            }
        }
        // BOW
        else if (material == Material.BOW)
        {
            if (itemMeta.toString().contains("ARROW_DAMAGE="+(main.uhc_stuffLimit_power+1)) || itemMeta.toString().contains("ARROW_DAMAGE="+(main.uhc_stuffLimit_power+2)) || itemMeta.toString().contains("ARROW_DAMAGE="+(main.uhc_stuffLimit_power+3)) || itemMeta.toString().contains("ARROW_DAMAGE="+(main.uhc_stuffLimit_power+4)) || itemMeta.toString().contains("ARROW_DAMAGE="+(main.uhc_stuffLimit_power+5)))
            {
                itemMeta.removeEnchant(Enchantment.ARROW_DAMAGE);
                itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, main.uhc_stuffLimit_power, true);
                itemStack.setItemMeta(itemMeta);
            }
            if (!main.uhc_stuffLimit_flame)
            {
                if (itemMeta.toString().contains("ARROW_FIRE"))
                {
                    itemMeta.removeEnchant(Enchantment.ARROW_FIRE);
                    itemStack.setItemMeta(itemMeta);
                }
            }
        }
    }

    public boolean notchApple(ItemStack itemStack)
    {
        if (itemStack == null) return false;
        return itemStack.getType() == Material.GOLDEN_APPLE && itemStack.getData().toString().equals("GOLDEN_APPLE(1)") && !main.uhc_stuffLimit_notchApple;
    }

    public boolean potion(ItemStack itemStack)
    {
        if (itemStack == null) return false;

        if (itemStack.getType() == Material.POTION && !main.uhc_stuffLimit_potion) return true;

        // STRENGH
        if (itemStack.getData().toString().equalsIgnoreCase("POTION(73)") || itemStack.getData().toString().equalsIgnoreCase("POTION(41)") || itemStack.getData().toString().equalsIgnoreCase("POTION(9)"))
        {
            return !main.uhc_stuffLimit_potionStrength;
        }

        // POISON
        else if (itemStack.getData().toString().equalsIgnoreCase("POTION(68)") || itemStack.getData().toString().equalsIgnoreCase("POTION(36)") || itemStack.getData().toString().equalsIgnoreCase("POTION(4)"))
        {
            return !main.uhc_stuffLimit_potionPoison;
        }

        // INSTANT DAMAGE
        else if (itemStack.getData().toString().equalsIgnoreCase("POTION(76)") || itemStack.getData().toString().equalsIgnoreCase("POTION(44)"))
        {
            return !main.uhc_stuffLimit_potionInstantDamage;
        }

        // LEVEL II
        else if (itemStack.getData().toString().equalsIgnoreCase("POTION(37)") || itemStack.getData().toString().equalsIgnoreCase("POTION(41)") || itemStack.getData().toString().equalsIgnoreCase("POTION(44)") || itemStack.getData().toString().equalsIgnoreCase("POTION(36)") || itemStack.getData().toString().equalsIgnoreCase("POTION(34)") || itemStack.getData().toString().equalsIgnoreCase("POTION(43)") || itemStack.getData().toString().equalsIgnoreCase("POTION(33)"))
        {
            return !main.uhc_stuffLimit_potionLevelII;
        }

        return false;
    }

    public int diamondArmorLimit(Player player)
    {
        ItemStack helmetSlot = player.getInventory().getHelmet();
        ItemStack chestplateSlot = player.getInventory().getChestplate();
        ItemStack leggingsSlot = player.getInventory().getLeggings();
        ItemStack bootsSlot = player.getInventory().getBoots();
        int diamondArmorNumber = 0;
        if (helmetSlot != null)
        {
            if (helmetSlot.getType() == Material.DIAMOND_HELMET) diamondArmorNumber ++;
        }
        if (chestplateSlot != null)
        {
            if (chestplateSlot.getType() == Material.DIAMOND_CHESTPLATE) diamondArmorNumber ++;
        }
        if (leggingsSlot != null)
        {
            if (leggingsSlot.getType() == Material.DIAMOND_LEGGINGS) diamondArmorNumber ++;
        }
        if (bootsSlot != null)
        {
            if (bootsSlot.getType() == Material.DIAMOND_BOOTS) diamondArmorNumber ++;
        }
        return diamondArmorNumber;
    }

    public int ironArmorLimit(Player player)
    {
        ItemStack helmetSlot = player.getInventory().getHelmet();
        ItemStack chestplateSlot = player.getInventory().getChestplate();
        ItemStack leggingsSlot = player.getInventory().getLeggings();
        ItemStack bootsSlot = player.getInventory().getBoots();
        int ironArmorNumber = 0;
        if (helmetSlot != null)
        {
            if (helmetSlot.getType() == Material.IRON_HELMET) ironArmorNumber ++;
        }
        if (chestplateSlot != null)
        {
            if (chestplateSlot.getType() == Material.IRON_CHESTPLATE) ironArmorNumber ++;
        }
        if (leggingsSlot != null)
        {
            if (leggingsSlot.getType() == Material.IRON_LEGGINGS) ironArmorNumber ++;
        }
        if (bootsSlot != null)
        {
            if (bootsSlot.getType() == Material.IRON_BOOTS) ironArmorNumber ++;
        }
        return ironArmorNumber;
    }

    @EventHandler
    public void onDispense(BlockDispenseEvent event)
    {
        if (!main.uhc_stuffLimit) return;
        Material material = event.getItem().getType();
        event.setCancelled(material == Material.POTION || material == Material.DIAMOND_HELMET || material == Material.DIAMOND_CHESTPLATE || material == Material.DIAMOND_LEGGINGS || material == Material.DIAMOND_BOOTS || material == Material.IRON_HELMET || material == Material.IRON_CHESTPLATE || material == Material.IRON_LEGGINGS || material == Material.IRON_BOOTS);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event)
    {
        if (!main.uhc_stuffLimit) return;
        for (Player player: main.noob_list)
        {
            if (player != null)
            {
                if (player.getDisplayName().equals(event.getPlayer().getDisplayName()))
                {
                    return;
                }
            }
        }
        limitEnchant(event.getItem().getItemStack());
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event)
    {
        if (!main.uhc_stuffLimit) return;
        for (Player player: main.noob_list)
        {
            if (player != null)
            {
                if (player.getDisplayName().equals(event.getPlayer().getDisplayName()))
                {
                    return;
                }
            }
        }
        limitEnchant(event.getItemDrop().getItemStack());
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event)
    {
        if (!main.uhc_stuffLimit) return;
        for (Player player: main.noob_list)
        {
            if (player != null)
            {
                if (player.getDisplayName().equals(event.getPlayer().getDisplayName()))
                {
                    return;
                }
            }
        }
        ItemStack itemStack = event.getItem();
        if (itemStack == null) return;
        limitEnchant(itemStack);
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
        {
            // NOTCH APPLE
            if (itemStack.getType() == Material.GOLDEN_APPLE)event.setCancelled(notchApple(itemStack));

            // POTION
            if (itemStack.getType() == Material.POTION) event.setCancelled(potion(itemStack));

            // ARMOR
            if (itemStack.getType() == Material.DIAMOND_HELMET || itemStack.getType() == Material.DIAMOND_CHESTPLATE || itemStack.getType() == Material.DIAMOND_LEGGINGS || itemStack.getType() == Material.DIAMOND_BOOTS)
            {
                event.setCancelled(diamondArmorLimit(event.getPlayer()) >= main.uhc_stuffLimit_diamondArmor);
                event.getPlayer().updateInventory();
            }
            if (itemStack.getType() == Material.IRON_HELMET || itemStack.getType() == Material.IRON_CHESTPLATE || itemStack.getType() == Material.IRON_LEGGINGS || itemStack.getType() == Material.IRON_BOOTS)
            {
                event.setCancelled(ironArmorLimit(event.getPlayer()) >= main.uhc_stuffLimit_ironArmor);
                event.getPlayer().updateInventory();
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        if (!main.uhc_stuffLimit) return;
        for (Player player: main.noob_list)
        {
            if (player != null)
            {
                if (player.getDisplayName().equals(((Player)event.getWhoClicked()).getDisplayName()))
                {
                    return;
                }
            }
        }
        limitEnchant(event.getCurrentItem());
        ItemStack itemStack = event.getCurrentItem();
        if (itemStack == null) return;
        if (itemStack.getType() == Material.DIAMOND_HELMET || itemStack.getType() == Material.DIAMOND_CHESTPLATE || itemStack.getType() == Material.DIAMOND_LEGGINGS || itemStack.getType() == Material.DIAMOND_BOOTS)
        {
            if (event.getSlotType() == InventoryType.SlotType.ARMOR)
            {
                if (event.getAction() == InventoryAction.HOTBAR_SWAP || event.getAction() == InventoryAction.PLACE_ALL)
                {
                    event.setCancelled(diamondArmorLimit((Player) event.getWhoClicked()) >= main.uhc_stuffLimit_diamondArmor);
                }
            }
            if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY)
            {
                if (event.getSlotType() == InventoryType.SlotType.CONTAINER || event.getSlotType() == InventoryType.SlotType.QUICKBAR)
                {
                    event.setCancelled(diamondArmorLimit((Player) event.getWhoClicked()) >= main.uhc_stuffLimit_diamondArmor);
                }
            }
        }
        if (itemStack.getType() == Material.IRON_HELMET || itemStack.getType() == Material.IRON_CHESTPLATE || itemStack.getType() == Material.IRON_LEGGINGS || itemStack.getType() == Material.IRON_BOOTS)
        {
            if (event.getSlotType() == InventoryType.SlotType.ARMOR)
            {
                if (event.getAction() == InventoryAction.HOTBAR_SWAP || event.getAction() == InventoryAction.PLACE_ALL)
                {
                    event.setCancelled(diamondArmorLimit((Player) event.getWhoClicked()) >= main.uhc_stuffLimit_ironArmor);
                }
            }
            if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY)
            {
                if (event.getSlotType() == InventoryType.SlotType.CONTAINER || event.getSlotType() == InventoryType.SlotType.QUICKBAR)
                {
                    event.setCancelled(diamondArmorLimit((Player) event.getWhoClicked()) >= main.uhc_stuffLimit_ironArmor);
                }
            }
        }
    }
}
