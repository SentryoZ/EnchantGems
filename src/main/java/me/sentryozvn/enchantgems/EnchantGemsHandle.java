package me.sentryozvn.enchantgems;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.CSCoreLibPlugin.cscorelib2.inventory.ItemUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.concurrent.ThreadLocalRandom;

public class EnchantGemsHandle extends SlimefunItem {

    public EnchantGemsHandle(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::onItemRightClick;
        addItemHandler(itemUseHandler);
    }

    private void onItemRightClick(PlayerRightClickEvent event) {
        event.cancel();
        Player player = event.getPlayer();
        ItemMeta im = event.getItem().getItemMeta();
        assert im != null;
        String enc = null;
        Integer encMaxLevel = null;

        if (im.hasLore()) {
            for (String line : im.getLore()) {
                if (line.startsWith(ChatColor.GRAY + "Type: ")) {
                    enc = line.replace(ChatColor.GRAY + "Enchant: ", "").replace(" ", "_").toUpperCase();
                    break;
                }
                if (line.startsWith(ChatColor.GRAY + "Max level: ")) {
                    encMaxLevel = getEncMaxLevel(line);
                    break;
                }
            }
        }

        if (enc != null && encMaxLevel != null) {
            ItemStack giveItem = new ItemStack(Material.ENCHANTED_BOOK);
            int encLv = ThreadLocalRandom.current().nextInt(1, encMaxLevel + 1);
            Enchantment enchantment = EnchantmentWrapper.getByKey(NamespacedKey.minecraft(enc));

            giveItem.addUnsafeEnchantment(enchantment, encLv);

            Inventory inventory = player.getInventory();
            inventory.addItem(giveItem);

            player.playSound(player.getEyeLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 1);
            ItemUtils.consumeItem(event.getItem(), false);
        }else{
            player.sendMessage(ChatColor.GREEN + "Something went wrong");
        }
    }

    private Integer getEncMaxLevel(String line) {
        try {
            return Integer.parseInt(line.replace(ChatColor.GRAY + "Max level: : ", ""));
        } catch (NumberFormatException e) {
            return 1;
        }
    }
}
