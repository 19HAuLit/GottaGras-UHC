package fun.gottagras.uhc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.text.DecimalFormat;

public class scoreBoard extends BukkitRunnable implements Runnable {
    private final Main main;
    public scoreBoard(Main main)
    {
        this.main = main;
    }

    @Override
    public void run()
    {
        // SCOREBOARD CREATION
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("GottaGras-UHC", "ScoreBoard");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("GottaGras-UHC");

        // PLAYER ALIVE
        Score info_1 = objective.getScore("§6Joueurs en vie: §7"+ main.uhc_real_player_number);
        info_1.setScore(2);

        // PVP
        Score info_2;
        if (main.uhc_state.equals("waiting") || main.uhc_state.equals("start") || main.uhc_state.equals("pve")) info_2 = objective.getScore("§6PvP dans: §7"+ (main.uhc_pvp-main.uhc_time)/60 + "min " + (main.uhc_pvp-main.uhc_time)%60 + "s");
        else info_2 = objective.getScore("§6PvP: §7Actif");
        info_2.setScore(3);

        // BORDER
        Score info_3;
        if (main.uhc_state.equals("meetup")) info_3 = objective.getScore("§4Tapez§3-§5vous §9!!!!");
        else if (main.uhc_state.equals("border"))info_3 = objective.getScore("§6Fin border dans: §7"+ (main.uhc_meetup-main.uhc_time)/60 + "min " + (main.uhc_meetup-main.uhc_time)%60 + "s");
        else info_3 = objective.getScore("§6Border dans: §7"+ (main.uhc_border-main.uhc_time)/60 + "min " + (main.uhc_border-main.uhc_time)%60 + "s"); info_3.setScore(3);
        info_3.setScore(4);

        // TIME PLAYED
        Score info_4 = objective.getScore("§6Temps de jeu: §7" + main.uhc_time/60 + "min " + main.uhc_time%60 + "s");
        info_4.setScore(5);

        // BORDER SIZE
        if (main.uhc_created)
        {
            double size = Bukkit.getWorld("uhc").getWorldBorder().getSize()/2;
            DecimalFormat numberFormat = new DecimalFormat("#.00");
            Score info_5 = objective.getScore("§6Border: §7"+ numberFormat.format(size));
            info_5.setScore(6);
        }

        // FORMAT
        Score info_6 = objective.getScore("§6Mode de jeu: §7"+ main.uhc_format);
        info_6.setScore(7);

        // SET SCOREBOARD
        for (Player player:Bukkit.getOnlinePlayers())
        {
            player.setScoreboard(board);
        }
    }
}
