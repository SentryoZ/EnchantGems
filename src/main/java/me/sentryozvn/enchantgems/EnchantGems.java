package me.sentryozvn.enchantgems;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.cscorelib2.updater.GitHubBuildsUpdater;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class EnchantGems extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        // Read something from your config.yml
        Config cfg = new Config(this);

//        if (cfg.getBoolean("options.auto-update")) {
//           new GitHubBuildsUpdater(this, this.getFile(), "SentryoZ/EnchantGems/master").start();
//        }

        // Create Categories
        ItemStack categoryItem =
                new CustomItem(
                        Material.PRISMARINE_CRYSTALS,
                        "&bEnchant Gems",
                        "",
                        "&a> Click to open");

        // Give your Category a unique id.
        NamespacedKey categoryId = new NamespacedKey(this, "enchant_gems");
        Category category = new Category(categoryId, categoryItem);

        createGem(category, Enchantment.SWEEPING_EDGE,"Sweeping Edge", "SWEEPING_EDGE",
                new ItemStack(Material.CHAIN),
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, Enchantment.DAMAGE_ALL,"Sharpness", "DAMAGE_ALL",
                new ItemStack(Material.FLINT),
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, Enchantment.SILK_TOUCH,"Silk Touch", "SILK_TOUCH",
                new ItemStack(Material.SLIME_BLOCK),
                new ItemStack(Material.DIAMOND_PICKAXE)
        );
        createGem(category, Enchantment.MENDING,"Mending", "MENDING",
                SlimefunItems.FILLED_FLASK_OF_KNOWLEDGE,
                new ItemStack(Material.BOOK)
        );
    }

    public void createGem(
            Category category,
            Enchantment enchant,
            String id,
            String name,
            ItemStack material,
            ItemStack tool
    ) {
        SlimefunItemStack slimefunItem =
                new SlimefunItemStack(
                        id + "_ENCHANT_GEM",
                        Material.PRISMARINE_CRYSTALS,
                        "&b" + name + " Enchant Gem",
                        "",
                        "&7Disenchant this to use");
        slimefunItem.addUnsafeEnchantment(enchant, 1);

        ItemStack[] recipe = {
                SlimefunItems.MAGIC_LUMP_3,
                tool,
                SlimefunItems.MAGIC_LUMP_3,
                SlimefunItems.ENDER_RUNE,
                material,
                SlimefunItems.STRANGE_NETHER_GOO,
                SlimefunItems.MAGIC_LUMP_3,
                SlimefunItems.MAGICAL_BOOK_COVER,
                SlimefunItems.MAGIC_LUMP_3,
        };

        SlimefunItem item =
                new SlimefunItem(
                        category,
                        slimefunItem,
                        RecipeType.MAGIC_WORKBENCH,
                        recipe
                );

        item.register(this);
    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

}
