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

        createGem(category, "Sweeping", 5,
                new ItemStack(Material.CHAIN),
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, "Sharpness", 5,
                new ItemStack(Material.FLINT),
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, "Silk Touch", 1,
                new ItemStack(Material.SLIME_BLOCK),
                new ItemStack(Material.DIAMOND_PICKAXE)
        );
        createGem(category, "Mending", 1,
                SlimefunItems.FILLED_FLASK_OF_KNOWLEDGE,
                new ItemStack(Material.BOOK)
        );
        createGem(category, "Punch", 5,
                new ItemStack(Material.FLINT),
                new ItemStack(Material.BOW)
        );
        createGem(category, "Flame", 1,
                SlimefunItems.FIRE_RUNE,
                new ItemStack(Material.BOW)
        );
        createGem(category, "Infinity", 1,
                SlimefunItems.TALISMAN_WHIRLWIND,
                new ItemStack(Material.BOW)
        );
        createGem(category, "Knockback", 5,
                SlimefunItems.GRANDPAS_WALKING_STICK,
                new ItemStack(Material.BOW)
        );
        createGem(category, "Channeling", 5,
                SlimefunItems.LIGHTNING_RUNE,
                new ItemStack(Material.TRIDENT)
        );
        createGem(category, "Bane Of Arthropods", 5,
                new ItemStack(Material.FERMENTED_SPIDER_EYE),
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, "Smite", 5,
                SlimefunItems.MONSTER_JERKY,
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, "Depth Strider", 5,
                SlimefunItems.TALISMAN_WATER,
                new ItemStack(Material.DIAMOND_BOOTS)
        );
        createGem(category, "Efficiency", 5,
                SlimefunItems.TALISMAN_MINER,
                new ItemStack(Material.DIAMOND_PICKAXE)
        );
        createGem(category, "Unbreaking", 5,
                SlimefunItems.REINFORCED_ALLOY_INGOT,
                new ItemStack(Material.BOOK)
        );
        createGem(category, "Fire Aspect", 5,
                SlimefunItems.LAVA_CRYSTAL,
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, "Frost Walker", 1,
                SlimefunItems.REACTOR_COOLANT_CELL,
                new ItemStack(Material.DIAMOND_BOOTS)
        );
        createGem(category, "Knockback", 5,
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
