package us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.HyperiumPotionEnchants;

import java.util.List;

public class CECommand implements CommandExecutor {

    private final HyperiumPotionEnchants instance = HyperiumPotionEnchants.getInstance();

    public static ItemStack speed = new ItemStack(Material.NETHER_STAR, 1);
    public static ItemStack fire = new ItemStack(Material.NETHER_STAR, 1);
    public static ItemStack str = new ItemStack(Material.NETHER_STAR, 1);
    public static ItemStack health = new ItemStack(Material.NETHER_STAR, 1);
    public static ItemStack wither = new ItemStack(Material.NETHER_STAR, 1);
    public static ItemStack slow = new ItemStack(Material.NETHER_STAR, 1);
    public static ItemStack poison = new ItemStack(Material.NETHER_STAR, 1);
    public static ItemStack blind = new ItemStack(Material.NETHER_STAR, 1);


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;

        Player p = (Player) sender;

        ItemStack filler = new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(" ");
        filler.setItemMeta(fillerMeta);

        instance.gui.setItem(0, filler);
        instance.gui.setItem(1, filler);
        instance.gui.setItem(2, filler);
        instance.gui.setItem(3, filler);
        instance.gui.setItem(4, filler);
        instance.gui.setItem(5, filler);
        instance.gui.setItem(6, filler);
        instance.gui.setItem(7, filler);
        instance.gui.setItem(8, filler);
        instance.gui.setItem(9, filler);
        instance.gui.setItem(11, filler);
        instance.gui.setItem(13, filler);
        instance.gui.setItem(15, filler);
        instance.gui.setItem(17, filler);
        instance.gui.setItem(18, filler);
        instance.gui.setItem(20, filler);
        instance.gui.setItem(22, filler);
        instance.gui.setItem(24, filler);
        instance.gui.setItem(26, filler);
        instance.gui.setItem(27, filler);
        instance.gui.setItem(28, filler);
        instance.gui.setItem(29, filler);
        instance.gui.setItem(30, filler);
        instance.gui.setItem(31, filler);
        instance.gui.setItem(32, filler);
        instance.gui.setItem(33, filler);
        instance.gui.setItem(34, filler);
        instance.gui.setItem(35, filler);
        instance.gui.setItem(36, filler);
        instance.gui.setItem(40, filler);
        instance.gui.setItem(44, filler);
        instance.gui.setItem(45, filler);
        instance.gui.setItem(46, filler);
        instance.gui.setItem(47, filler);
        instance.gui.setItem(48, filler);
        instance.gui.setItem(49, filler);
        instance.gui.setItem(50, filler);
        instance.gui.setItem(51, filler);
        instance.gui.setItem(52, filler);
        instance.gui.setItem(53, filler);

        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta helmetMeta = helmet.getItemMeta();
        helmetMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', instance.messagesConfig.getString("enchants.helmet")));
        helmet.setItemMeta(helmetMeta);
        instance.gui.setItem(10, helmet);
        instance.gui.setItem(19, health);

        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta chestplateMeta = chestplate.getItemMeta();
        chestplateMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', instance.messagesConfig.getString("enchants.chestplate")));
        chestplate.setItemMeta(chestplateMeta);
        instance.gui.setItem(12, chestplate);
        instance.gui.setItem(21, str);

        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta leggingsMeta = leggings.getItemMeta();
        leggingsMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', instance.messagesConfig.getString("enchants.leggings")));
        leggings.setItemMeta(leggingsMeta);
        instance.gui.setItem(14, leggings);
        instance.gui.setItem(23, fire);

        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta bootsMeta = boots.getItemMeta();
        bootsMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', instance.messagesConfig.getString("enchants.boots")));
        boots.setItemMeta(bootsMeta);
        instance.gui.setItem(16, boots);
        instance.gui.setItem(25, speed);

        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', instance.messagesConfig.getString("enchants.sword")));
        sword.setItemMeta(swordMeta);
        instance.gui.setItem(37, sword);
        instance.gui.setItem(38, wither);
        instance.gui.setItem(39, slow);

        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', instance.messagesConfig.getString("enchants.bow")));
        bow.setItemMeta(bowMeta);
        instance.gui.setItem(43, bow);
        instance.gui.setItem(41, blind);
        instance.gui.setItem(42, poison);

        p.openInventory(instance.gui);

        return true;
    }

}
