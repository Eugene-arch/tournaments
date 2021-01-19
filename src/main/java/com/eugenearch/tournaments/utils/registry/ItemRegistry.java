package com.eugenearch.tournaments.utils.registry;

import com.eugenearch.tournaments.items.SpleefTool;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry {
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static Item SPLEEF_SHOVEL = new SpleefTool("spleef_shovel");
}
