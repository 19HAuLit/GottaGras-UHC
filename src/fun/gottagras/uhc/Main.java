package fun.gottagras.uhc;

import fun.gottagras.uhc.commands.*;
import fun.gottagras.uhc.listeners.*;
import fun.gottagras.uhc.menu.limitStuffMenu;
import fun.gottagras.uhc.menu.teamMenu;
import fun.gottagras.uhc.menu.uhcMenu;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.nio.file.Files;
import java.util.*;

public class Main extends JavaPlugin
{
    public static Main INSTANCE;
    @Override
    public void onEnable()
    {
        // BUNGEE CORD
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        INSTANCE = this;

        // PATCH
        getServer().getPluginManager().registerEvents(new strengthPatch(this), this);

        // LISTERNERS
        getServer().getPluginManager().registerEvents(new pvpListener(this), this);
        getServer().getPluginManager().registerEvents(new teamMenu(this), this);
        getServer().getPluginManager().registerEvents(new deathListener(this), this);
        getServer().getPluginManager().registerEvents(new the_end(this), this);
        getServer().getPluginManager().registerEvents(new nether(this), this);
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
        getCommand("invsee").setExecutor(new invseeCommand(this));
        getCommand("item").setExecutor(new itemCommand(this));
        getCommand("broadcast").setExecutor(new broadcastCommand(this));
        getCommand("team").setExecutor(new teamCommand(this));

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

        // CREATION DES TEAMS
        nameList.add("Bleu");
        nameList.add("Rouge");
        nameList.add("Vert");
        nameList.add("Jaune");
        nameList.add("Rose");
        colorList.add("??1");
        colorList.add("??4");
        colorList.add("??2");
        colorList.add("??e");
        colorList.add("??d");
        symbolList.add("");
        symbolList.add("???");
        symbolList.add("???");
        symbolList.add("???");
        symbolList.add("???");
        symbolList.add("???");
        for (int i = 0; i < symbolList.size(); i++)
        {
            for (int j = 0; j < colorList.size(); j++)
            {
                uhc_team.put(colorList.get(j)+symbolList.get(i)+nameList.get(j), new ArrayList<String>());
            }
        }
    }

    @Override
    public void onDisable()
    {
        for (Player player : Bukkit.getOnlinePlayers())
        {
            player.setDisplayName(player.getName());
            player.setPlayerListName(player.getName());
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
    public boolean uhc_nether = true;
    public boolean uhc_the_end = true;
    public int uhc_team_size = 1;
    public boolean uhc_team_friendly_fire = false;
    public boolean uhc_team_only_one_winner = false;

    // UHC TIME
    public int uhc_invincible = 30;
    public int uhc_pvp = 1200;
    public int uhc_border = 3600;
    public int uhc_meetup = 5400;

    // UHC PLAYERS
    public int uhc_player_number = 0;
    public List<String> uhc_player_list = new ArrayList<String>();
    public int uhc_real_player_number = 0;
    public List<String> uhc_real_player_list = new ArrayList<String>();
    public List<String> noob_list = new ArrayList<String>();

    // UHC TEAMS
    public Map<String, List<String>> uhc_team = new HashMap<>();
    public List<String> nameList = new ArrayList<String>();
    public List<String> colorList = new ArrayList<String>();
    public List<String> symbolList = new ArrayList<String>();

    // PLAYER DAMAGER | KILL
    public Map<String, String> last_damager = new HashMap<String, String>();
    public Map<String, Long> last_damager_time = new HashMap<String, Long>();
    public Map<String, Integer> player_kill = new HashMap<String, Integer>();

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
    public boolean uhc_stuffLimit_potionInstantDamage = false;
    public boolean uhc_stuffLimit_potionLevelII = false;

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

    public boolean checkOneTeamLeft()
    {
        int team_alive = 0;
        for (String team : uhc_team.keySet())
        {
            boolean teamOk = false;
            for (String uuid : uhc_real_player_list)
            {
                for (Player player : Bukkit.getOnlinePlayers())
                {
                    if (player.getUniqueId().toString().equals(uuid))
                    {
                        if (uhc_team.get(team).contains(player.getName()) && !teamOk)
                        {
                            teamOk = true;
                            team_alive++;
                        }
                    }
                }
            }
        }

        return team_alive == 1;
    }

    public void checkWin()
    {
        if (uhc_real_player_number == 1 && uhc_team_only_one_winner)
        {
            for (Player player:Bukkit.getOnlinePlayers())
            {
                if (uhc_real_player_list.contains(player.getUniqueId().toString()))
                {
                    Bukkit.broadcastMessage("??6" +player.getDisplayName()+ "??7 a win, GG!");
                    win = true;
                }
            }
        }
        else if (checkOneTeamLeft() && !uhc_team_only_one_winner)
        {
            for (Player player:Bukkit.getOnlinePlayers())
            {
                if (uhc_real_player_list.contains(player.getUniqueId().toString()))
                {
                    Bukkit.broadcastMessage("??6" +player.getDisplayName()+ "??7 a win, GG!");
                }
            }
            win = true;
        }

        if (win)
        {
            Bukkit.broadcastMessage("??8=================");

            List<Integer> killsNb = new ArrayList<>(player_kill.values());
            killsNb.sort(Comparator.reverseOrder());
            int previousKillNb = -1;

            Set<String> playersName = player_kill.keySet();
            for (int killNb : killsNb)
            {
                if (previousKillNb != killNb)
                {
                    previousKillNb = killNb;
                    for (String playerName : playersName)
                    {
                        if (player_kill.get(playerName).equals(killNb)) Bukkit.broadcastMessage("      ??d" + playerName + "??6 - ??c" + killNb + " ??7kills\n");
                    }
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

    public Location randomLocation()
    {
        Random random = new Random();
        int map_size = (int) Bukkit.getWorld("uhc").getWorldBorder().getSize();
        int x = map_size/2-random.nextInt(map_size);
        int z = map_size/2-random.nextInt(map_size);
        return new Location(Bukkit.getWorld("uhc"), x, 255, z);
    }

    public void playerAddColorTeam(Player player)
    {
        for (int i = 0; i < symbolList.size(); i++)
        {
            for (int j = 0; j < colorList.size(); j++)
            {
                String key = colorList.get(j) + symbolList.get(i) + nameList.get(j);
                if (uhc_team.get(key).contains(player.getName()))
                {
                    player.setDisplayName(colorList.get(j) + symbolList.get(i) + " " + player.getName());
                    player.setPlayerListName(colorList.get(j) + symbolList.get(i) + " " + player.getName());
                }
            }
        }
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
