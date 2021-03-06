package xyz.oribuin.rankupmax.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.event.Listener;
import xyz.oribuin.rankupmax.RankupMax;

import java.util.List;

public abstract class OriCommand implements TabExecutor, Listener {

    public final RankupMax plugin;
    private final String commandName;

    public OriCommand(RankupMax plugin, String commandName) {
        this.plugin = plugin;
        this.commandName = commandName;
    }

    /**
     * Register the command & listener in the plugin.
     */
    public void register() {
        PluginCommand cmd = Bukkit.getPluginCommand(this.commandName);

        if (cmd != null) {
            cmd.setExecutor(this);
            cmd.setTabCompleter(this);
        }

        Bukkit.getPluginManager().registerEvents(this, plugin);
        Bukkit.getLogger().info("Registered Command " + StringUtils.capitalize(this.commandName));
    }

    /**
     * Required void function for command execution
     *
     * @param sender The command sender
     * @param args   The arguments provided in the command
     */
    public abstract void executeCommand(CommandSender sender, String[] args);

    /**
     * Setup the tab completion for the command
     *
     * @param sender The person executing the command
     * @param args   The arguments being provided
     * @return Tab complete String List
     */
    public abstract List<String> tabComplete(CommandSender sender, String[] args);

    // Register Command
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.executeCommand(sender, args);
        return true;
    }

    // Register command tab complete
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
        return this.tabComplete(sender, args);
    }

    // Get Command Name
    public String getName() {
        return commandName;
    }

    // Get command
    public Command getCommand() {
        return plugin.getCommand(this.getName());
    }
}