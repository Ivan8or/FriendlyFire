package online.umbcraft.friendlyfire;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FFCommands implements CommandExecutor {

    FriendlyFire plugin;

    public FFCommands(FriendlyFire plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("friendlyfire.reload")) {
            plugin.refreshTeamNames();
            sender.sendMessage(ChatColor.GREEN+"Config reloaded for FriendlyFire!");
            return true;
        }
        else {
            sender.sendMessage(ChatColor.RED+"You lack the permission to run this command!");
            return false;
        }
    }
}
