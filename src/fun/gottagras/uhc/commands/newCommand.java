package fun.gottagras.uhc.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fun.gottagras.uhc.Main;
import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class newCommand implements CommandExecutor {
    private final Main main;
    public newCommand(Main main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        // CHECK
        if (main.uhc_created)
        {
            commandSender.sendMessage("§6Server need reload");
            return false;
        }

        // SEND PLAYERS TO LOBBY
        for (Player player:Bukkit.getServer().getOnlinePlayers())
        {
            final ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF("lobby");
            player.sendPluginMessage(Main.INSTANCE, "BungeeCord", out.toByteArray());
        }

        // UHC STATE
        main.uhc_created = true;
        main.uhc_state = "waiting";

        // UNLOAD WORLD
        Bukkit.unloadWorld(Bukkit.getWorld("uhc"), true);
        Bukkit.unloadWorld(Bukkit.getWorld("uhc_nether"), true);
        Bukkit.unloadWorld(Bukkit.getWorld("uhc_the_end"), true);

        // LOAD WORLD
        WorldCreator worldCreator = new WorldCreator("uhc");
        worldCreator.environment(World.Environment.NORMAL);

        WorldCreator worldNetherCreator = new WorldCreator("uhc_nether");
        worldNetherCreator.environment(World.Environment.NETHER);

        WorldCreator worldTheEndCreator = new WorldCreator("uhc_the_end");
        worldTheEndCreator.environment(World.Environment.THE_END);

        // DELETE WORLD
        File uhcFile = new File(System.getProperty("user.dir") + "\\uhc");
        main.fileDelete(uhcFile);
        File uhcNetherFile = new File(System.getProperty("user.dir") + "\\uhc_nether");
        main.fileDelete(uhcNetherFile);
        File uhcEndFile = new File(System.getProperty("user.dir") + "\\uhc_the_end");
        main.fileDelete(uhcEndFile);


        // SET BIOMES
        String generator = "{\"coordinateScale\":684.412,\"heightScale\":684.412,\"lowerLimitScale\":512.0,\"upperLimitScale\":512.0,\"depthNoiseScaleX\":200.0,\"depthNoiseScaleZ\":200.0,\"depthNoiseScaleExponent\":0.5,\"mainNoiseScaleX\":80.0,\"mainNoiseScaleY\":160.0,\"mainNoiseScaleZ\":80.0,\"baseSize\":8.5,\"stretchY\":12.0,\"biomeDepthWeight\":1.0,\"biomeDepthOffset\":0.0,\"biomeScaleWeight\":1.0,\"biomeScaleOffset\":0.0,\"seaLevel\":1,\"useCaves\":true,\"useDungeons\":true,\"dungeonChance\":8,\"useStrongholds\":true,\"useVillages\":true,\"useMineShafts\":true,\"useTemples\":true,\"useMonuments\":false,\"useRavines\":true,\"useWaterLakes\":true,\"waterLakeChance\":2,\"useLavaLakes\":true,\"lavaLakeChance\":80,\"useLavaOceans\":false,\"fixedBiome\":-1,\"biomeSize\":4,\"riverSize\":4,\"dirtSize\":33,\"dirtCount\":10,\"dirtMinHeight\":0,\"dirtMaxHeight\":256,\"gravelSize\":33,\"gravelCount\":8,\"gravelMinHeight\":0,\"gravelMaxHeight\":256,\"graniteSize\":33,\"graniteCount\":10,\"graniteMinHeight\":0,\"graniteMaxHeight\":80,\"dioriteSize\":33,\"dioriteCount\":10,\"dioriteMinHeight\":0,\"dioriteMaxHeight\":80,\"andesiteSize\":33,\"andesiteCount\":10,\"andesiteMinHeight\":0,\"andesiteMaxHeight\":80,\"coalSize\":17,\"coalCount\":20,\"coalMinHeight\":0,\"coalMaxHeight\":128,\"ironSize\":9,\"ironCount\":20,\"ironMinHeight\":0,\"ironMaxHeight\":64,\"goldSize\":9,\"goldCount\":2,\"goldMinHeight\":0,\"goldMaxHeight\":32,\"redstoneSize\":8,\"redstoneCount\":8,\"redstoneMinHeight\":0,\"redstoneMaxHeight\":16,\"diamondSize\":8,\"diamondCount\":1,\"diamondMinHeight\":0,\"diamondMaxHeight\":16,\"lapisSize\":7,\"lapisCount\":1,\"lapisCenterHeight\":16,\"lapisSpread\":16}";
        worldCreator.generatorSettings(generator);


        if (strings.length > 0)
        {
            // SET SEED
            long seed = Long.parseLong(strings[0]);

            // SEED WORLD
            worldCreator.seed(seed);
            worldNetherCreator.seed(seed);
            worldTheEndCreator.seed(seed);
        }

        // CREATION WORLD
        worldCreator.environment(World.Environment.NORMAL);
        worldNetherCreator.environment(World.Environment.NETHER);
        worldTheEndCreator.environment(World.Environment.THE_END);

        Bukkit.createWorld(worldCreator);
        Bukkit.createWorld(worldNetherCreator);
        Bukkit.createWorld(worldTheEndCreator);

        World world = Bukkit.getWorld("uhc");
        World world_nether = Bukkit.getWorld("uhc_nether");
        World world_the_end = Bukkit.getWorld("uhc_the_end");

        world.setGameRuleValue("naturalRegeneration", "false");
        world_nether.setGameRuleValue("naturalRegeneration", "false");
        world_the_end.setGameRuleValue("naturalRegeneration", "false");

        return false;
    }
}
