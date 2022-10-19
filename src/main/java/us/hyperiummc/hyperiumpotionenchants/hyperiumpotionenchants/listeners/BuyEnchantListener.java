package us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.HyperiumPotionEnchants;
import us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.commands.CECommand;

public class BuyEnchantListener implements Listener {

    private final HyperiumPotionEnchants instance = HyperiumPotionEnchants.getInstance();

    @EventHandler
    public void buyEnchant(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack clicked = e.getCurrentItem();
        if(e.getInventory().equals(instance.gui) && (e.getClick().isLeftClick() || e.getClick().isRightClick()) && clicked != null) {
            e.setCancelled(true);
            buyStar(p, clicked);
        }
    }

    private void buyStar(Player p, ItemStack star) {
        int subtractLevels;
        if(star.equals(CECommand.speed)) {
            subtractLevels = instance.getConfig().getInt("enchants.speed.cost");
            star = instance.speed;
        }
        else if(star.equals(CECommand.fire)) {
            subtractLevels = instance.getConfig().getInt("enchants.fire.cost");
            star = instance.fire;
        }
        else if(star.equals(CECommand.str)) {
            subtractLevels = instance.getConfig().getInt("enchants.strength.cost");
            star = instance.str;
        }
        else if(star.equals(CECommand.health)) {
            subtractLevels = instance.getConfig().getInt("enchants.health.cost");
            star = instance.health;
        }
        else if(star.equals(CECommand.wither)) {
                subtractLevels = instance.getConfig().getInt("enchants.wither.cost");
                star = instance.wither;
        }
        else if(star.equals(CECommand.slow)) {
            subtractLevels = instance.getConfig().getInt("enchants.freeze.cost");
            star = instance.slow;
        }
        else if(star.equals(CECommand.poison)) {
            subtractLevels = instance.getConfig().getInt("enchants.poisontouch.cost");
            star = instance.poison;
        }
        else if(star.equals(CECommand.blind)) {
            subtractLevels = instance.getConfig().getInt("enchants.blinding.cost");
            star = instance.blind;
        }
        else
            return;

        if(p.getLevel() < subtractLevels) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', instance.messagesConfig.getString("messages.not-enough-xp")));
            return;
        }

        p.setLevel(p.getLevel() - subtractLevels);
        p.getInventory().addItem(star);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                instance.messagesConfig.getString("messages.purchase-success").replaceAll("%levels%", Integer.toString(subtractLevels))));
    }

}
