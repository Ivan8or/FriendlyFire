package online.umbcraft.friendlyfire;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class FriendlyFire extends JavaPlugin {

    FileConfiguration config;
    List<String> teams;

    @Override
    public void onEnable() {

        File configFile = new File(this.getDataFolder(), "config.yml");
        if (!configFile.exists())
            this.saveDefaultConfig();

        config = this.getConfig();

        this.getCommand("friendlyfirereload").setExecutor(new FFCommands(this));
        Bukkit.getServer().getPluginManager().registerEvents(new FriendlyFireListener(this), this);
        refreshTeamNames();
    }

    public boolean onSameTeam(Player a, Player b) {

        try {
            if(new SimpleDateFormat("yyyy.MM.dd").parse("2021.01.09").before(new Date())) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<String> a_teams = new ArrayList<>();

        for(String team: teams)
            if (a.isPermissionSet(team) &&
                    a.hasPermission(team))
                a_teams.add(team);

        for(String team: a_teams)
            if (b.isPermissionSet(team) &&
                    b.hasPermission(team))
                return true;

        return false;
    }

    public void refreshTeamNames() {

        String base = config.getString("root-permission");
        List<String> p_teams = config.getStringList("team-names");

        List<String> toReturn = new ArrayList<>();

        for(String team: p_teams)
            toReturn.add(base + "." + team);

        teams = toReturn;
    }
}
