package us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.commands.CECommand;
import us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.commands.ReloadCommand;
import us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.enchants.ApplyArmorEnchants;
import us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.enchants.custom.bow.BlindingEnchant;
import us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.enchants.custom.bow.PoisonTouchEnchant;
import us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.enchants.custom.sword.FreezeEnchant;
import us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.enchants.custom.sword.WitherEnchant;
import us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.listeners.BuyEnchantListener;
import us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.listeners.DragListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class HyperiumPotionEnchants extends JavaPlugin {

    private static HyperiumPotionEnchants instance;

    public static HyperiumPotionEnchants getInstance() {
        return instance;
    }

    public FileConfiguration config;
    public File messages;
    public FileConfiguration messagesConfig;

    public Inventory gui;
    public List<String> allowedWorlds = new ArrayList<>();

    public ItemStack speed = new ItemStack(Material.NETHER_STAR, 1);
    public ItemStack fire = new ItemStack(Material.NETHER_STAR, 1);
    public ItemStack str = new ItemStack(Material.NETHER_STAR, 1);
    public ItemStack health = new ItemStack(Material.NETHER_STAR, 1);
    public ItemStack wither = new ItemStack(Material.NETHER_STAR, 1);
    public ItemStack slow = new ItemStack(Material.NETHER_STAR, 1);
    public ItemStack poison = new ItemStack(Material.NETHER_STAR, 1);
    public ItemStack blind = new ItemStack(Material.NETHER_STAR, 1);

    @Override
    public void onEnable() {
        instance = this;

        loadFiles();
        gui = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&',
                messagesConfig.getString("messages.inventory")));
        allowedWorlds.addAll(getConfig().getStringList("allowed-worlds"));

        this.getCommand("hpenchants").setExecutor(new ReloadCommand());
        this.getCommand("ce").setExecutor(new CECommand());

        initStars();

        Bukkit.getPluginManager().registerEvents(new BlindingEnchant(), this);
        Bukkit.getPluginManager().registerEvents(new PoisonTouchEnchant(), this);
        Bukkit.getPluginManager().registerEvents(new FreezeEnchant(), this);
        Bukkit.getPluginManager().registerEvents(new WitherEnchant(), this);

        Bukkit.getPluginManager().registerEvents(new BuyEnchantListener(), this);
        Bukkit.getPluginManager().registerEvents(new DragListener(), this);
        Bukkit.getPluginManager().registerEvents(new ApplyArmorEnchants(), this);
    }

    public void initStars() {
        ItemMeta speedMeta = speed.getItemMeta();
        speedMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getConfig().getString("enchants.speed.name")));

        ItemMeta fireMeta = fire.getItemMeta();
        fireMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getConfig().getString("enchants.fire.name")));

        ItemMeta strMeta = str.getItemMeta();
        strMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getConfig().getString("enchants.strength.name")));

        ItemMeta healthMeta = health.getItemMeta();
        healthMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getConfig().getString("enchants.health.name")));

        ItemMeta blindMeta = blind.getItemMeta();
        blindMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getConfig().getString("enchants.blinding.name")));

        ItemMeta poisonMeta = poison.getItemMeta();
        poisonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getConfig().getString("enchants.poisontouch.name")));

        ItemMeta slowMeta = slow.getItemMeta();
        slowMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getConfig().getString("enchants.freeze.name")));

        ItemMeta witherMeta = wither.getItemMeta();
        witherMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getConfig().getString("enchants.wither.name")));

        List<String> lore = new ArrayList<>();

        for (String s : getConfig().getStringList("enchants.speed.lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        speedMeta.setLore(lore);
        speed.setItemMeta(speedMeta);
        lore.add(ChatColor.translateAlternateColorCodes('&',
                instance.getConfig().getString("cost-lore").replaceAll("%levels%", instance.getConfig().getString("enchants.speed.cost"))));
        speedMeta.setLore(lore);
        CECommand.speed.setItemMeta(speedMeta);
        lore.clear();

        for (String s : getConfig().getStringList("enchants.fire.lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        fireMeta.setLore(lore);
        fire.setItemMeta(fireMeta);
        lore.add(ChatColor.translateAlternateColorCodes('&',
                instance.getConfig().getString("cost-lore").replaceAll("%levels%", instance.getConfig().getString("enchants.fire.cost"))));
        fireMeta.setLore(lore);
        CECommand.fire.setItemMeta(fireMeta);
        lore.clear();

        for (String s : getConfig().getStringList("enchants.strength.lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        strMeta.setLore(lore);
        str.setItemMeta(strMeta);
        lore.add(ChatColor.translateAlternateColorCodes('&',
                instance.getConfig().getString("cost-lore").replaceAll("%levels%", instance.getConfig().getString("enchants.strength.cost"))));
        strMeta.setLore(lore);
        CECommand.str.setItemMeta(strMeta);
        lore.clear();

        for (String s : getConfig().getStringList("enchants.health.lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        healthMeta.setLore(lore);
        health.setItemMeta(healthMeta);
        lore.add(ChatColor.translateAlternateColorCodes('&',
                instance.getConfig().getString("cost-lore").replaceAll("%levels%", instance.getConfig().getString("enchants.health.cost"))));
        healthMeta.setLore(lore);
        CECommand.health.setItemMeta(healthMeta);
        lore.clear();

        for (String s : getConfig().getStringList("enchants.blinding.lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        blindMeta.setLore(lore);
        blind.setItemMeta(blindMeta);
        lore.add(ChatColor.translateAlternateColorCodes('&',
                instance.getConfig().getString("cost-lore").replaceAll("%levels%", instance.getConfig().getString("enchants.blinding.cost"))));
        blindMeta.setLore(lore);
        CECommand.blind.setItemMeta(blindMeta);
        lore.clear();

        for (String s : getConfig().getStringList("enchants.poisontouch.lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        poisonMeta.setLore(lore);
        poison.setItemMeta(poisonMeta);
        lore.add(ChatColor.translateAlternateColorCodes('&',
                instance.getConfig().getString("cost-lore").replaceAll("%levels%", instance.getConfig().getString("enchants.poisontouch.cost"))));
        poisonMeta.setLore(lore);
        CECommand.poison.setItemMeta(poisonMeta);
        lore.clear();

        for (String s : getConfig().getStringList("enchants.freeze.lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        slowMeta.setLore(lore);
        slow.setItemMeta(slowMeta);
        lore.add(ChatColor.translateAlternateColorCodes('&',
                instance.getConfig().getString("cost-lore").replaceAll("%levels%", instance.getConfig().getString("enchants.freeze.cost"))));
        slowMeta.setLore(lore);
        CECommand.slow.setItemMeta(slowMeta);
        lore.clear();

        for (String s : getConfig().getStringList("enchants.wither.lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        witherMeta.setLore(lore);
        wither.setItemMeta(witherMeta);
        lore.add(ChatColor.translateAlternateColorCodes('&',
                instance.getConfig().getString("cost-lore").replaceAll("%levels%", instance.getConfig().getString("enchants.wither.cost"))));
        witherMeta.setLore(lore);
        CECommand.wither.setItemMeta(witherMeta);
        lore.clear();
    }

    private void loadFiles() {
        try {
            // Config
            getLogger().info(ChatColor.translateAlternateColorCodes('&', "&9&lHPEnchants&8 -&r &bLoading &fconfig.yml."));
            config = this.getConfig();
            config.options().copyDefaults();
            saveDefaultConfig();
            // Config

            // Messages
            messages = new File(getDataFolder(), "messages.yml");
            if(!(messages.exists())) {
                getLogger().info(ChatColor.translateAlternateColorCodes('&', "&9&lHPEnchants&8 -&r &aCreating &fmessages.yml."));
                saveResource("messages.yml", true);
            }
            else {
                getLogger().info(ChatColor.translateAlternateColorCodes('&', "&9&lHPEnchants&8 -&r &bLoading &fmessages.yml."));
            }
            messagesConfig = YamlConfiguration.loadConfiguration(messages);
            // Messages
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
