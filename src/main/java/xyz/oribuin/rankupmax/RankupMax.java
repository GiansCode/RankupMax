package xyz.oribuin.rankupmax;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.oribuin.rankupmax.command.CmdPrestige;
import xyz.oribuin.rankupmax.command.CmdRank;
import xyz.oribuin.rankupmax.command.CmdReload;
import xyz.oribuin.rankupmax.manager.ConfigManager;
import xyz.oribuin.rankupmax.manager.MessageManager;

public class RankupMax extends JavaPlugin {

    private static RankupMax instance;
    private ConfigManager configManager;
    private MessageManager messageManager;

    public static RankupMax getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        this.configManager = new ConfigManager(this);
        this.messageManager = new MessageManager(this);

        new CmdPrestige(this).register();
        new CmdRank(this).register();
        new CmdReload(this).register();

        this.saveDefaultConfig();
        this.reload();
    }

    public void reload() {
        this.configManager.reload();
        this.messageManager.reload();
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }
}
