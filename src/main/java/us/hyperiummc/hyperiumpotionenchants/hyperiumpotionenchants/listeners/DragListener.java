package us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.listeners;

import com.codingforcookies.armorequip.ArmorType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.HyperiumPotionEnchants;

import java.util.ArrayList;
import java.util.List;

public class DragListener implements Listener {

    private final HyperiumPotionEnchants instance = HyperiumPotionEnchants.getInstance();
    @EventHandler
    public void dragEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem() != null) {
            if(e.getAction().equals(InventoryAction.SWAP_WITH_CURSOR)) {
                ItemStack clicked = e.getCurrentItem();
                if (e.getCursor().isSimilar(instance.speed) && (e.getClick().isLeftClick() || e.getClick().isRightClick())) {
                    if(!(applyEnchant(p, clicked, "&bSpeed", Material.DIAMOND_BOOTS)))
                        return;
                }

                else if (e.getCursor().isSimilar(instance.fire) && (e.getClick().isLeftClick() || e.getClick().isRightClick())) {
                    if(!(applyEnchant(p, clicked, "&cFire Resistance", Material.DIAMOND_LEGGINGS)))
                        return;
                }

                else if (e.getCursor().isSimilar(instance.str) && (e.getClick().isLeftClick() || e.getClick().isRightClick())) {
                    if(!(applyEnchant(p, clicked, "&4Strength", Material.DIAMOND_CHESTPLATE)))
                        return;
                }

                else if (e.getCursor().isSimilar(instance.health) && (e.getClick().isLeftClick() || e.getClick().isRightClick())) {
                    if(!(applyEnchant(p, clicked, "&6Health Boost", Material.DIAMOND_HELMET)))
                        return;
                }

                else if (e.getCursor().isSimilar(instance.wither) && (e.getClick().isLeftClick() || e.getClick().isRightClick())) {
                    if(!(applyEnchant(p, clicked, "&5Wither", Material.DIAMOND_SWORD)))
                        return;
                }

                else if (e.getCursor().isSimilar(instance.slow) && (e.getClick().isLeftClick() || e.getClick().isRightClick())) {
                    if(!(applyEnchant(p, clicked, "&9Freeze", Material.DIAMOND_SWORD)))
                        return;
                }

                else if (e.getCursor().isSimilar(instance.poison) && (e.getClick().isLeftClick() || e.getClick().isRightClick())) {
                    if(!(applyEnchant(p, clicked, "&2Poison Touch", Material.BOW)))
                        return;
                }

                else if (e.getCursor().isSimilar(instance.blind) && (e.getClick().isLeftClick() || e.getClick().isRightClick())) {
                    if(!(applyEnchant(p, clicked, "&eBlinding", Material.BOW)))
                        return;
                }

                else
                    return;

                e.setCancelled(true);
                p.getItemOnCursor().setAmount(p.getItemOnCursor().getAmount() - 1);
            }
        }
    }

    private boolean applyEnchant(Player p, ItemStack item, String lore, Material applicable) {
        if(!(item.getType().equals(applicable))) {
            String type = "null";
            if(applicable.equals(Material.DIAMOND_HELMET))
                type = "helmets";
            else if(applicable.equals(Material.DIAMOND_CHESTPLATE))
                type = "chestplates";
            else if(applicable.equals(Material.DIAMOND_LEGGINGS))
                type = "leggings";
            else if(applicable.equals(Material.DIAMOND_BOOTS))
                type = "boots";
            else if(applicable.equals(Material.DIAMOND_SWORD))
                type = "swords";
            else if(applicable.equals(Material.BOW))
                type = "bows";

            p.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    instance.messagesConfig.getString("messages.wrong-item").replaceAll("%type%", type)));

            return false;
        }

        ItemMeta meta = item.getItemMeta();

        List<String> itemLore = new ArrayList<>();
        if(meta.hasLore())
            itemLore = meta.getLore();

        if(itemLore.contains(ChatColor.translateAlternateColorCodes('&', lore))) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', instance.messagesConfig.getString("messages.already-has-enchant")));
            return false;
        }

        itemLore.add(ChatColor.translateAlternateColorCodes('&', lore));
        meta.setLore(itemLore);
        item.setItemMeta(meta);

        return true;
    }

}
