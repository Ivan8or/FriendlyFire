package online.umbcraft.friendlyfire;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.List;

public class FriendlyFireListener implements Listener {

    FriendlyFire plugin;
    public FriendlyFireListener(FriendlyFire plugin) {
        this.plugin = plugin;
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onTakingDamage(EntityDamageByEntityEvent e) {

        if(!(e.getEntity() instanceof Player))
            return;

        Player victim = (Player)e.getEntity();
        Player damager = null;

        if(e.getDamager() instanceof Projectile && (((Projectile)e.getDamager()).getShooter() instanceof Player))
            damager = (Player) ((Projectile) e.getDamager()).getShooter();

        else if(e.getDamager() instanceof Player)
            damager = (Player) e.getDamager();

        if(damager == null)
            return;

        if(damager.getUniqueId() == victim.getUniqueId())
            return;

        if(plugin.onSameTeam(damager, victim))
            e.setCancelled(true);

    }
}
