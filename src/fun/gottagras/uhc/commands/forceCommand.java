package fun.gottagras.uhc.commands;

import fun.gottagras.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class forceCommand implements CommandExecutor
{
    private final Main main;
    public forceCommand(Main main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (strings.length >= 1)
        {
            switch (strings[0])
            {
                case "pve":
                    if (main.uhc_state.equals("start")) main.uhc_invincible = main.uhc_time + 5;
                    else commandSender.sendMessage("§6Current UHC State: §7"+ main.uhc_state+"§8 | §6Needed UHC State: §7start");
                    break;

                case "pvp":
                    if (main.uhc_state.equals("pve")) main.uhc_pvp = main.uhc_time + 5;
                    else commandSender.sendMessage("§6Current UHC State: §7"+ main.uhc_state+"§8 | §6Needed UHC State: §7pve");
                    break;

                case "border":
                    if (main.uhc_state.equals("pvp")) main.uhc_border = main.uhc_time + 5;
                    else commandSender.sendMessage("§6Current UHC State: §7"+ main.uhc_state+"§8 | §6Needed UHC State: §7pvp");
                    break;

                case "meetup":
                    if (main.uhc_state.equals("border"))
                    {
                        main.uhc_meetup = main.uhc_time + 5;
                    }
                    else commandSender.sendMessage("§6Current UHC State: §7"+ main.uhc_state+"§8 | §6Needed UHC State: §7border");
                    break;

                case "addplayer":
                    main.uhc_real_player_number ++;
                    main.uhc_player_number ++;
                    break;

                case "removeplayer":
                    main.uhc_real_player_number --;
                    main.uhc_player_number --;
                    break;

                default:
                    commandSender.sendMessage("§6/force <pve|pvp|border|meetup|addplayer|removeplayer>");
                    break;
            }
        }
        else commandSender.sendMessage("§6/force <pve|pvp|border|meetup|addplayer|removeplayer>");
        return false;
    }
}
