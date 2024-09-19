package net.tv.twitch.chrono_fish.numeron;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Numeron extends JavaPlugin {

    private ArrayList<NuGame> nuGames;

    @Override
    public void onEnable() {
        this.nuGames = new ArrayList<>();
        Bukkit.getPluginManager().registerEvents(new GameListener(this), this);
    }

    public ArrayList<NuGame> getNuGames() {return nuGames;}

    public NuPlayer getNuPlayer(Player player){
        NuPlayer nuPlayer = null;
        for(NuGame nuGame : nuGames){
            if(nuGame.getFirstP().getPlayer().equals(player)) nuPlayer = nuGame.getFirstP();
            if(nuGame.getSecondP().getPlayer().equals(player)) nuPlayer = nuGame.getSecondP();
        }
        return nuPlayer;
    }
}
