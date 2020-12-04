package xyz.oribuin.rankupmax.command;

import org.bukkit.command.CommandSender;
import xyz.oribuin.rankupmax.RankupMax;
import xyz.oribuin.rankupmax.manager.MessageManager;
import xyz.oribuin.rankupmax.util.StringPlaceholders;

import java.util.List;

public class CmdReload extends OriCommand {
    private final RankupMax plugin;

    public CmdReload(RankupMax plugin) {
        super(plugin, "maxreload");
        this.plugin = plugin;
    }

    @Override
    public void executeCommand(CommandSender sender, String[] args) {
        MessageManager msgM = this.plugin.getMessageManager();
        if (!sender.hasPermission("rankupmax.reload")) {
            msgM.sendMessage(sender, "invalid-permission");
            return;
        }

        this.plugin.reload();
        msgM.sendMessage(sender, "reload", StringPlaceholders.single("version", this.plugin.getDescription().getVersion()));

    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return null;
    }
}