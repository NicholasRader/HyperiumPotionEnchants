package us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.enchants;

import com.codingforcookies.armorequip.ArmorEquipEvent;
import com.codingforcookies.armorequip.ArmorType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.HyperiumPotionEnchants;

public class ApplyArmorEnchants implements Listener {

    private final HyperiumPotionEnchants instance = HyperiumPotionEnchants.getInstance();

    private PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1);
    private PotionEffect fire = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0);
    private PotionEffect str = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0);
    private PotionEffect health = new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 0);

    @EventHandler
    public void onEquip(ArmorEquipEvent e) {
        Player p = e.getPlayer();

        if(!(e.getNewArmorPiece() != null && e.getNewArmorPiece().getType() != Material.AIR))
            return;

        ItemStack piece = e.getNewArmorPiece();
        ItemMeta meta = piece.getItemMeta();

        if(e.getType().equals(ArmorType.HELMET)) {
            if(!(meta.hasLore()))
                return;

            if(meta.getLore().contains(ChatColor.translateAlternateColorCodes('&', "&6Health Boost")))
                p.addPotionEffect(health);
        }

        if(e.getType().equals(ArmorType.CHESTPLATE)) {
            if(!(meta.hasLore()))
                return;

            if(meta.getLore().contains(ChatColor.translateAlternateColorCodes('&', "&4Strength")))
                p.addPotionEffect(str);
        }

        if(e.getType().equals(ArmorType.LEGGINGS)) {
            if(!(meta.hasLore()))
                return;

            if(meta.getLore().contains(ChatColor.translateAlternateColorCodes('&', "&cFire Resistance")))
                p.addPotionEffect(fire);
        }

        if(!(instance.allowedWorlds.contains(p.getWorld().getName())))
            return;

        if(e.getType().equals(ArmorType.BOOTS)) {
            if(!(meta.hasLore()))
                return;

            if(meta.getLore().contains(ChatColor.translateAlternateColorCodes('&', "&bSpeed")))
                p.addPotionEffect(speed);
        }
    }

    @EventHandler
    public void onUnequip(ArmorEquipEvent e) {
        Player p = e.getPlayer();

        if(!(e.getOldArmorPiece() != null && e.getOldArmorPiece().getType() != Material.AIR))
            return;

        ItemStack piece = e.getOldArmorPiece();
        ItemMeta meta = piece.getItemMeta();

        if(e.getType().equals(ArmorType.HELMET)) {
            if(!(meta.hasLore()))
                return;

            if(meta.getLore().contains(ChatColor.translateAlternateColorCodes('&', "&6Health Boost")))
                p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
        }

        if(e.getType().equals(ArmorType.CHESTPLATE)) {
            if(!(meta.hasLore()))
                return;

            if(meta.getLore().contains(ChatColor.translateAlternateColorCodes('&', "&4Strength")))
                p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        }

        if(e.getType().equals(ArmorType.LEGGINGS)) {
            if(!(meta.hasLore()))
                return;

            if(meta.getLore().contains(ChatColor.translateAlternateColorCodes('&', "&cFire Resistance")))
                p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
        }

        if(!(instance.allowedWorlds.contains(p.getWorld().getName())))
            return;

        if(e.getType().equals(ArmorType.BOOTS)) {
            if(!(meta.hasLore()))
                return;

            if(meta.getLore().contains(ChatColor.translateAlternateColorCodes('&', "&bSpeed")))
                p.removePotionEffect(PotionEffectType.SPEED);
        }
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();

        if(!(instance.allowedWorlds.contains(p.getWorld().getName()))) {
            if (p.hasPotionEffect(PotionEffectType.SPEED))
                p.removePotionEffect(PotionEffectType.SPEED);
//            if (p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE))
//                p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
//            if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
//                p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
//            if (p.hasPotionEffect(PotionEffectType.HEALTH_BOOST))
//                p.removePotionEffect(PotionEffectType.HEALTH_BOOST);

            return;
        }

        ItemStack helmet = p.getInventory().getHelmet();
        ItemStack chestplate = p.getInventory().getChestplate();
        ItemStack leggings = p.getInventory().getLeggings();
        ItemStack boots = p.getInventory().getBoots();

        if(helmet != null) {
            ItemMeta helmetMeta = helmet.getItemMeta();
            if(!(helmetMeta.hasLore()))
                return;

            if(helmetMeta.getLore().contains(ChatColor.translateAlternateColorCodes('&', "&6Health Boost")))
                p.addPotionEffect(health);
        }

        if(chestplate != null) {
            ItemMeta chestplateMeta = chestplate.getItemMeta();
            if(!(chestplateMeta.hasLore()))
                return;

            if(chestplateMeta.getLore().contains(ChatColor.translateAlternateColorCodes('&', "&4Strength")))
                p.addPotionEffect(str);
        }

        if(leggings != null) {
            ItemMeta leggingsMeta = leggings.getItemMeta();
            if(!(leggingsMeta.hasLore()))
                return;

            if(leggingsMeta.getLore().contains(ChatColor.translateAlternateColorCodes('&', "&cFire Resistance")))
                p.addPotionEffect(fire);
        }

        if(boots != null) {
            ItemMeta bootsMeta = boots.getItemMeta();
            if(!(bootsMeta.hasLore()))
                return;

            if(bootsMeta.getLore().contains(ChatColor.translateAlternateColorCodes('&', "&bSpeed")))
                p.addPotionEffect(speed);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = (Player) e.getEntity();

        if (p.hasPotionEffect(PotionEffectType.SPEED))
            p.removePotionEffect(PotionEffectType.SPEED);
        if (p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE))
            p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
        if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
            p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        if (p.hasPotionEffect(PotionEffectType.HEALTH_BOOST))
            p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        ItemStack helmet = p.getInventory().getHelmet();
        ItemStack chestplate = p.getInventory().getChestplate();
        ItemStack leggings = p.getInventory().getLeggings();
        ItemStack boots = p.getInventory().getBoots();

        if(helmet != null) {
            ItemMeta helmetMeta = helmet.getItemMeta();
            if(!(helmetMeta.hasLore()))
                return;

            if(helmetMeta.getLore().contains(ChatColor.translateAlternateColorCodes('&', "&6Health Boost")))
                p.addPotionEffect(health);
        }

        if(chestplate != null) {
            ItemMeta chestplateMeta = chestplate.getItemMeta();
            if(!(chestplateMeta.hasLore()))
                return;

            if(chestplateMeta.getLore().contains(ChatColor.translateAlternateColorCodes('&', "&4Strength")))
                p.addPotionEffect(str);
        }

        if(leggings != null) {
            ItemMeta leggingsMeta = leggings.getItemMeta();
            if(!(leggingsMeta.hasLore()))
                return;

            if(leggingsMeta.getLore().contains(ChatColor.translateAlternateColorCodes('&', "&cFire Resistance")))
                p.addPotionEffect(fire);
        }

        if(!(instance.allowedWorlds.contains(p.getWorld().getName())))
            return;

        if(boots != null) {
            ItemMeta bootsMeta = boots.getItemMeta();
            if(!(bootsMeta.hasLore()))
                return;

            if(bootsMeta.getLore().contains(ChatColor.translateAlternateColorCodes('&', "&bSpeed")))
                p.addPotionEffect(speed);
        }
    }

}
