package us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.enchants.custom.sword;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.HyperiumPotionEnchants;

import java.util.Random;

public class FreezeEnchant implements Listener {

    private final HyperiumPotionEnchants instance = HyperiumPotionEnchants.getInstance();

    private PotionEffect slow = new PotionEffect(PotionEffectType.SLOW, 60, 0);

    @EventHandler (priority = EventPriority.MONITOR)
    public void onHit(EntityDamageByEntityEvent e) {
        if(e.getEntity() != null && e.getEntity() instanceof Player && e.getDamager() != null && e.getDamager() instanceof Player) {
            if(((Player) e.getDamager()).getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_SWORD)
            && ((Player) e.getDamager()).getInventory().getItemInMainHand().getItemMeta().hasLore()
            && ((Player) e.getDamager()).getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.translateAlternateColorCodes('&', "&9Freeze"))) {
                Random random = new Random();
                int selection = random.nextInt(10);
                if(selection < 1) {
                    ((Player) e.getEntity()).addPotionEffect(slow);
                    e.getEntity().sendMessage(ChatColor.translateAlternateColorCodes('&',
                            "&9&lENCHANTS &8» &7You've been slowed down by &b" + e.getDamager().getName() + "'s &9Freeze&7!"));
                }
            }
        }
    }

}
