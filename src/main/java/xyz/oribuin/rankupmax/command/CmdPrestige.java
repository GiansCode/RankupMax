package xyz.oribuin.rankupmax.command;

import me.clip.ezprestige.EZPrestige;
import me.clip.ezprestige.PrestigeManager;
import me.clip.ezrankspro.EZAPI;
import me.clip.ezrankspro.EZRanksPro;
import me.clip.ezrankspro.rankdata.Rankup;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import xyz.oribuin.rankupmax.RankupMax;

import java.io.File;
import java.util.List;

public class CmdPrestige extends OriCommand {
    private final RankupMax plugin;

    public CmdPrestige(RankupMax plugin) {
        super(plugin, "maxprestige");
        this.plugin = plugin;
    }

    @Override
    public void executeCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        EZPrestige ezPrestige = EZPrestige.getPlugin(EZPrestige.class);
        PrestigeManager prestigeManager = new PrestigeManager(ezPrestige);

        Bukkit.getScheduler().runTaskAsynchronously(plugin, bukkitTask -> {

            while (!PrestigeManager.isLastPrestige(player)) {
                double cost = PrestigeManager.getNextPrestigeCost(player);
                if (ezPrestige.eco.hasEnoughMoney(cost, player)) {
                    ezPrestige.eco.withdrawMoney(cost, player);
                    prestigeManager.prestigePlayer(player);
                }
            }
        });

    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}