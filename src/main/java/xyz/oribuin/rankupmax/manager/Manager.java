package xyz.oribuin.rankupmax.manager;

import xyz.oribuin.rankupmax.RankupMax;

public abstract class Manager {

    protected final RankupMax plugin;

    public Manager(RankupMax plugin) {
        this.plugin = plugin;
    }

    public abstract void reload();
}
