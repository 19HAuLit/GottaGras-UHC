package fun.gottagras.uhc;

import fun.gottagras.uhc.commands.*;
import fun.gottagras.uhc.listeners.*;
import fun.gottagras.uhc.menu.limitStuffMenu;
import fun.gottagras.uhc.menu.uhcMenu;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Random;

public class Main extends JavaPlugin
{
    public static Main INSTANCE;
    @Override
    public void onEnable()
    {
        // BUNGEE CORD
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        INSTANCE = this;

        // PATCH FORCE
        getServer().getPluginManager().registerEvents(new strengthPatch(this), this);

        // LISTERNERS
        getServer().getPluginManager().registerEvents(new nofall(this), this);
        getServer().getPluginManager().registerEvents(new superHeroes(this), this);
        getServer().getPluginManager().registerEvents(new limitStuffMenu(this), this);
        getServer().getPluginManager().registerEvents(new limitStuffListener(this), this);
        getServer().getPluginManager().registerEvents(new goneFishing(this), this);
        getServer().getPluginManager().registerEvents(new hasteyBoy(this), this);
        getServer().getPluginManager().registerEvents(new uhcMenu(this), this);
        getServer().getPluginManager().registerEvents(new allTreeDrop(this), this);
        getServer().getPluginManager().registerEvents(new cutClean(this), this);
        getServer().getPluginManager().registerEvents(new waitingListener(this),this);
        getServer().getPluginManager().registerEvents(new startListener(this), this);
        getServer().getPluginManager().registerEvents(new pveListener(this), this);
        getServer().getPluginManager().registerEvents(new mainListener(this), this);

        // COMMANDS
        getCommand("new").setExecutor(new newCommand(this));
        getCommand("start").setExecutor(new startCommand(this));
        getCommand("info").setExecutor(new infoCommand(this));
        getCommand("uhc").setExecutor(new uhcCommand(this));
        getCommand("revive").setExecutor(new reviveCommand(this));
        getCommand("force").setExecutor(new forceCommand(this));
        getCommand("limit").setExecutor(new limitCommand(this));
        getCommand("noob").setExecutor(new noobCommand(this));

        // SCOREBOARD
        Bukkit.getScheduler().runTaskTimer(this, new scoreBoard(this),0,20);

        plateForm();

        // TP ALL 0 0
        for (Player player : Bukkit.getOnlinePlayers())
        {
            Location spawn = new Location(Bukkit.getWorld("world"), 0, 255, 0);
            player.teleport(spawn);
            resetPlayer(player);
        }
    }

    // UHC STATE
    public String uhc_state = "waiting";
    public boolean uhc_created = false;
    public int uhc_time = 0;
    public boolean win = false;

    // UHC SCENARIO
    public String uhc_format = "classico";
    public boolean uhc_cutclean = true;
    public boolean uhc_alltreedrop = true;
    public boolean uhc_hasteyboy = true;
    public boolean uhc_stuffLimit = true;
    public boolean uhc_superHeroes = false;
    public boolean uhc_nofall = false;
    public boolean uhc_skyhigh = false;

    // UHC TIME
    public int uhc_invincible = 30;
    public int uhc_pvp = 1200;
    public int uhc_border = 3600;
    public int uhc_meetup = 5400;

    // UHC PLAYERS
    public int uhc_player_number = 0;
    public Player[] uhc_player_list = new Player[1024];
    public int uhc_real_player_number = 0;
    public Player[] uhc_real_player_list = new Player[1024];
    public int uhc_join_tracker = 0;
    public Player[] noob_list = new Player[1024];
    public int noob_tracker = 0;

    // LIMIT DE STUFF
    public int uhc_stuffLimit_diamondArmor = 2;
    public int uhc_stuffLimit_diamondProtection = 2;

    public int uhc_stuffLimit_ironArmor = 4;
    public int uhc_stuffLimit_ironProtection = 3;

    public int uhc_stuffLimit_sharpness = 3;
    public boolean uhc_stuffLimit_fireAspect = false;
    public int uhc_stuffLimit_power = 3;
    public boolean uhc_stuffLimit_flame = false;

    public boolean uhc_stuffLimit_potion = true;
    public boolean uhc_stuffLimit_potionStrength = false;
    public boolean uhc_stuffLimit_potionPoison = false;
    public int uhc_stuffLimit_potionLevel = 1;

    public boolean uhc_stuffLimit_notchApple = false;

    // METHODS
    public void plateForm()
    {
        // PLATFORM 0 0
        int x = -15;
        int y = 250;
        int z = -15;
        for (int i = x; i < 15; i++)
        {
            for (int j = z; j < 15; j++)
            {
                Bukkit.getWorld("world").getBlockAt(i, y, j).setType(Material.BARRIER);
            }
        }

        for (int i = y; i < 256; i++)
        {
            for (int j = x; j < 16; j++)
            {
                Bukkit.getWorld("world").getBlockAt(j, i, z).setType(Material.GLASS);
            }
            for (int j = z; j < 16; j++)
            {
                Bukkit.getWorld("world").getBlockAt(x, i, j).setType(Material.GLASS);
            }
            for (int j = x; j < 16; j++)
            {
                Bukkit.getWorld("world").getBlockAt((-1)*j, i, (-1)*z).setType(Material.GLASS);
            }
            for (int j = z; j < 16; j++)
            {
                Bukkit.getWorld("world").getBlockAt((-1)*x, i, (-1)*j).setType(Material.GLASS);
            }
        }
    }

    public void resetPlayer(Player player)
    {
        // INVENTORY
        player.getInventory().clear();
        player.getInventory().setHelmet(new ItemStack(Material.AIR));
        player.getInventory().setChestplate(new ItemStack(Material.AIR));
        player.getInventory().setLeggings(new ItemStack(Material.AIR));
        player.getInventory().setBoots(new ItemStack(Material.AIR));

        // POTION
        Collection<PotionEffect> effect = player.getActivePotionEffects();
        for (PotionEffect potionEffect : effect)
        {
            PotionEffectType potionType = potionEffect.getType();
            player.removePotionEffect(potionType);
        }

        // EXP
        player.setExp(0);
        player.setLevel(0);

        // FOOD & HEAL
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setHealthScale(20);
        player.setHealth(20);

        // GAMEMODE
        player.setGameMode(GameMode.SURVIVAL);
        player.updateInventory();
    }

    public void checkWin()
    {
        if (uhc_real_player_number == 1)
        {
            for (Player player:uhc_real_player_list)
            {
                if (player != null)
                {
                    Bukkit.broadcastMessage("§6" +player.getDisplayName()+ "§7 a win, GG!");
                    win = true;
                }
            }
        }
    }

    public void randomTp(Player player)
    {
        Random random = new Random();
        int map_size = (int) Bukkit.getWorld("uhc").getWorldBorder().getSize();
        int x = map_size/2-random.nextInt(map_size);
        int z = map_size/2-random.nextInt(map_size);
        Location location = new Location(Bukkit.getWorld("uhc"), x, 255, z);
        player.teleport(location);
    }

    public void fileDelete(File file)
    {
        File[] contents = file.listFiles();
        if (contents != null)
        {
            for (File f : contents)
            {
                if (! Files.isSymbolicLink(f.toPath()))
                {
                    fileDelete(f);
                }
            }
        }
        boolean success = file.delete();
    }
}
