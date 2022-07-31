package com.airplanegobrr.coaltodiamond.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import com.airplanegobrr.coaltodiamond.Coaltodiamond;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryClick implements Listener {
    private final Coaltodiamond main;

    public InventoryClick(Coaltodiamond main) {
        this.main = main;
        ItemStack C_Item = new ItemStack(Material.COAL_BLOCK);
        ItemMeta C_Meta = C_Item.getItemMeta();

        C_Meta.setDisplayName("Special Coal Block");
        List<String> C_lore = new ArrayList<>();
        C_lore.add("Used to make Diamonds!");
        C_Meta.setLore(C_lore);
        C_Meta.addEnchant(Enchantment.DURABILITY, 1, true);
        C_Meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        C_Item.setItemMeta(C_Meta);
        this.main.special = C_Item;
    }

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event) {
        main.log(" ");
        Inventory inv = event.getClickedInventory();
        Inventory inv2 = event.getInventory();
        ItemStack item = event.getCurrentItem();
        ItemStack itemHolding = event.getCursor();
        Boolean shift = event.isShiftClick();
        InventoryAction action = event.getAction();
        Event.Result res = event.getResult();

        String name = event.getView().getTitle();
        main.log("InventoryClickEvent: " + name);

        int slot = event.getSlot();
        int slot2 = event.getRawSlot();

        main.log("Inv: " + inv.getType().toString());
        main.log("Inv2: " + inv2.getType().toString());
        main.log("Clicked item: " + item.getType().toString());
        main.log("Item holding: " + itemHolding.getType().toString());
        main.log("Result: " + res.toString());
        main.log("Is the inv the block? " + (inv.getType().toString() == itemHolding.getType().toString()));
        main.log("Is the item we are clicking air? " + String.valueOf(item.getType().toString() == "AIR"));

        //slots
        main.log("Slot: "+slot);
        main.log("Slot2: "+slot2);
        //Log action
        main.log("Action: "+action.toString());


        if (name.contains("§9§lEnchanted Catalyst§o§n")) {
            main.log("Right Inv!!");
            main.log(String.valueOf(inv.getSize()));
            //ItemStack C = main.C_Item;

            if (inv.getSize() == 9) { //Good is Dispenser
                int ammountOfcoal = 0;
                int ammountOfSpecialCoal = 0;
                for (int i = 0; i < inv.getSize(); i++) {
                    ItemStack item2 = inv.getItem(i);
                    if (item2.getType() == Material.COAL_BLOCK) {
                        List itemLoreList = item2.getItemMeta().getLore();
                        if (itemLoreList != null && itemLoreList.get(0).toString().contains("Used to make Diamonds!")) {
                            ammountOfSpecialCoal++;
                        } else {
                            ammountOfcoal++;
                        } 
                    }
                }
                main.log("Ammount of coal: " + ammountOfcoal);
                main.log("Ammount of special coal: " + ammountOfSpecialCoal);
                if (ammountOfcoal == 9) {
                    //clear inv
                    for (int i = 0; i < inv.getSize(); i++) {
                        inv.setItem(i, null);
                    }
                    //ceter the center item to main.c
                    inv.setItem(4, this.main.special);
                }

                if (ammountOfSpecialCoal == 9){
                    //clear inv
                    for (int i = 0; i < inv.getSize(); i++) {
                        inv.setItem(i, null);
                    }
                    //set the center item to be a diamond
                    inv.setItem(4, new ItemStack(Material.DIAMOND));
                }
            }
        
        }

        main.log(" ");
    }
}
