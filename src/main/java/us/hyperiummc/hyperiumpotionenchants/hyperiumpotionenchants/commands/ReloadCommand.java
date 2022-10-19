package us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import us.hyperiummc.hyperiumpotionenchants.hyperiumpotionenchants.HyperiumPotionEnchants;

public class ReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender.hasPermission("hpenchants.reload"))) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    HyperiumPotionEnchants.getInstance().messagesConfig.getString("messages.unknown-command")));
            return true;
        }

        if(command.getName().equalsIgnoreCase("hpenchants") && args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            HyperiumPotionEnchants.getInstance().reloadConfig();
            HyperiumPotionEnchants.getInstance().messagesConfig = YamlConfiguration.loadConfiguration(HyperiumPotionEnchants.getInstance().messages);
            HyperiumPotionEnchants.getInstance().initStars();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&9&lHPEnchants &8- &bReloaded."));
        }

        return true;
    }

}