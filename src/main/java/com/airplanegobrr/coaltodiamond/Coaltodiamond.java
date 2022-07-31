package com.airplanegobrr.coaltodiamond;

import com.airplanegobrr.coaltodiamond.events.InventoryClick;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Coaltodiamond extends JavaPlugin {

    public ItemStack special;
    FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {
        // Plugin startup logic

        //Enchanted Catalyst
        ItemStack EC_Item = new ItemStack(Material.DIAMOND);
        ItemMeta EC_Meta = EC_Item.getItemMeta();
        
        EC_Meta.setDisplayName("Enchanted Catalyst");
        List<String> EC_lore = new ArrayList<>();
        EC_lore.add("Used to make Enchanted Gem Dispenser!");
        EC_Meta.setLore(EC_lore);
        EC_Meta.addEnchant(Enchantment.DURABILITY, 1, true);
        EC_Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        EC_Item.setItemMeta(EC_Meta);

        ShapedRecipe EC_Recipe = new ShapedRecipe(NamespacedKey.minecraft("enchantedcatalyst"), EC_Item);
        EC_Recipe.shape("DLD",
                        "LSL",
                        "DLD");
        EC_Recipe.setIngredient('S', Material.NETHER_STAR);
        EC_Recipe.setIngredient('D', Material.DIAMOND);
        EC_Recipe.setIngredient('L', Material.LAPIS_LAZULI);
        Bukkit.getServer().addRecipe(EC_Recipe);
        MaterialData EC_Data = EC_Item.getData();

        //Enchanted Gem Dispenser
        ItemStack EGD_Item = new ItemStack(Material.DISPENSER);
        ItemMeta EGD_Meta = EGD_Item.getItemMeta();
        EGD_Meta.setDisplayName("§9§lEnchanted Catalyst§r§o§n");
        List<String> EGD_lore = new ArrayList<>();
        EGD_lore.add("Used to convert");
        EGD_lore.add("Coal to Diamonds!");
        EGD_Meta.setLore(EGD_lore);
        EGD_Meta.addEnchant(Enchantment.DURABILITY, 1, true);
        EGD_Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        EGD_Item.setItemMeta(EGD_Meta);
        ShapelessRecipe EGD_Recipe = new ShapelessRecipe(NamespacedKey.minecraft("enchantedgemdispenser"), EGD_Item);
        EGD_Recipe.addIngredient(1, EC_Data);
        EGD_Recipe.addIngredient(Material.DISPENSER);

        Bukkit.getServer().addRecipe(EGD_Recipe);

        //Spical coal


        getServer().getPluginManager().registerEvents(new InventoryClick(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void log(String s) {
        if (false) {
            this.getLogger().info(s);
        }
    }
}
