package me.sentryozvn.enchantgems;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.cscorelib2.updater.GitHubBuildsUpdater;
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
        createGem(category, Enchantment.ARROW_DAMAGE,"Power", "ARROW_DAMAGE",
                new ItemStack(Material.FLINT),
                new ItemStack(Material.BOW)
        );
        createGem(category, Enchantment.ARROW_FIRE,"Flame", "ARROW_FIRE",
                SlimefunItems.FIRE_RUNE,
                new ItemStack(Material.BOW)
        );
        createGem(category, Enchantment.ARROW_INFINITE,"Infinity", "ARROW_INFINITE",
                SlimefunItems.TALISMAN_WHIRLWIND,
                new ItemStack(Material.BOW)
        );
        createGem(category, Enchantment.ARROW_KNOCKBACK,"Punch", "ARROW_KNOCKBACK",
                SlimefunItems.GRANDPAS_WALKING_STICK,
                new ItemStack(Material.BOW)
        );
        createGem(category, Enchantment.CHANNELING,"Channeling", "CHANNELING",
                SlimefunItems.LIGHTNING_RUNE,
                new ItemStack(Material.TRIDENT)
        );
        createGem(category, Enchantment.DAMAGE_ARTHROPODS,"Bane of Arthropods", "DAMAGE_ARTHROPODS",
                new ItemStack(Material.FERMENTED_SPIDER_EYE),
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, Enchantment.DAMAGE_UNDEAD,"Smite", "DAMAGE_UNDEAD",
                SlimefunItems.MONSTER_JERKY,
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, Enchantment.DEPTH_STRIDER,"Depth Strider", "DEPTH_STRIDER",
                SlimefunItems.TALISMAN_WATER,
                new ItemStack(Material.DIAMOND_BOOTS)
        );
        createGem(category, Enchantment.DIG_SPEED,"Efficiency", "DIG_SPEED",
                SlimefunItems.TALISMAN_MINER,
                new ItemStack(Material.DIAMOND_PICKAXE)
        );
        createGem(category, Enchantment.DURABILITY,"Unbreaking", "DURABILITY",
                SlimefunItems.REINFORCED_ALLOY_INGOT,
                new ItemStack(Material.BOOK)
        );
        createGem(category, Enchantment.FIRE_ASPECT,"Fire Aspect", "FIRE_ASPECT",
                SlimefunItems.LAVA_CRYSTAL,
                new ItemStack(Material.DIAMOND_SWORD)
        );
        createGem(category, Enchantment.FROST_WALKER,"Frost Walker", "FROST_WALKER",
                SlimefunItems.REACTOR_COOLANT_CELL,
                new ItemStack(Material.DIAMOND_BOOTS)
        );
        createGem(category, Enchantment.KNOCKBACK,"Knock back", "KNOCKBACK",
                SlimefunItems.GRANDPAS_WALKING_STICK,
                new ItemStack(Material.DIAMOND_SWORD)
        );

    }

    public void createGem(
            Category category,
            Enchantment enchant,
            String name,
            String id,
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
