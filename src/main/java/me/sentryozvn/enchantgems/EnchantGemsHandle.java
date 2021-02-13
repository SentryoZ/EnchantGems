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
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;
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
        String enc = "Unbreaking";
        int encMaxLevel = 1;

        if (im.hasLore()) {
            for (String line : Objects.requireNonNull(im.getLore())) {
                if (line.startsWith(ChatColor.GRAY + "Enchant: ")) {
                    enc = line
                            .replace(ChatColor.GRAY + "Enchant: ", "")
                            .replace(" ", "_")
                            .toLowerCase();
                }
                if (line.startsWith(ChatColor.GRAY + "Max level: ")) {
                    try {
                        encMaxLevel = Integer.parseInt(line.replace(ChatColor.GRAY + "Max level: ", ""));
                    } catch (NumberFormatException e) {
                        encMaxLevel = 1;

                    }
                }
            }
        }

        ItemStack book = new ItemStack(Material.BOOK);
        int encLv = ThreadLocalRandom.current().nextInt(1, encMaxLevel + 1);

        Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(enc));

        if (enchantment != null) {
            EnchantmentStorageMeta storageEnchant = (EnchantmentStorageMeta) book.getItemMeta();
            storageEnchant.addStoredEnchant(enchantment, encLv, true);
            book.setItemMeta(storageEnchant);

            Inventory inventory = player.getInventory();
            inventory.addItem(book);
        } else {
            player.sendMessage(ChatColor.RED + "----------------------------");
            player.sendMessage(ChatColor.RED + "Something went wrong");
            player.sendMessage(ChatColor.GOLD + "Enchant: " + enc);
            player.sendMessage(ChatColor.RED + "Level: " + encLv + "/" + encMaxLevel);
        }

        player.playSound(player.getEyeLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 1);
        ItemUtils.consumeItem(event.getItem(), false);
    }
}
