package fun.gottagras.uhc.commands;

import fun.gottagras.uhc.Main;
import fun.gottagras.uhc.timers.uhcTime;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class startCommand implements CommandExecutor {
    private Main main;
    public startCommand(Main main)
    {
        this.main = main;
    }

    public void giveUHCStuff(Player player)
    {
        ItemStack axe = new ItemStack(Material.STONE_AXE);
        ItemStack pickaxe = new ItemStack(Material.STONE_PICKAXE);
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 64);
        ItemStack book = new ItemStack(Material.BOOK, 4);
        player.getInventory().addItem(axe, pickaxe, steak, book);
    }

    public void giveMeetupStuff(Player player)
    {
        // SWORD
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
        swordMeta.spigot().setUnbreakable(true);
        sword.setItemMeta(swordMeta);

        // BOW
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        bowMeta.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
        bowMeta.spigot().setUnbreakable(true);
        bow.setItemMeta(bowMeta);

        // ROD
        ItemStack rod = new ItemStack(Material.FISHING_ROD);
        ItemMeta rodMeta = rod.getItemMeta();
        rodMeta.spigot().setUnbreakable(true);
        rod.setItemMeta(rodMeta);

        // ARMOR
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta helmetMeta = helmet.getItemMeta();
        helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        helmetMeta.spigot().setUnbreakable(true);
        helmet.setItemMeta(helmetMeta);

        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta chestplateMeta = chestplate.getItemMeta();
        chestplateMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        chestplateMeta.spigot().setUnbreakable(true);
        chestplate.setItemMeta(chestplateMeta);

        ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta leggingsMeta = leggings.getItemMeta();
        leggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        leggingsMeta.spigot().setUnbreakable(true);
        leggings.setItemMeta(leggingsMeta);

        ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        ItemMeta bootsMeta = boots.getItemMeta();
        bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        bootsMeta.spigot().setUnbreakable(true);
        boots.setItemMeta(bootsMeta);

        // STUFF
        ItemStack wood = new ItemStack(Material.WOOD, 64);
        ItemStack water = new ItemStack(Material.WATER_BUCKET, 1);
        ItemStack axe = new ItemStack(Material.DIAMOND_AXE, 1);
        ItemStack food = new ItemStack(Material.COOKED_BEEF, 64);
        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE, 16);
        ItemStack arrow = new ItemStack(Material.ARROW, 1);

        // GIVE STUFF
        player.getInventory().addItem(sword, bow, wood, wood, water, axe, rod, food, gapple, arrow, wood, wood, water, water);
        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);
    }

    public void giveGoneFishingStuff(Player player)
    {
        // NORMAL STUFF
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack bow = new ItemStack(Material.BOW);
        ItemStack dirt = new ItemStack(Material.DIRT, 3);
        ItemStack slime = new ItemStack(Material.SLIME_BLOCK, 64);
        ItemStack water = new ItemStack(Material.WATER_BUCKET);
        ItemStack shovel = new ItemStack(Material.DIAMOND_SPADE);
        ItemStack food = new ItemStack(Material.COOKED_BEEF, 64);
        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE, 16);
        ItemStack wood = new ItemStack(Material.WOOD, 64);
        ItemStack arrow = new ItemStack(Material.ARROW, 1);
        ItemStack anvil = new ItemStack(Material.ANVIL, 64);

        // OP ROD
        ItemStack rod = new ItemStack(Material.FISHING_ROD);
        ItemMeta itemMeta = rod.getItemMeta();
        //itemMeta.spigot().setUnbreakable(true);
        itemMeta.addEnchant(Enchantment.LURE, 3, true);
        itemMeta.addEnchant(Enchantment.LUCK, 255, true);
        rod.setItemMeta(itemMeta);

        // ARMOR
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack leggins = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);

        // GIVE STUFF
        player.getInventory().addItem(sword, bow, dirt, slime, water, shovel, rod, food, gapple, wood, arrow, anvil, water, water, slime);
        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggins);
        player.getInventory().setBoots(boots);
        player.giveExpLevels(9999);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        // UHC CREATED
        if (!main.uhc_created) return false;

        // ADD PLAYERS TO THE GAME
        for (Player player: Bukkit.getOnlinePlayers())
        {
            main.uhc_player_list[main.uhc_player_number] = player;
            main.uhc_player_number ++;
            main.uhc_real_player_list[main.uhc_real_player_number] = player;
            main.uhc_real_player_number ++;
            main.uhc_join_tracker ++;
        }

        // SET WORLD BORDER
        WorldBorder worldBorder = Bukkit.getWorld("uhc").getWorldBorder();
        WorldBorder worldNetherBorder = Bukkit.getWorld("uhc_nether").getWorldBorder();
        WorldBorder worldTheEndBorder = Bukkit.getWorld("uhc_the_end").getWorldBorder();

        // SET TIME & WEATHER
        Bukkit.getWorld("uhc").setTime(0);
        Bukkit.getWorld("uhc_nether").setTime(0);
        Bukkit.getWorld("uhc_the_end").setTime(0);

        switch (main.uhc_format)
        {
            case "meetup":
                worldBorder.setCenter(0, 0);
                worldBorder.setSize(300);
                worldBorder.setDamageAmount(1);
                worldBorder.setDamageBuffer(1);
                worldBorder.setWarningDistance(1);
                worldBorder.setWarningTime(1);

                worldNetherBorder.setCenter(0, 0);
                worldNetherBorder.setSize(75);
                worldNetherBorder.setDamageAmount(1);
                worldNetherBorder.setDamageBuffer(1);
                worldNetherBorder.setWarningDistance(1);
                worldNetherBorder.setWarningTime(1);

                worldTheEndBorder.setCenter(0, 0);
                worldTheEndBorder.setSize(300);
                worldTheEndBorder.setDamageAmount(1);
                worldTheEndBorder.setDamageBuffer(1);
                worldTheEndBorder.setWarningDistance(1);
                worldTheEndBorder.setWarningTime(1);
                break;

            case "gonefishing":
                worldBorder.setCenter(0, 0);
                worldBorder.setSize(800);
                worldBorder.setDamageAmount(1);
                worldBorder.setDamageBuffer(1);
                worldBorder.setWarningDistance(1);
                worldBorder.setWarningTime(1);

                worldNetherBorder.setCenter(0, 0);
                worldNetherBorder.setSize(200);
                worldNetherBorder.setDamageAmount(1);
                worldNetherBorder.setDamageBuffer(1);
                worldNetherBorder.setWarningDistance(1);
                worldNetherBorder.setWarningTime(1);

                worldTheEndBorder.setCenter(0, 0);
                worldTheEndBorder.setSize(800);
                worldTheEndBorder.setDamageAmount(1);
                worldTheEndBorder.setDamageBuffer(1);
                worldTheEndBorder.setWarningDistance(1);
                worldTheEndBorder.setWarningTime(1);
                break;

            default:
                worldBorder.setCenter(0, 0);
                worldBorder.setSize(200* main.uhc_player_number);
                worldBorder.setDamageAmount(1);
                worldBorder.setDamageBuffer(1);
                worldBorder.setWarningDistance(1);
                worldBorder.setWarningTime(1);

                worldNetherBorder.setCenter(0, 0);
                worldNetherBorder.setSize(50* main.uhc_player_number);
                worldNetherBorder.setDamageAmount(1);
                worldNetherBorder.setDamageBuffer(1);
                worldNetherBorder.setWarningDistance(1);
                worldNetherBorder.setWarningTime(1);

                worldTheEndBorder.setCenter(0, 0);
                worldTheEndBorder.setSize(200* main.uhc_player_number);
                worldTheEndBorder.setDamageAmount(1);
                worldTheEndBorder.setDamageBuffer(1);
                worldTheEndBorder.setWarningDistance(1);
                worldTheEndBorder.setWarningTime(1);
                break;
        }

        // TP PLAYER + GIVE STUFF
        for (Player player: main.uhc_player_list)
        {
            if (player != null)
            {
                main.randomTp(player);
                switch (main.uhc_format)
                {
                    case "meetup":
                        giveMeetupStuff(player);
                        break;

                    case "gonefishing":
                        giveGoneFishingStuff(player);
                        break;

                    default:
                        giveUHCStuff(player);
                        break;
                }
            }
        }

        // START TIMER
        Bukkit.getScheduler().runTaskTimer(main, new uhcTime(main),0,20);

        // UHC STATE
        main.uhc_state = "start";
        return false;
    }
}
