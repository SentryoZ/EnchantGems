package me.sentryozvn.enchantgems;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.cscorelib2.updater.GitHubBuildsUpdater;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
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

        createGem(category, "Sweeping", cfg.getInt("max-enchant-levels.sweeping"),
                new ItemStack(Material.CHAIN),
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, "Sharpness", cfg.getInt("max-enchant-levels.sharpness"),
                new ItemStack(Material.FLINT),
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, "Silk Touch", cfg.getInt("max-enchant-levels.silk_touch"),
                new ItemStack(Material.SLIME_BLOCK),
                new ItemStack(Material.DIAMOND_PICKAXE)
        );
        createGem(category, "Mending", cfg.getInt("max-enchant-levels.mending"),
                SlimefunItems.FILLED_FLASK_OF_KNOWLEDGE,
                new ItemStack(Material.BOOK)
        );
        createGem(category, "Power", cfg.getInt("max-enchant-levels.power"),
                new ItemStack(Material.FLINT),
                new ItemStack(Material.BOW)
        );
        createGem(category, "Flame", cfg.getInt("max-enchant-levels.flame"),
                SlimefunItems.FIRE_RUNE,
                new ItemStack(Material.BOW)
        );
        createGem(category, "Infinity", cfg.getInt("max-enchant-levels.infinity"),
                SlimefunItems.TALISMAN_WHIRLWIND,
                new ItemStack(Material.BOW)
        );
        createGem(category, "Punch", cfg.getInt("max-enchant-levels.punch"),
                SlimefunItems.GRANDPAS_WALKING_STICK,
                new ItemStack(Material.BOW)
        );
        createGem(category, "Channeling", cfg.getInt("max-enchant-levels.channeling"),
                SlimefunItems.LIGHTNING_RUNE,
                new ItemStack(Material.TRIDENT)
        );
        createGem(category, "Bane Of Arthropods", cfg.getInt("max-enchant-levels.bane_of_arthropods"),
                new ItemStack(Material.FERMENTED_SPIDER_EYE),
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, "Smite", cfg.getInt("max-enchant-levels.smite"),
                SlimefunItems.MONSTER_JERKY,
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, "Depth Strider", cfg.getInt("max-enchant-levels.depth_strider"),
                SlimefunItems.TALISMAN_WATER,
                new ItemStack(Material.DIAMOND_BOOTS)
        );
        createGem(category, "Efficiency", cfg.getInt("max-enchant-levels.efficiency"),
                SlimefunItems.TALISMAN_MINER,
                new ItemStack(Material.DIAMOND_PICKAXE)
        );
        createGem(category, "Unbreaking", cfg.getInt("max-enchant-levels.unbreaking"),
                SlimefunItems.REINFORCED_ALLOY_INGOT,
                new ItemStack(Material.BOOK)
        );
        createGem(category, "Fire Aspect", cfg.getInt("max-enchant-levels.fire_aspect"),
                SlimefunItems.LAVA_CRYSTAL,
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, "Frost Walker", cfg.getInt("max-enchant-levels.frost_walker"),
                SlimefunItems.REACTOR_COOLANT_CELL,
                new ItemStack(Material.DIAMOND_BOOTS)
        );
        createGem(category, "Knockback", cfg.getInt("max-enchant-levels.knockback"),
                SlimefunItems.GRANDPAS_WALKING_STICK,
                new ItemStack(Material.DIAMOND_SWORD)
        );

    }

    public void createGem(
            Category category,
            String enc,
            Integer maxlv,
            ItemStack material,
            ItemStack tool
    ) {
        SlimefunItemStack slimefunItem =
                new SlimefunItemStack(
                        enc.replace(" ", "_").toUpperCase() + "_ENCHANT_GEM",
                        Material.PRISMARINE_CRYSTALS,
                        ChatColor.BLUE + "Enchant Gem",
                        "",
                        ChatColor.GRAY + "Enchant: " + enc,
                        ChatColor.GRAY + "Max level: " + maxlv,
                        ChatColor.GRAY + "Right click to use");

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

        new EnchantGemsHandle(
                category,
                slimefunItem,
                RecipeType.MAGIC_WORKBENCH,
                recipe
        ).register(this);
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
