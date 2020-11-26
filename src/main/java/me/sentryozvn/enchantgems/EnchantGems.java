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

        createGem(category, "SWEEPING_EDGE", 5,
                new ItemStack(Material.CHAIN),
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, "DAMAGE_ALL", 5,
                new ItemStack(Material.FLINT),
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, "SILK_TOUCH", 1,
                new ItemStack(Material.SLIME_BLOCK),
                new ItemStack(Material.DIAMOND_PICKAXE)
        );
        createGem(category, "MENDING", 1,
                SlimefunItems.FILLED_FLASK_OF_KNOWLEDGE,
                new ItemStack(Material.BOOK)
        );
        createGem(category, "ARROW_DAMAGE", 5,
                new ItemStack(Material.FLINT),
                new ItemStack(Material.BOW)
        );
        createGem(category, "ARROW_FIRE", 1,
                SlimefunItems.FIRE_RUNE,
                new ItemStack(Material.BOW)
        );
        createGem(category, "ARROW_INFINITE", 1,
                SlimefunItems.TALISMAN_WHIRLWIND,
                new ItemStack(Material.BOW)
        );
        createGem(category, "ARROW_KNOCKBACK", 5,
                SlimefunItems.GRANDPAS_WALKING_STICK,
                new ItemStack(Material.BOW)
        );
        createGem(category, "CHANNELING", 5,
                SlimefunItems.LIGHTNING_RUNE,
                new ItemStack(Material.TRIDENT)
        );
        createGem(category, "DAMAGE_ARTHROPODS", 5,
                new ItemStack(Material.FERMENTED_SPIDER_EYE),
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, "DAMAGE_UNDEAD", 5,
                SlimefunItems.MONSTER_JERKY,
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, "DEPTH_STRIDER", 5,
                SlimefunItems.TALISMAN_WATER,
                new ItemStack(Material.DIAMOND_BOOTS)
        );
        createGem(category, "DIG_SPEED", 5,
                SlimefunItems.TALISMAN_MINER,
                new ItemStack(Material.DIAMOND_PICKAXE)
        );
        createGem(category, "DURABILITY", 5,
                SlimefunItems.REINFORCED_ALLOY_INGOT,
                new ItemStack(Material.BOOK)
        );
        createGem(category, "FIRE_ASPECT", 5,
                SlimefunItems.LAVA_CRYSTAL,
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, "FROST_WALKER", 1,
                SlimefunItems.REACTOR_COOLANT_CELL,
                new ItemStack(Material.DIAMOND_BOOTS)
        );
        createGem(category, "KNOCKBACK", 5,
                SlimefunItems.GRANDPAS_WALKING_STICK,
                new ItemStack(Material.DIAMOND_SWORD)
        );

    }

    public void createGem(
            Category category,
            String id,
            Integer maxlv,
            ItemStack material,
            ItemStack tool
    ) {
        String encLore = id.replace("_", " ").toLowerCase().substring(0, 1).toUpperCase() + id.substring(1);
        SlimefunItemStack slimefunItem =
                new SlimefunItemStack(
                        id + "_ENCHANT_GEM",
                        Material.PRISMARINE_CRYSTALS,
                        ChatColor.BLUE + "Enchant Gem",
                        "",
                        ChatColor.GRAY + "Enchant: " + encLore,
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

        SlimefunItem item =
                new EnchantGemsHandle(
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
