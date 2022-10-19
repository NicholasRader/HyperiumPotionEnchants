package us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.enchants.custom.bow;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.HyperiumPotionEnchants;

import java.util.Random;

public class BlindingEnchant implements Listener {

    private final HyperiumPotionEnchants instance = HyperiumPotionEnchants.getInstance();

    private PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, 100, 0);

    @EventHandler (priority = EventPriority.MONITOR)
    public void onShoot(EntityShootBowEvent e) {
        if(e.getEntity() != null && e.getEntity() instanceof Player) {
            if(!e.getBow().getItemMeta().hasLore())
                return;
            if(e.getBow().getItemMeta().getLore().contains(ChatColor.translateAlternateColorCodes('&', "&eBlinding"))) {
                e.getProjectile().setMetadata("blinded", new FixedMetadataValue(instance, "yes"));
            }
        }
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onHit(EntityDamageByEntityEvent e) {
        if(e.getEntity() != null && e.getEntity() instanceof Player && e.getDamager() != null && e.getDamager() instanceof Arrow) {
            if(((Arrow) e.getDamager()).getShooter() instanceof Player) {
                if(e.getDamager().hasMetadata("blinded")) {
                    Random random = new Random();
                    int selection = random.nextInt(10);
                    if(selection < 3) {
                        ((Player) e.getEntity()).addPotionEffect(blindness);
                        e.getEntity().sendMessage(ChatColor.translateAlternateColorCodes('&',
                                "&9&lENCHANTS &8» &7You've been blinded by &b" + ((Player) ((Arrow) e.getDamager()).getShooter()).getName() + "'s &eBlinding&7!"));
                    }
                }
            }
        }
    }

}
