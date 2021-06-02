package fun.gottagras.uhc.menu;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class uhcMenu implements Listener
{
    private Main main;
    public uhcMenu(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent event)
    {
        Inventory inventory = event.getInventory();
        if (inventory.getName().equals(menu().getName()))
        {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (player.isOp())
            {
                ItemStack itemStack = event.getCurrentItem();
                if (itemStack != null)
                {
                    if (itemStack.getType() != Material.AIR)
                    {
                        // NAME
                        String itemName = itemStack.getItemMeta().getDisplayName();
                        String formatName = format().getItemMeta().getDisplayName();
                        String cutCleanName = cutClean().getItemMeta().getDisplayName();
                        String allTreeDropName = allTreeDrop().getItemMeta().getDisplayName();
                        String hasteyBoyName = hasteyBoy().getItemMeta().getDisplayName();
                        String stuffLimitName = stuffLimit().getItemMeta().getDisplayName();
                        String superHeroesName = superHeroes().getItemMeta().getDisplayName();
                        String noFallName = noFall().getItemMeta().getDisplayName();
                        String skyHighName = skyHigh().getItemMeta().getDisplayName();
                        String netherName = nether().getItemMeta().getDisplayName();
                        String theEndName = theEnd().getItemMeta().getDisplayName();

                        if (itemName == null) return;

                        // CHANGES
                        if (itemName.equals(formatName))
                        {
                            switch (main.uhc_format) {
                                case "classico":
                                    // FORMAT
                                    main.uhc_format = "meetup";
                                    // TIME
                                    main.uhc_invincible = 30;
                                    main.uhc_pvp = 60;
                                    main.uhc_border = 300;
                                    main.uhc_meetup = 600;
                                    break;
                                case "meetup":
                                    main.uhc_format = "gonefishing";
                                    // TIME
                                    main.uhc_invincible = 30;
                                    main.uhc_pvp = 1200;
                                    main.uhc_border = 1260;
                                    main.uhc_meetup = 1860;
                                    // SCENARIO
                                    main.uhc_stuffLimit = false;
                                    break;
                                case "gonefishing":
                                    // FORMAT
                                    main.uhc_format = "classico";
                                    // TIME
                                    main.uhc_invincible = 30;
                                    main.uhc_pvp = 1200;
                                    main.uhc_border = 3600;
                                    main.uhc_meetup = 4500;
                                    // SCENARIO
                                    main.uhc_stuffLimit = true;
                                    break;
                            }
                        }
                        else if (itemName.equals(cutCleanName))
                        {
                            main.uhc_cutclean = !main.uhc_cutclean;
                        }
                        else if (itemName.equals(allTreeDropName))
                        {
                            main.uhc_alltreedrop = !main.uhc_alltreedrop;
                        }
                        else if (itemName.equals(hasteyBoyName))
                        {
                            main.uhc_hasteyboy = !main.uhc_hasteyboy;
                        }
                        else if (itemName.equals(stuffLimitName))
                        {
                            main.uhc_stuffLimit = !main.uhc_stuffLimit;
                        }
                        else if (itemName.equals(superHeroesName))
                        {
                            main.uhc_superHeroes = !main.uhc_superHeroes;
                        }
                        else if (itemName.equals(noFallName))
                        {
                            main.uhc_nofall = !main.uhc_nofall;
                        }
                        else if (itemName.equals(skyHighName))
                        {
                            main.uhc_skyhigh = !main.uhc_skyhigh;
                        }
                        else if (itemName.equals(netherName))
                        {
                            main.uhc_nether = !main.uhc_nether;
                        }
                        else if (itemName.equals(theEndName))
                        {
                            main.uhc_the_end = !main.uhc_the_end;
                        }
                    }
                }
            }
            player.openInventory(menu());
        }
    }

    public Inventory menu()
    {
        Inventory inventory = Bukkit.createInventory(null, 36, "§7UHC");
        inventory.setItem(0, format());
        inventory.setItem(10, cutClean());
        inventory.setItem(11, allTreeDrop());
        inventory.setItem(12, hasteyBoy());
        inventory.setItem(13, stuffLimit());
        inventory.setItem(14, superHeroes());
        inventory.setItem(15, noFall());
        inventory.setItem(16, skyHigh());
        inventory.setItem(19, nether());
        inventory.setItem(20, theEnd());
        return inventory;
    }

    public ItemStack format()
    {
        ItemStack itemStack = new ItemStack(Material.GRASS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7Mode: §6"+main.uhc_format);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack cutClean()
    {
        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7CutClean: §6" + main.uhc_cutclean);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack allTreeDrop()
    {
        ItemStack itemStack = new ItemStack(Material.SAPLING);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7AllTreeDrop: §6" + main.uhc_alltreedrop);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack hasteyBoy()
    {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7HasteyBoy: §6" + main.uhc_hasteyboy);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack stuffLimit()
    {
        ItemStack itemStack = new ItemStack(Material.ANVIL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7Limite de Stuff: §6" + main.uhc_stuffLimit);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack superHeroes()
    {
        ItemStack itemStack = new ItemStack(Material.POTION);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7SuperHeroes: §6" + main.uhc_superHeroes);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack noFall()
    {
        ItemStack itemStack = new ItemStack(Material.FEATHER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7NoFall: §6" + main.uhc_nofall);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack skyHigh()
    {
        ItemStack itemStack = new ItemStack(Material.DIRT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7SkyHigh: §6" + main.uhc_skyhigh);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack nether()
    {
        ItemStack itemStack = new ItemStack(Material.NETHERRACK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7Nether: §6" + main.uhc_nether);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack theEnd()
    {
        ItemStack itemStack = new ItemStack(Material.ENDER_STONE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§7The End: §6" + main.uhc_the_end);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
