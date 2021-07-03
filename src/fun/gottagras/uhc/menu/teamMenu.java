package fun.gottagras.uhc.menu;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.ArrayList;
import java.util.List;

public class teamMenu implements Listener {
    private final Main main;
    public teamMenu(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        Inventory inventory = event.getInventory();
        if (inventory.getName().equals(menu().getName()))
        {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack itemStack = event.getCurrentItem();
            if (itemStack != null)
            {
                if (itemStack.getType() != Material.AIR)
                {
                    // NAME
                    String itemName = itemStack.getItemMeta().getDisplayName();

                    for (int i = 0; i < main.symbolList.size(); i++)
                    {
                        for (int j = 0; j < main.colorList.size(); j++)
                        {
                            String key = main.colorList.get(j) + main.symbolList.get(i) + main.nameList.get(j);
                            ItemStack itemStack1 = teamSelector(main.nameList.get(j), main.colorList.get(j), main.symbolList.get(i));
                            if (main.uhc_team.get(key).contains(player.getName()))
                            {
                                main.uhc_team.get(key).remove(player.getName());
                                player.setDisplayName(player.getName());
                                player.setPlayerListName(player.getName());
                            }
                            if (itemName.equals(itemStack1.getItemMeta().getDisplayName()) && main.uhc_team.get(key).size() < main.uhc_team_size)
                            {
                                main.uhc_team.get(key).add(player.getName());
                                player.setDisplayName(main.colorList.get(j) + main.symbolList.get(i) + " " + player.getName());
                                player.setPlayerListName(main.colorList.get(j) + main.symbolList.get(i) + " " + player.getName());
                            }
                        }
                    }
                }
                player.openInventory(menu());
            }
        }
    }

    public Inventory menu()
    {
        Inventory inventory = Bukkit.createInventory(null, 54, "§7Teams");
        for (int i = 0; i < main.symbolList.size(); i++)
        {
            for (int j = 0; j < main.colorList.size(); j++)
            {
                inventory.setItem(9*i+j*2,teamSelector(main.nameList.get(j), main.colorList.get(j), main.symbolList.get(i)));
            }
        }

        return inventory;
    }

    public ItemStack teamSelector(String name, String color, String symbol)
    {
        ItemStack itemStack = new ItemStack(Material.BANNER);
        BannerMeta bannerMeta = (BannerMeta) itemStack.getItemMeta();

        bannerMeta.setDisplayName(color + symbol + " " + name);

        DyeColor dyeColor;

        switch (name)
        {
            case "Bleu":
                dyeColor = DyeColor.BLUE;
                break;
            case "Rouge":
                dyeColor = DyeColor.RED;
                break;
            case "Vert":
                dyeColor = DyeColor.GREEN;
                break;
            case "Jaune":
                dyeColor = DyeColor.YELLOW;
                break;
            case "Rose":
                dyeColor = DyeColor.PINK;
                break;
            default:
                dyeColor = DyeColor.BLACK;
        }

        bannerMeta.setBaseColor(dyeColor);

        List<Pattern> patterns = new ArrayList<Pattern>();
        switch (symbol)
        {
            case "♥":
                patterns.add(new Pattern(DyeColor.WHITE, PatternType.CIRCLE_MIDDLE));
                patterns.add(new Pattern(dyeColor, PatternType.TRIANGLE_TOP));
                break;
            case "♦":
                patterns.add(new Pattern(DyeColor.WHITE, PatternType.RHOMBUS_MIDDLE));
                break;
            case "▲":
                patterns.add(new Pattern(DyeColor.WHITE, PatternType.RHOMBUS_MIDDLE));
                patterns.add(new Pattern(dyeColor, PatternType.STRIPE_BOTTOM));
                break;
            case "☼":
                patterns.add(new Pattern(DyeColor.WHITE, PatternType.FLOWER));
                break;
            case "•":
                patterns.add(new Pattern(DyeColor.WHITE, PatternType.CIRCLE_MIDDLE));
                break;
        }
        bannerMeta.setPatterns(patterns);

        bannerMeta.setLore(main.uhc_team.get(color+symbol+name));

        itemStack.setItemMeta(bannerMeta);
        return itemStack;
    }
}
