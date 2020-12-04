package xyz.oribuin.rankupmax.command;

import me.clip.ezrankspro.EZAPI;
import me.clip.ezrankspro.EZRanksPro;
import me.clip.ezrankspro.rankdata.Rankup;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.oribuin.rankupmax.RankupMax;

import java.util.List;

public class CmdRank extends OriCommand {
    private final RankupMax plugin;

    public CmdRank(RankupMax plugin) {
        super(plugin, "maxrankup");
        this.plugin = plugin;
    }

    @Override
    public void executeCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        EZRanksPro ezRanksPro = EZRanksPro.getInstance();
        EZAPI api = EZRanksPro.getAPI();

        for (int i = api.getCurrentRankup(player).getOrder(); i < Rankup.getLoadedRankupAmount(); i++) {
            if (api.isLastRank(player)) {
                break;
            }

            Rankup rankup = api.getRankup(api.getRankupName(player));
            if (ezRanksPro.getEconomy().hasEnoughMoney(rankup.getCost(), player)) {
                ezRanksPro.getEconomy().withdrawMoney(rankup.getCost(), player);
                ezRanksPro.getActionHandler().executeRankupActions(player, Rankup.getRankup(player));
            }
        }

    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}